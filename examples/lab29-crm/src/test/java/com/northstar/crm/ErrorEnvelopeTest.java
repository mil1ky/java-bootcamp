package com.northstar.crm;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorEnvelopeTest {
  @Autowired
  private MockMvc mockMvc;

  private String loginAndGetToken() throws Exception {
    String loginBody = """
        {
          "username": "agent1",
          "password": "password"
        }
        """;

    MvcResult result = mockMvc.perform(post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(loginBody))
            .andExpect(status().isOk())
            .andReturn();

    String response = result.getResponse().getContentAsString();
    if (response.contains("\"token\"")) {
      return JsonPath.read(response, "$.token");
    }
    if (response.contains("\"accessToken\"")) {
      return JsonPath.read(response, "$.accessToken");
    }
    if (response.contains("\"jwt\"")) {
      return JsonPath.read(response, "$.jwt");
    }
    throw new IllegalStateException("Login response missing token field: " + response);
  }

  @Test
  void validationReturns400Envelope() throws Exception {
    String token = loginAndGetToken();

    mockMvc.perform(post("/api/customers")
                    .header("Authorization", "Bearer " + token)
                    .header("X-Correlation-Id", "lab-request-001")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                    {
                      "id": "",
                      "name": "",
                      "email": "bad",
                      "status": "ACTIVE"
                    }
                    """))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Bad Request"))
            .andExpect(jsonPath("$.correlationId").value("lab-request-001"))
            .andExpect(jsonPath("$.violations").isArray());
  }

  @Test
  void missingCustomerReturns404Envelope() throws Exception {
    String token = loginAndGetToken();

    mockMvc.perform(get("/api/customers/CUS-9999")
                    .header("Authorization", "Bearer " + token)
                    .header("X-Correlation-Id", "lab-request-404"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").value("Not Found"))
            .andExpect(jsonPath("$.correlationId").value("lab-request-404"))
            .andExpect(jsonPath("$.message").exists());
  }

  @Test
  void duplicateReturns409Envelope() throws Exception {
    String token = loginAndGetToken();

    String duplicateBody = """
        {
          "id": "CUS-1001",
          "name": "Northstar Labs",
          "email": "dup@example.com",
          "status": "ACTIVE"
        }
        """;

    mockMvc.perform(post("/api/customers")
                    .header("Authorization", "Bearer " + token)
                    .header("X-Correlation-Id", "lab-request-409")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(duplicateBody))
            .andExpect(status().isConflict())
            .andExpect(jsonPath("$.status").value(409))
            .andExpect(jsonPath("$.error").value("Conflict"))
            .andExpect(jsonPath("$.correlationId").value("lab-request-409"))
            .andExpect(jsonPath("$.message").exists());
  }

  @Test
  void securityStillRequiresToken() throws Exception {
    mockMvc.perform(get("/api/customers/CUS-1001")
                    .header("X-Correlation-Id", "lab-request-401"))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.status").value(401))
            .andExpect(jsonPath("$.error").value("Unauthorized"))
            .andExpect(jsonPath("$.correlationId").value("lab-request-401"));
  }
}
