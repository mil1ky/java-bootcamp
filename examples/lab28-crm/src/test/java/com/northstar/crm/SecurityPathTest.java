package com.northstar.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityPathTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void missingTokenIs401() throws Exception {
    mockMvc.perform(
                    get("/api/customers/CUS-1001"))
            .andExpect(status().isUnauthorized());
  }

  @Test
  void agentCanReadCustomerButNotAdmin() throws Exception {
    String response = mockMvc.perform(
                    post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                {
                                  "username": "agent1",
                                  "password": "agent1"
                                }
                                """))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    String token = response
            .split("\"accessToken\":\"")[1]
            .split("\"")[0];

    mockMvc.perform(
                    get("/api/customers/CUS-1001")
                            .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());

    mockMvc.perform(
                    get("/api/admin/ping")
                            .header("Authorization", "Bearer " + token))
            .andExpect(status().isForbidden());
  }

  @Test
  void adminCanPing() throws Exception {
    String response = mockMvc.perform(
                    post("/api/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                {
                                  "username": "admin1",
                                  "password": "admin1"
                                }
                                """))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

    String token = response
            .split("\"accessToken\":\"")[1]
            .split("\"")[0];

    mockMvc.perform(
                    get("/api/admin/ping")
                            .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());
  }
}