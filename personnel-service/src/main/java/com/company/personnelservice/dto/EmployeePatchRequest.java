package com.company.personnelservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeePatchRequest(
        String name,
        @Email String email,
        String department
) {
}
