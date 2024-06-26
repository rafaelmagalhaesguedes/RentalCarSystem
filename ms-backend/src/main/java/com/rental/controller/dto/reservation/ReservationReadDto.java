package com.rental.controller.dto.reservation;

import com.rental.controller.dto.accessory.AccessoryDto;
import com.rental.controller.dto.person.PersonDto;
import com.rental.controller.dto.group.GroupDto;
import com.rental.entity.Reservation;
import com.rental.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReservationReadDto(
    UUID id,
    PersonDto person,
    GroupDto group,
    List<AccessoryDto> accessories,
    LocalDateTime pickupDateTime,
    LocalDateTime returnDateTime,
    Double totalAmount,
    Status status,
    String paymentMethod
) {
  public static ReservationReadDto fromEntity(Reservation reservation) {
    return new ReservationReadDto(
        reservation.getId(),
        PersonDto.fromEntity(reservation.getPerson()),
        GroupDto.fromEntity(reservation.getGroup()),
        reservation.getAccessories()
            .stream()
            .map(AccessoryDto::fromEntity)
            .toList(),
        reservation.getPickupDateTime(),
        reservation.getReturnDateTime(),
        reservation.getTotalAmount(),
        reservation.getStatus(),
        reservation.getPaymentMethod()
    );
  }
}
