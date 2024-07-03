package com.rental.enums;

/**
 * The enum Payment method.
 */
public enum PaymentType {

  /**
   * The Pay at counter.
   */
  PAY_AT_COUNTER("Pagar no balcão da locadora"),

  /**
   * The Online payment.
   */
  ONLINE_PAYMENT("Pagamento online realizado");

  private final String name;

  PaymentType(String name) {
    this.name = name;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }
}
