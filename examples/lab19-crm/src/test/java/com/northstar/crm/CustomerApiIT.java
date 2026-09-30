package com.northstar.crm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerApiIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void getAmina_returns200() throws Exception {
    mockMvc.perform(get("/api/customers/CUS-1001")
                    .header("X-Correlation-Id", "lab-request-001"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("CUS-1001"))
            .andExpect(jsonPath("$.name").value("Amina Khan"));
  }

  @Test
  void getMissing_returns404() throws Exception {
    mockMvc.perform(get("/api/customers/CUS-9999")
                    .header("X-Correlation-Id", "lab-request-001"))
            .andExpect(status().isNotFound());
  }

  @Test
  void create_returns201() throws Exception {
    String json = """
                {
                  "id": "CUS-1003",
                  "name": "Maya Chen",
                  "email": "maya.chen@example.com",
                  "status": "PROSPECT"
                }
                """;

    mockMvc.perform(post("/api/customers")
                    .header("X-Correlation-Id", "lab-request-001")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/customers/CUS-1003"));
  }
}