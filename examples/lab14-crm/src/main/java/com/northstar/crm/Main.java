package com.northstar.crm;

import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.service.CustomerService;


public class Main {
    public static void main(String[] args) {
        CustomerApiFacade api = new CustomerApiFacade(new CustomerService());
        // TODO: create CUS-1001 / CUS-1002 via DTOs; print CustomerResponseDTO only
//        api. amina = new CustomerRequestDTO( "CUS-1001", "Amina Khan", "amina@example.com", "555-1001", "ACTIVE" );
//
//        // TODO: attempt invalid email; show correlation lab-request-001 in failure
//        CustomerRequestDTO ravi = new CustomerRequestDTO( "CUS-1002", "Ravi Singh", "ravi@example.com", "555-1002", "PROSPECT" );
        throw new UnsupportedOperationException("TODO: DTO facade demo");
    }
}
