package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private final Map<String, Customer> customersById = new HashMap<>();
    private String correlationId;

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Customer createCustomer(
            String customerId,
            String fullName,
            String email,
            String phone,
            CustomerStatus status) {

        requireNonBlank(customerId, "customerId");
        requireNonBlank(fullName, "fullName");
        requireUniqueId(customerId);

        Customer customer = new Customer(
                customerId,
                fullName,
                email,
                phone,
                status,
                LocalDateTime.now()
        );

        customersById.put(customerId, customer);

        return customer;
    }

    public Customer getCustomer(String customerId) {
        return requireExisting(customerId);
    }

    public Customer updateStatus(
            String customerId,
            CustomerStatus newStatus) {

        Customer customer = requireExisting(customerId);
        customer.setStatus(newStatus);

        return customer;
    }

    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " must not be blank"
            );
        }
    }

    private void requireUniqueId(String customerId) {
        if (customersById.containsKey(customerId)) {
            throw new IllegalStateException(
                    "Customer already exists: " + customerId
            );
        }
    }

    private Customer requireExisting(String customerId) {
        requireNonBlank(customerId, "customerId");

        Customer customer = customersById.get(customerId);

        if (customer == null) {
            String message = "Customer not found: " + customerId;
            if (correlationId != null && !correlationId.isBlank())
            {
                message += " correlationId=" + correlationId;
            }
            throw new IllegalArgumentException(message);
        }

        return customer;
    }
}

