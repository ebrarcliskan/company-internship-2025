package com.company.personnelservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeCreateRequest(

        @NotBlank String name,
        @Email String email,
        @NotBlank String department

) {
}
