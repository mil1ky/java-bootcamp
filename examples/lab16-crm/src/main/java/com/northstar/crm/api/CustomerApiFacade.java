package com.northstar.crm.api;

import com.northstar.crm.dto.CustomerMapper;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.BusinessException;
import com.northstar.crm.exception.GlobalExceptionHandler;
import com.northstar.crm.service.CustomerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class CustomerApiFacade {
    private final CustomerService service;
    private final Validator validator;
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    public CustomerApiFacade(CustomerService service) {
        this.service = service;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public ApiResult create(CustomerRequestDTO request, String correlationId) {
        // TODO: validate → Fail fromValidation; else addCustomer → Ok; catch BusinessException before Exception
        var violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return new ApiResult.Fail(handler.fromValidation(violations, correlationId));
        }
        try {
            var saved = service.addCustomer(CustomerMapper.toEntity(request));
            return new ApiResult.Ok(CustomerMapper.toResponse(saved));
        } catch (BusinessException ex) {
            return new ApiResult.Fail(handler.fromBusiness(ex));
        } catch (Exception ex) {
            return new ApiResult.Fail(handler.fromUnexpected(ex, correlationId));
        }
        //throw new UnsupportedOperationException("TODO: create → ApiResult");
    }

    public ApiResult getById(String customerId, String correlationId) {
        // TODO: findById → Ok DTO or BusinessException.notFound → Fail
        try {
            return service.findById(customerId)
                    .<ApiResult>map(c -> new ApiResult.Ok(CustomerMapper.toResponse(c)))
                    .orElseThrow(() -> BusinessException.notFound(customerId, correlationId));
        } catch (BusinessException ex) {
            return new ApiResult.Fail(handler.fromBusiness(ex));
        }
        //throw new UnsupportedOperationException("TODO: getById → ApiResult");
    }

    public ApiResult changeStatus(String customerId, CustomerStatus newStatus, String correlationId) {
        // TODO: service.changeStatus → Ok; catch BusinessException → Fail
        try {
            var updated = service.changeStatus(customerId, newStatus, correlationId);
            return new ApiResult.Ok(CustomerMapper.toResponse(updated));
        } catch (BusinessException ex) {
            return new ApiResult.Fail(handler.fromBusiness(ex));
        } catch (Exception ex) {
            return new ApiResult.Fail(handler.fromUnexpected(ex, correlationId));
        }
       // throw new UnsupportedOperationException("TODO: changeStatus → ApiResult");
    }
}
