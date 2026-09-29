package com.northstar.crm.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void mapsNotFoundTo404() {
        ErrorResponse r = handler.fromBusiness(BusinessException.notFound("CUS-9999", "lab-request-001"));
        assertEquals(404, r.getStatus());
        assertEquals("CUSTOMER_NOT_FOUND", r.getError());
        assertEquals("lab-request-001", r.getCorrelationId());
        var handler = new GlobalExceptionHandler();
        var err = handler.fromBusiness(
                BusinessException.notFound("CUS-9999", "lab-request-001"));
        assertEquals(404, err.getStatus());
        assertEquals("lab-request-001", err.getCorrelationId());
    }

    @Test
    void mapsConflictTo409() {
        ErrorResponse r = handler.fromBusiness(BusinessException.conflict("duplicate email", "lab-request-001"));
        assertEquals(409, r.getStatus());
        assertEquals("BUSINESS_CONFLICT", r.getError());
        var err = handler.fromBusiness(
                BusinessException.conflict("illegal status transition ACTIVE -> PROSPECT", "lab-request-001"));
        assertEquals(409, err.getStatus());
    }

    @Test
    void unexpectedIsGeneric500() {
        ErrorResponse r = handler.fromUnexpected(new IllegalStateException("SECRET_KEY=abc123\nstack trace"), "lab-request-001");
        assertEquals(500, r.getStatus());
        assertEquals("INTERNAL_ERROR", r.getError());
        assertEquals("Unexpected server error", r.getMessage());
        assertFalse(r.getMessage().contains("SECRET_KEY"));
        assertFalse(r.getMessage().contains("stack"));
        assertEquals("lab-request-001", r.getCorrelationId());
    }
}
