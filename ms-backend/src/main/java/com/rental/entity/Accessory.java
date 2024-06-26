package com.rental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "accessories")
public class Accessory {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  private Double dailyRate;

  public Accessory() { }

  public Accessory(String id) {
    this.id = UUID.fromString(id);
  }

  public Accessory(String name, Double dailyRate) {
    this.name = name;
    this.dailyRate = dailyRate;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = UUID.fromString(String.valueOf(id));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getDailyRate() {
    return dailyRate;
  }

  public void setDailyRate(Double dailyRate) {
    this.dailyRate = dailyRate;
  }
}