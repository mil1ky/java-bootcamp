package com.northstar.crm;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.northstar.crm.model.Customer;
import com.northstar.crm.service.CustomerService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerMockMvcTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CustomerService customerService;

  @Test
  void getAmina_ok() throws Exception {
    Customer amina = new Customer(
        "CUS-1001",
        "Amina Khan",
        "amina.khan@example.com",
        "ACTIVE"
    );

    when(customerService.find("CUS-1001"))
        .thenReturn(Optional.of(amina));

    mockMvc.perform(
            get("/api/customers/CUS-1001")
                .header("X-Correlation-Id", "lab-request-001")
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("CUS-1001"))
        .andExpect(jsonPath("$.name").value("Amina Khan"));
  }

  @Test
  void getMissing_notFound() throws Exception {
    when(customerService.find("CUS-9999"))
        .thenReturn(Optional.empty());

    mockMvc.perform(
            get("/api/customers/CUS-9999")
        )
        .andExpect(status().isNotFound());
  }

  @Test
  void createMaya_created() throws Exception {
    Customer maya = new Customer(
        "CUS-1003",
        "Maya Chen",
        "maya@example.com",
        "PROSPECT"
    );

    when(customerService.create(any(Customer.class), anyString()))
        .thenReturn(maya);

    mockMvc.perform(
            post("/api/customers")
                .header("X-Correlation-Id", "lab-request-001")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "id": "CUS-1003",
                      "name": "Maya Chen",
                      "email": "maya@example.com",
                      "status": "PROSPECT"
                    }
                    """)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("CUS-1003"))
        .andExpect(header().string("Location", "/api/customers/CUS-1003"));
  }
}
