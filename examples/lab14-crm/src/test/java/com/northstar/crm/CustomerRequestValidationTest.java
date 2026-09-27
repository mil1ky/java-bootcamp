package com.northstar.crm;

import com.northstar.crm.dto.CustomerRequestDTO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.northstar.crm.entity.Customer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRequestValidationTest {
    static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validAminaRequestPasses()
    {
        // TODO: build valid DTO for CUS-1001; assert violations empty
        CustomerRequestDTO cutomerDto = new CustomerRequestDTO();
        cutomerDto.setCustomerId("CUS-1001"); // Assigning customer ID
        cutomerDto.setFullName("Amina Khan"); // Assigning full name
        cutomerDto.setEmail("amina.Khan@example.com"); // Assigning email
        cutomerDto.setStatus("ACTIVE"); // Assigning status
        assertTrue(validator.validate(cutomerDto).isEmpty());
        //throw new UnsupportedOperationException("TODO: valid request");
    }

    @Test
    void invalidEmailFails()
    {
        // TODO: bad email → assert violations mention email
        CustomerRequestDTO cutomerDto = validTemplate();
        cutomerDto.setEmail("not-an-email");    // Assigning invalid email
        assertFalse(validator.validate(cutomerDto).isEmpty());
        //throw new UnsupportedOperationException("TODO: invalid email");
    }

    @Test
    void blankNameFails() {
        // TODO: blank fullName → assert violation
        CustomerRequestDTO cutomerDto = validTemplate();
        cutomerDto.setFullName(" ");    // Assigning blank full name
        assertFalse(validator.validate(cutomerDto).isEmpty());
        //throw new UnsupportedOperationException("TODO: blank name");
    }

    private CustomerRequestDTO validTemplate() {
        CustomerRequestDTO dto = new CustomerRequestDTO();

        dto.setCustomerId("CUS-1001");
        dto.setFullName("Amina Khan");
        dto.setEmail("amina.khan@example.com");
        dto.setStatus("ACTIVE");

        return dto;
    }
}
