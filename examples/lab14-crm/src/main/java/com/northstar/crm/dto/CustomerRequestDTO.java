package com.northstar.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerRequestDTO
{

    // TODO: @NotBlank + @Size(max=32) on customerId
    @NotBlank
    @Size(max = 32)
    private String customerId;
    // customerId = @NotBlank + @Size(max = 32);

    // TODO: @NotBlank + @Size(min=2, max=100) on fullName
    @NotBlank
    @Size(min= 2, max= 100 )
    private String fullName;

    // TODO: @NotBlank + @Email + @Size(max=254) on email
    @NotBlank
    @Email
    @Size(max= 254)
    private String email;

    // TODO: @NotBlank on status (ACTIVE / PROSPECT / ...)
    @NotBlank
    private String status;

    public CustomerRequestDTO() {}

    public CustomerRequestDTO(String customerId, String fullName, String email, String status) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
