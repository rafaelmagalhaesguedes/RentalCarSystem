package com.rental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

/**
 * The type Vehicle.
 */
@Entity
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String model;

  private String licensePlate;

  private String brand;

  private String color;

  private Date yearOfManufacture;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "group_id", nullable = false)
  private Group group;

  /**
   * Instantiates a new Vehicle.
   */
  public Vehicle() { }

  /**
   * Instantiates a new Vehicle.
   *
   * @param id                the id
   * @param model             the model
   * @param licensePlate      the license plate
   * @param brand             the brand
   * @param color             the color
   * @param yearOfManufacture the year of manufacture
   * @param group             the group
   */
  public Vehicle(UUID id, String model, String licensePlate, String brand, String color,
      Date yearOfManufacture, Group group) {
    this.id = id;
    this.model = model;
    this.licensePlate = licensePlate;
    this.brand = brand;
    this.color = color;
    this.yearOfManufacture = yearOfManufacture;
    this.group = group;
  }

  /**
   * Instantiates a new Vehicle.
   *
   * @param model             the model
   * @param licensePlate      the license plate
   * @param brand             the brand
   * @param color             the color
   * @param yearOfManufacture the year of manufacture
   * @param group             the group
   */
  public Vehicle(String model, String licensePlate, String brand, String color,
      Date yearOfManufacture, Group group) {
    this.model = model;
    this.licensePlate = licensePlate;
    this.brand = brand;
    this.color = color;
    this.yearOfManufacture = yearOfManufacture;
    this.group = group;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Gets model.
   *
   * @return the model
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets model.
   *
   * @param model the model
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets license plate.
   *
   * @return the license plate
   */
  public String getLicensePlate() {
    return licensePlate;
  }

  /**
   * Sets license plate.
   *
   * @param licensePlate the license plate
   */
  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  /**
   * Gets brand.
   *
   * @return the brand
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets brand.
   *
   * @param brand the brand
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets color.
   *
   * @param color the color
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Gets year of manufacture.
   *
   * @return the year of manufacture
   */
  public Date getYearOfManufacture() {
    return yearOfManufacture;
  }

  /**
   * Sets year of manufacture.
   *
   * @param yearOfManufacture the year of manufacture
   */
  public void setYearOfManufacture(Date yearOfManufacture) {
    this.yearOfManufacture = yearOfManufacture;
  }

  /**
   * Gets group.
   *
   * @return the group
   */
  public Group getGroup() {
    return group;
  }

  /**
   * Sets group.
   *
   * @param group the group
   */
  public void setGroup(Group group) {
    this.group = group;
  }
}
