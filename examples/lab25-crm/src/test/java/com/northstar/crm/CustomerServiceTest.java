package com.northstar.crm;

import com.northstar.crm.model.Customer;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
  @Test
  void getSeededCus1001() {
    // TODO: new CustomerService(new InMemoryCustomerRepository()); assert get("CUS-1001") name Amina Khan
    CustomerService service =
            new CustomerService(
                    new InMemoryCustomerRepository()
            );

    Customer amina = service.get("CUS-1001");

    assertEquals("CUS-1001", amina.getId());
    assertEquals("Amina Khan", amina.getName());
    //fail("TODO");
  }

  @Test
  void duplicateCreateRejected() {
    // TODO: create(Customer.amina(), …) twice → IllegalStateException
    CustomerService service =
            new CustomerService(
                    new InMemoryCustomerRepository()
            );

    assertThrows(
            IllegalStateException.class,
            () -> service.create(
                    Customer.amina(),
                    "lab-request-001"
            )
    );
   // fail("TODO");
  }
}
