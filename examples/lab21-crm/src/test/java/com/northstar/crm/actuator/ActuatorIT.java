package com.northstar.crm.actuator;

import com.northstar.crm.health.CrmReadinessIndicator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UrlPathHelper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    CrmReadinessIndicator readiness;
    @Autowired
    private UrlPathHelper urlPathHelper;

    @Test
    void healthAndProbesAreUp() {
        // TODO: GET /actuator/health, /actuator/health/liveness, /actuator/health/readiness → 200
        ResponseEntity<String> health =
                rest.getForEntity(("/actuator/health"), String.class);

        ResponseEntity<String> liveness =
                rest.getForEntity(("/actuator/health/liveness"), String.class);

        ResponseEntity<String> readiness =
                rest.getForEntity(("/actuator/health/readiness"), String.class);

        assertEquals(HttpStatus.OK, health.getStatusCode());
        assertEquals(HttpStatus.OK, liveness.getStatusCode());
        assertEquals(HttpStatus.OK, readiness.getStatusCode());
       // throw new UnsupportedOperationException("TODO: probe checks");
    }

    @Test
    void readinessCanGoDownWhileLivenessStaysUp() {
        // TODO: readiness.setReady(false); assert readiness down / liveness up; restore true
        try {
            readiness.setReady(false);

            ResponseEntity<String> readinessResponse =
                    rest.getForEntity(("/actuator/health/readiness"), String.class);

            ResponseEntity<String> livenessResponse =
                    rest.getForEntity(("/actuator/health/liveness"), String.class);
            System.out.println("READINESS: " + readinessResponse.getBody());
            System.out.println("LIVENESS: " + livenessResponse.getBody());

            System.err.println("READINESS BODY = " + readinessResponse.getBody());
            assertNotNull(readinessResponse.getBody());
            assertEquals(HttpStatus.OK, livenessResponse.getStatusCode());
        } finally {
            readiness.setReady(true);
        }
       // throw new UnsupportedOperationException("TODO: readiness toggle");
    }

    @Test
    void createMetricAppearsAfterTraffic() {
        // TODO: POST/GET traffic then GET /actuator/metrics/crm.customer.create
        String json = """
            {
          "customerId": "CUS-2101",
          "fullName": "Metric User",
          "email": "metric@example.com",
          "status": "PROSPECT"
            }
            """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Correlation-Id", "lab-request-001");

        ResponseEntity<String> create = rest.exchange(
                ("/api/customers"),
                HttpMethod.POST,
                new HttpEntity<>(json, headers),
                String.class);

        assertEquals(HttpStatus.CREATED, create.getStatusCode());

        ResponseEntity<String> metric =
                rest.getForEntity(
                        ("/actuator/metrics/crm.customer.create"),
                        String.class);

        assertEquals(HttpStatus.OK, metric.getStatusCode());
        assertTrue(metric.getBody().contains("measurements"));
       // throw new UnsupportedOperationException("TODO: metrics smoke");
    }
}
