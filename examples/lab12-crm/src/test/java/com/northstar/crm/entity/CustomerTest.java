package com.northstar.crm.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {

    @Test
    void twoCustomersWithSameIdAreEqual() {
        Customer c1 = new Customer(
                "CUS-1001",
                "Amina Khan",
                "amina@example.com",
                "555-1234",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );

        Customer c2 = new Customer(
                "CUS-1001",
                "Different Name",
                "other@example.com",
                "555-0000",
                CustomerStatus.PROSPECT,
                LocalDateTime.now().minusDays(1)
        );

        assertEquals(c1, c2);
    }

    @Test
    void toStringContainsCustomerId() {
        Customer c = new Customer(
                "CUS-2002",
                "Name",
                "n@example.com",
                "555-9999",
                CustomerStatus.SUSPENDED,
                LocalDateTime.now()
        );

        String result = c.toString();

        assertTrue(result.contains("CUS-2002"));
    }
}