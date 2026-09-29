package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorTest {
    CustomerValidator validator;
    InMemoryCustomerRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryCustomerRepository();
        validator = new CustomerValidator(repo);
    }

    @Test
    void prospectToActiveAllowed() {
        // TODO: validateTransition(PROSPECT, ACTIVE, "lab-request-001") does not throw

        assertDoesNotThrow(() ->
                validator.validateTransition(
                        CustomerStatus.PROSPECT, CustomerStatus.ACTIVE, "lab-request-001"));
    }

    @Test
    void activeToProspectRejected() {
        // TODO: assertThrows IllegalStateException; message contains lab-request-001
        assertThrows(IllegalStateException.class, () ->
                validator.validateTransition(
                        CustomerStatus.ACTIVE, CustomerStatus.PROSPECT, "lab-request-001"));
    }

    @Test
    void duplicateIdRejected() {
        // TODO: seed via repo.save(Customer.amina()); validateNew duplicate → throws
        repo.save(Customer.amina());
        assertThrows(IllegalStateException.class, () ->
                validator.validateNew(new Customer(
                        "CUS-1001", "Amina Kahn", "different@example.com",
                        " ", CustomerStatus.ACTIVE, LocalDateTime.now()))); }
}
