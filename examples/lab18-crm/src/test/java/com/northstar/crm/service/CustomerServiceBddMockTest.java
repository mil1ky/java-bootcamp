package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.validation.Validator;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceBddMockTest {

    @Mock
    CustomerRepository repository;

    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        // TODO: wire real CustomerValidator + DefaultCustomerService
        CustomerValidator validator = new CustomerValidator(repository);
        service = new DefaultCustomerService(repository, validator);
       // throw new UnsupportedOperationException("TODO: BDD setup");
    }

    @Test
    void givenProspectWhenActivateThenSavedActive() {
        // TODO: given(repository.findById(...)).willReturn(...);
        Customer ravi = new Customer(
                "CUS-1002",
                "Ravi Singh",
                "ravi.singh@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.now()
        );

        given(repository.findById("CUS-1002"))
                .willReturn(Optional.of(ravi));

        given(repository.save(any(Customer.class)))
                .willAnswer(inv -> inv.getArgument(0));

        Customer updated = service.changeStatus(
                "CUS-1002",
                CustomerStatus.ACTIVE,
                "lab-request-001"
        );

        then(repository).should().findById("CUS-1002");
        then(repository).should().save(any(Customer.class));

        assertEquals(CustomerStatus.ACTIVE, updated.getStatus());
    }
        // then(repository).should().save(...);
        //throw new UnsupportedOperationException("TODO: BDDMockito given/then/should");
    }

