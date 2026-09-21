package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;

public class Main {

    public static void main(String[] args) {
        CustomerService service = new CustomerService();

        Customer amina = service.createCustomer(
                "CUS-1001",
                "Amina Khan",
                "amina.khan@example.com",
                null,
                CustomerStatus.ACTIVE
        );

        Customer ravi = service.createCustomer(
                "CUS-1002",
                "Ravi Singh",
                "ravi.singh@example.com",
                null,
                CustomerStatus.PROSPECT
        );

        System.out.println("Created: " + amina);
        System.out.println("Created: " + ravi);

        System.out.println("Found: " + service.getCustomer("CUS-1001"));

        service.updateStatus("CUS-1002", CustomerStatus.ACTIVE);
        System.out.println("Updated Ravi: " + service.getCustomer("CUS-1002"));

        try {
            service.createCustomer(
                    "CUS-1001",
                    "Duplicate Customer",
                    "duplicate@example.com",
                    null,
                    CustomerStatus.PROSPECT
            );
        } catch (IllegalStateException e) {
            System.out.println("Duplicate failure: " + e.getMessage());
        }

        try {
            service.getCustomer("CUS-9999");
        } catch (IllegalArgumentException e) {
            System.out.println("Unknown customer failure: " + e.getMessage());
        }
    }
}

