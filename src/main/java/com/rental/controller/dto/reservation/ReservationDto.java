package com.rental.controller.dto.reservation;

import com.rental.controller.dto.accessory.AccessoryDto;
import com.rental.entity.Reservation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.rental.entity.Customer;
import com.rental.entity.Group;

public record ReservationDto(
    UUID id,
    Customer customer,
    Group group,
    List<AccessoryDto> accessories,
    LocalDateTime pickupDateTime,
    LocalDateTime returnDateTime,
    Double totalAmount,
    String status,
    String paymentMethod,
    String paymentUrl
) {
  public static ReservationDto fromEntity(Reservation reservation, String paymentUrl) {
    return new ReservationDto(
        reservation.getId(),
        reservation.getCustomer(),
        reservation.getGroup(),
        reservation.getAccessories()
            .stream()
            .map(AccessoryDto::fromEntity)
            .toList(),
        reservation.getPickupDateTime(),
        reservation.getReturnDateTime(),
        reservation.getTotalAmount(),
        reservation.getStatus(),
        reservation.getPaymentMethod(),
        paymentUrl
    );
  }
}

