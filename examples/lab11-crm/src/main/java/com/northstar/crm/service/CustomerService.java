package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Simple in-memory CustomerService (no framework annotations).
 * Holds customers in a List and provides basic add/find/update operations.
 */
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();
    private final CustomerNotifier notifier;
    private static final CustomerNotifier NOOP_NOTIFIER = (id, oldS, newS) -> {};

    public CustomerService() {
        this(NOOP_NOTIFIER);
    }

    public CustomerService(CustomerNotifier notifier) {
        this.notifier = notifier == null ? NOOP_NOTIFIER : notifier;
    }

    private void validateCustomerId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalStateException("customerId must not be blank");
        }
    }

    /**
     * Add a customer to the in-memory store.
     * Rejects blank or null customerId and duplicate customerId with IllegalStateException.
     */
    public Customer addCustomer(Customer customer) {
        Objects.requireNonNull(customer, "customer must not be null");
        String id = customer.getCustomerId();
        validateCustomerId(id);
        String trimmed = id.trim();
        if (findByCustomerId(trimmed).isPresent()) {
            throw new IllegalStateException("customerId already exists: " + trimmed);
        }
        customer.setCustomerId(trimmed);
        customers.add(customer);
        return customer;
    }

    /**
     * Find a customer by customerId.
     */
    public Optional<Customer> findByCustomerId(String customerId) {
        if (customerId == null) return Optional.empty();
        String target = customerId.trim();
        return customers.stream()
                .filter(c -> c != null && target.equals(c.getCustomerId()))
                .findFirst();
    }

    /**
     * Update the status of an existing customer. Throws IllegalArgumentException if not found.
     */
    public Customer updateStatus(String customerId, CustomerStatus status) {
        validateCustomerId(customerId);
        return findByCustomerId(customerId)
                .map(c -> {
                    CustomerStatus old = c.getStatus();
                    c.setStatus(status);
                    try {
                        notifier.notifyStatusChange(c.getCustomerId(), old, status);
                    } catch (Exception e) {
                        // Swallow notifier exceptions to avoid breaking business logic
                    }
                    return c;
                })
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
    }
}
