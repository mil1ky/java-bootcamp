package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

public class DefaultCustomerService implements CustomerService {
    // TODO: final CustomerRepository repository
    private final CustomerRepository repository;
    // TODO: final CustomerValidator validator
    private final CustomerValidator validator;

    public DefaultCustomerService(CustomerRepository repository, CustomerValidator validator) {
        // TODO: assign final fields (constructor DI — no field injection)
        this.repository = repository;
        this.validator = validator;
        // throw new UnsupportedOperationException("TODO: wire constructor DI");
    }

    @Override
    public Customer addCustomer(Customer customer) {
        // TODO: validator.validateNew then repository.save
        // throw new UnsupportedOperationException("TODO: addCustomer");
        validator.validateNew(customer);
        return repository.save(customer);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        // TODO: repository.findById
        // throw new UnsupportedOperationException("TODO: findById");
        return repository.findById(customerId);
    }

    @Override
    public List<Customer> listAll() {
        // TODO: return List.copyOf(repository.findAll()) — never leak mutable Map/List
        // throw new UnsupportedOperationException("TODO: listAll");
        return List.copyOf(repository.findAll());
    }

    @Override
    public Customer changeStatus(String customerId, CustomerStatus newStatus, String correlationId) {
        // TODO: find or throw; validateTransition BEFORE setStatus; save; return
        // throw new UnsupportedOperationException("TODO: changeStatus");
        Customer existing = repository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "customer not found [" + correlationId + "]: " + customerId));
        validator.validateTransition(existing.getStatus(), newStatus, correlationId);
        existing.setStatus(newStatus);
        // touchUpdatedAt() if your entity supports it; else setUpdatedAt(now)
        return repository.save(existing);
    }

}
