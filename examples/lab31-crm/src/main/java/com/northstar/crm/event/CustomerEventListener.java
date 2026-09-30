package com.northstar.crm.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener {

  private static final Logger log = LoggerFactory.getLogger(CustomerEventListener.class);
  private final ProcessedEventStore store;

  public CustomerEventListener(ProcessedEventStore store) {
    this.store = store;
  }

  @KafkaListener(topics = "${crm.kafka.customer-events-topic}")
  public void onCustomerEvent(
      @Payload CustomerEvent event,
      @Header(KafkaHeaders.RECEIVED_KEY) String key) {
    // TODO: reject when key == null or key does not equal event.customerId()
    // TODO: skip when !store.markIfNew(event.eventId())
    // TODO: log correlationId + customerId (no PII beyond fixture ids)
    // Reject missing or incorrect Kafka keys
    if (key == null || !key.equals(event.customerId())) {
      throw new InvalidCustomerEventException("Kafka key does not match customerId");
    }

    // Ignore duplicate events
    if (!store.markIfNew(event.eventId())) {
      log.info("duplicate_event_ignored eventId={} customerId={}", event.eventId(), event.customerId());
      return;
    }

    // Log only safe event metadata
    log.info(
        "customer_event_received correlationId={} customerId={} eventId={}",
        event.correlationId(),
        event.customerId(),
        event.eventId());

    // Handle the event
    log.info("customer_event_handled customerId={}", event.customerId());
  }
}

class InvalidCustomerEventException extends RuntimeException {
  public InvalidCustomerEventException(String message) {
    super(message);
  }
}

