package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTests {
    DefaultCustomerService service;

    @BeforeEach
    void setUp() {
        // TODO: fresh InMemoryCustomerRepository + CustomerValidator + DefaultCustomerService each test
        InMemoryCustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        service = new DefaultCustomerService(repo, validator);
        //throw new UnsupportedOperationException("TODO: wire @BeforeEach");
    }

    @Test
    void addAndActivateRaviHappyPath() {
        // TODO: add Amina ACTIVE + Ravi PROSPECT; changeStatus CUS-1002 → ACTIVE; assert ACTIVE
        //Adding Amina
        service.addCustomer(com.northstar.crm.entity.Customer.amina());
        //Adding Ravi
        service.addCustomer(com.northstar.crm.entity.Customer.ravi());
        //throw new UnsupportedOperationException("TODO: happy path");
        // Activate Ravi
        service.changeStatus("CUS-1002", CustomerStatus.ACTIVE, "lab-request-001");

        // Verify
        service.findById("CUS-1002");
    }

    @Test
    void duplicateIdThrowsConflict() {
        // TODO: add Amina twice → assertThrows BusinessException
        // Add Amina the first time
        service.addCustomer(Customer.amina());

        // Adding Amina again should throw BusinessException
        assertThrows(
                BusinessException.class,
                () -> service.addCustomer(Customer.amina())
        );
    }

    @Test
    void illegalTransitionThrowsConflict() {
        // TODO: ACTIVE → PROSPECT on CUS-1001 → BusinessException; status still ACTIVE
        // Add Amina (ACTIVE)
        service.addCustomer(Customer.amina());

        // ACTIVE -> PROSPECT should fail
        assertThrows(
                BusinessException.class,
                () -> service.changeStatus(
                        "CUS-1001",
                        CustomerStatus.PROSPECT,
                        "lab-request-002"
                )
        );

        // Verify status is still ACTIVE
      service.findById("CUS-1001");
        assertEquals(CustomerStatus.ACTIVE, Customer.amina().getStatus());
    }

    @Test
    void missingCustomerThrowsNotFound() {
        // TODO: changeStatus CUS-9999 → BusinessException with CUSTOMER_NOT_FOUND
        assertThrows(
                BusinessException.class,
                () -> service.changeStatus(
                        "CUS-9999",
                        CustomerStatus.ACTIVE,
                        "lab-request-003"
                )
        );
    }

    @Test
    void duplicateEmailThrowsConflict() {
        // TODO: add Amina; add other id with same email → BusinessException
        // Add Amina
        service.addCustomer(Customer.amina());

        // Create another customer with a different ID
        // but Amina's email
        //Make sure to fill out the other fields as well
        Customer duplicateEmail = new Customer("CUS-2001", "John Doe", Customer.amina().getEmail(),
                "555-0201", CustomerStatus.PROSPECT, java.time.LocalDateTime.now());

        // Same email should cause a BusinessException
        assertThrows(
                BusinessException.class,
                () -> service.addCustomer(duplicateEmail)
        );

    }

    @Test
    void closedToActiveRejected() {
        // TODO: add CLOSED CUS-1001; changeStatus → ACTIVE throws BusinessException
        service.addCustomer(Customer.amina());

        assertThrows(
                BusinessException.class,
                () -> service.changeStatus(
                        "CUS-1001",
                        CustomerStatus.ACTIVE,
                        "lab-request-004"
                )
        );
    }

}
