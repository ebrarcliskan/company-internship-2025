package com.company.personnelservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record EmployeeUpdateRequest(

        @NotBlank String name,
        @Email String email,
        @NotBlank String department
) {
}
