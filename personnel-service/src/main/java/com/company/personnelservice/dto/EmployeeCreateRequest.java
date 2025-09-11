package com.company.personnelservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeCreateRequest(

        @Schema(example = "Hatice Caliskan") @NotBlank String name,
        @Schema(example = "hatice@gmail.com") @Email String email,
        @Schema(example = "IT") @NotBlank String department

) {
}
