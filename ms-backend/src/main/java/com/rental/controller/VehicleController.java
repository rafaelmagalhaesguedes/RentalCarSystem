package com.rental.controller;

import com.rental.controller.dto.vehicle.VehicleCreationDto;
import com.rental.controller.dto.vehicle.VehicleDto;
import com.rental.entity.Vehicle;
import com.rental.service.VehicleService;
import com.rental.service.exception.GroupNotFoundException;
import com.rental.service.exception.VehicleNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The type Vehicle controller.
 */
@RestController
@RequestMapping("/vehicles")
@Validated
public class VehicleController {

  private final VehicleService vehicleService;

  /**
   * Instantiates a new Vehicle controller.
   *
   * @param vehicleService the vehicle service
   */
  @Autowired
  public VehicleController(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  /**
   * Gets vehicle by id.
   *
   * @param id the id
   * @return the vehicle by id
   * @throws VehicleNotFoundException the vehicle not found exception
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('MANAGER')")
  public VehicleDto getVehicleById(@PathVariable UUID id) throws VehicleNotFoundException {
    return VehicleDto.fromEntity(vehicleService.getVehicleById(id));
  }

  /**
   * Gets all vehicles.
   *
   * @param pageNumber the page number
   * @param pageSize   the page size
   * @return the all vehicles
   */
  @GetMapping
  @PreAuthorize("hasAuthority('MANAGER')")
  public List<VehicleDto> getAllVehicles(
      @RequestParam(required = false, defaultValue = "0") int pageNumber,
      @RequestParam(required = false, defaultValue = "20") int pageSize) {
    List<Vehicle> paginatedVehicles = vehicleService.getAllVehicles(pageNumber, pageSize);
    return paginatedVehicles.stream().map(VehicleDto::fromEntity).toList();
  }

  /**
   * Create vehicle.
   *
   * @param vehicleCreationDto the vehicle creation dto
   * @return the vehicle dto
   * @throws GroupNotFoundException the group not found exception
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize("hasAuthority('MANAGER')")
  public VehicleDto createVehicle(@RequestBody @Valid VehicleCreationDto vehicleCreationDto) throws GroupNotFoundException {
    return vehicleService.createVehicle(vehicleCreationDto);
  }

  /**
   * Update vehicle.
   *
   * @param vehicleCreationDto the vehicle creation dto
   * @param id                 the id
   * @return the vehicle dto
   * @throws VehicleNotFoundException the vehicle not found exception
   * @throws GroupNotFoundException   the group not found exception
   */
  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('MANAGER')")
  public VehicleDto updateVehicle(@RequestBody @Valid VehicleCreationDto vehicleCreationDto, @PathVariable UUID id) throws VehicleNotFoundException, GroupNotFoundException {
    return vehicleService.updateVehicle(vehicleCreationDto, id);
  }

  /**
   * Delete vehicle.
   *
   * @param id the id
   * @return the vehicle dto
   * @throws VehicleNotFoundException the vehicle not found exception
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('MANAGER')")
  public VehicleDto deleteVehicle(@PathVariable UUID id) throws VehicleNotFoundException {
    return VehicleDto.fromEntity(vehicleService.deleteVehicle(id));
  }
}