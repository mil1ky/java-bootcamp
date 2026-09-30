package com.northstar.crm.service;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

// TODO: add @Service
@Service
public class CustomerService {

  private static final Logger log =
          (Logger) LoggerFactory.getLogger(CustomerService.class);

  private final CustomerRepository customerRepository;
  private final NotificationService notificationService;

  public CustomerService(
          CustomerRepository customerRepository,
          NotificationService notificationService) {

    this.customerRepository = customerRepository;
    this.notificationService = notificationService;
  }

  @PostConstruct
  void init() {
    log.info("CustomerService ready");
  }

  @PreDestroy
  void shutdown() {
    log.info("CustomerService shutting down");
  }

  public Customer create(Customer customer, String correlationId) {
    Customer saved = customerRepository.save(customer);

    notificationService.notifyCreated(
            saved.getId(),
            correlationId
    );

    return saved;
  }

  public Customer get(String id) {
    return customerRepository
            .findById(id)
            .orElseThrow(() ->
                    new IllegalArgumentException(
                            "Customer not found: " + id
                    ));
  }
}
