package com.northstar.crm.api;

import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Customer> create(
          @RequestBody Customer customer,
          @RequestHeader(value = "X-Correlation-Id", defaultValue = "lab-request-001") String correlationId) {
    Customer saved = customerService.create(customer, correlationId);

    URI location =
            URI.create("/api/customers/" + saved.getId());

    return ResponseEntity
            .created(location)
            .body(saved);
  }


  @GetMapping("/{id}")
  public ResponseEntity<Customer> getById(
          @PathVariable String id,
          @RequestHeader(
                  value = "X-Correlation-Id",
                  defaultValue = "lab-request-001"
          ) String correlationId) {

    try {
      return ResponseEntity.ok(customerService.get(id));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}

