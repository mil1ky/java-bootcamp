package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class CustomerValidator {
    private static final Map<CustomerStatus, Set<CustomerStatus>> ALLOWED =
            new EnumMap<>(CustomerStatus.class);

    static {
        // TODO: ALLOWED.put(PROSPECT, EnumSet.of(ACTIVE, CLOSED));
        ALLOWED.put(CustomerStatus.PROSPECT, EnumSet.of(CustomerStatus.ACTIVE, CustomerStatus.CLOSED));
        // TODO: ALLOWED.put(ACTIVE, EnumSet.of(SUSPENDED, CLOSED));
        ALLOWED.put(CustomerStatus.ACTIVE, EnumSet.of(CustomerStatus.SUSPENDED, CustomerStatus.CLOSED));
        // TODO: ALLOWED.put(SUSPENDED, EnumSet.of(ACTIVE, CLOSED));
        ALLOWED.put(CustomerStatus.SUSPENDED, EnumSet.of(CustomerStatus.ACTIVE, CustomerStatus.CLOSED));
        // TODO: ALLOWED.put(CLOSED, EnumSet.noneOf(CustomerStatus.class));
        ALLOWED.put(CustomerStatus.CLOSED, EnumSet.noneOf(CustomerStatus.class));
    }

    private final CustomerRepository repository;

    public CustomerValidator(CustomerRepository repository) {
        this.repository = repository;
    }

    public void validateNew(Customer customer) {
        // TODO: require customerId; reject duplicate id / email via repository
        if (customer.getCustomerId() == null || customer.getCustomerId().isBlank())
        {
            throw new IllegalArgumentException("customerId is required");
        }
        if (repository.existsById(customer.getCustomerId()))
        {
            throw new IllegalStateException("duplicate customerId: " + customer.getCustomerId());
        }
        if (repository.existsByEmail(customer.getEmail()))
        {
            throw new IllegalStateException("duplicate email: " + customer.getEmail());
        }

    }

    public void validateTransition(CustomerStatus from, CustomerStatus to, String correlationId) {
        // TODO: reject when `to` not in ALLOWED.get(from); message must include correlationId
        Set<CustomerStatus> allowed = ALLOWED.getOrDefault(from, Set.of());
        if(!allowed.contains(to)) {
            throw new IllegalStateException("Invalid status transition from " + from + " to " + to + " for correlationId: " + correlationId);
        }
    }
}
