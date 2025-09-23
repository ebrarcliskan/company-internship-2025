package com.company.personnelservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record EmployeeUpdateRequest(

        @Schema(example = "Hatice Caliskan") @NotBlank String name,
        @Schema(example = "hatice@gmail.com") @Email String email,
        @Schema(example = "IT") @NotBlank String department
) {
}
