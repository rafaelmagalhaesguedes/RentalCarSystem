package com.rental.service;

import com.rental.entity.Payment;
import com.rental.entity.Reservation;
import com.rental.enums.PaymentStatus;
import com.rental.repository.PaymentRepository;
import com.rental.service.exception.PaymentNotFoundException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * The type Payment service.
 */
@Service
public class PaymentService {

  private final PaymentRepository paymentRepository;

  /**
   * Instantiates a new Payment service.
   *
   * @param paymentRepository the payment repository
   * @param stripeApiKey      the stripe api key
   */
  @Autowired
  public PaymentService(PaymentRepository paymentRepository, @Value("${stripe.api.key}") String stripeApiKey) {
    this.paymentRepository = paymentRepository;
    Stripe.apiKey = stripeApiKey;
  }

  /**
   * Create checkout session.
   *
   * @param amount      the amount
   * @param successUrl  the success url
   * @param cancelUrl   the cancel url
   * @param reservation the reservation
   * @return the session
   * @throws StripeException the stripe exception
   */
  @Transactional
  public Session createCheckoutSession(Double amount, String successUrl, String cancelUrl, Reservation reservation) throws StripeException {
    Payment payment = new Payment();
    payment.setReservation(reservation);
    payment.setAmount(amount);
    payment.setPaymentDate(LocalDateTime.now());

    paymentRepository.save(payment);

    return getSession(amount, successUrl, cancelUrl, payment);
  }

  public Session getSession(Double amount, String successUrl, String cancelUrl, Payment payment) throws StripeException {
    SessionCreateParams params = SessionCreateParams.builder()
        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
        .addLineItem(
            SessionCreateParams.LineItem.builder()
                .setPriceData(
                    SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency("BRL")
                        .setUnitAmount((long) (amount * 100)) // amount in cents
                        .setProductData(
                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName("Reservation Payment")
                                .build())
                        .build())
                .setQuantity(1L)
                .build())
        .setMode(SessionCreateParams.Mode.PAYMENT)
        .setSuccessUrl(successUrl)
        .setCancelUrl(cancelUrl)
        .build();

    return Session.create(params);
  }

  public Payment paymentSuccess(UUID paymentId) throws PaymentNotFoundException {
    Payment payment = paymentRepository.findById(paymentId)
        .orElseThrow(PaymentNotFoundException::new);

    payment.setStatus(PaymentStatus.CONFIRMED);
    return paymentRepository.save(payment);
  }

  public Payment paymentCancel(UUID paymentId) throws PaymentNotFoundException {
    Payment payment = paymentRepository.findById(paymentId)
        .orElseThrow(PaymentNotFoundException::new);

    payment.setStatus(PaymentStatus.CANCEL);
    return paymentRepository.save(payment);
  }
}
