package com.northstar.crm.repository;

import com.northstar.crm.entity.Customer;

import java.util.*;

/** Map must stay private — anti-leak rule for Lab 15. */
public class InMemoryCustomerRepository implements CustomerRepository {
    // TODO: private final Map<String, Customer> store = new HashMap<>();
    private final Map<String, Customer> store = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        // TODO: put by customerId; return customer
         store.put(customer.getCustomerId(), customer);
        return customer;
       // throw new UnsupportedOperationException("TODO: save");
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        // TODO: Optional.ofNullable(store.get(customerId))
        return Optional.ofNullable(store.get(customerId));
       // throw new UnsupportedOperationException("TODO: findById");
    }

    @Override
    public boolean existsById(String customerId) {
        // TODO: store.containsKey
        return store.containsKey(customerId);
    }

    @Override
    public boolean existsByEmail(String email) {
        // TODO: scan values for matching email (case-insensitive OK)
        return store.values().stream()
                .anyMatch(customer -> customer.getEmail() != null && customer.getEmail().equalsIgnoreCase(email));
        // throw new UnsupportedOperationException("TODO: existsByEmail");
    }

    @Override
    public List<Customer> findAll() {
        // TODO: return new ArrayList<>(store.values()) — never return the Map itself
        return new ArrayList<>(store.values());

        // throw new UnsupportedOperationException("TODO: findAll");
    }
}
