package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerServiceTest {

    @Test
    void createAminaKhanThenGetById() {
        CustomerService svc = new CustomerService();

        Customer created = svc.createCustomer(
                "CUS-1001",
                "Amina Khan",
                "amina.khan@example.com",
                null,
                CustomerStatus.ACTIVE
        );

        assertEquals("CUS-1001", created.getCustomerId());
        assertEquals(CustomerStatus.ACTIVE, created.getStatus());
        assertEquals(
                "Amina Khan",
                svc.getCustomer("CUS-1001").getFullName()
        );
    }

    @Test
    void createRaviProspectThenActivate() {
        CustomerService svc = new CustomerService();

        Customer created = svc.createCustomer(
                "CUS-1002",
                "Ravi Singh",
                "ravi.singh@example.com",
                null,
                CustomerStatus.PROSPECT
        );

        assertEquals(CustomerStatus.PROSPECT, created.getStatus());

        Customer updated = svc.updateStatus(
                "CUS-1002",
                CustomerStatus.ACTIVE
        );

        assertEquals(CustomerStatus.ACTIVE, updated.getStatus());
        assertEquals(
                CustomerStatus.ACTIVE,
                svc.getCustomer("CUS-1002").getStatus()
        );
    }

    @Test
    void duplicateIdThrows() {
        CustomerService svc = new CustomerService();

        svc.createCustomer(
                "CUS-1002",
                "Ravi Singh",
                "ravi.singh@example.com",
                null,
                CustomerStatus.PROSPECT
        );

        assertThrows(
                IllegalStateException.class,
                () -> svc.createCustomer(
                        "CUS-1002",
                        "Other",
                        "x@example.com",
                        null,
                        CustomerStatus.PROSPECT
                )
        );
    }

    @Test
    void unknownIdThrows() {
        CustomerService svc = new CustomerService();

        assertThrows(
                IllegalArgumentException.class,
                () -> svc.getCustomer("CUS-9999")
        );
    }

    @Test
    void blankCustomerIdThrows() {
        CustomerService svc = new CustomerService();

        assertThrows(
                IllegalArgumentException.class,
                () -> svc.createCustomer(
                        "   ",
                        "Amina Khan",
                        "amina.khan@example.com",
                        null,
                        CustomerStatus.ACTIVE
                )
        );
    }

    @Test
    void updateUnknownThrowsWithCorrelation() {
        CustomerService svc = new CustomerService();
        svc.setCorrelationId("lab-request-001");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> svc.updateStatus(
                        "CUS-9999",
                        CustomerStatus.ACTIVE
                )
        );

        assertEquals(
                "Customer not found: CUS-9999 correlationId=lab-request-001",
                exception.getMessage()
        );
    }
}

