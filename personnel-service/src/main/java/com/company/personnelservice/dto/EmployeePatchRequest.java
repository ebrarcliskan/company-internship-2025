package com.company.personnelservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeePatchRequest(
        @Schema(example = "Hatice C.") String name,
        @Schema(example = "hatice.c@gmail.com") @Email String email,
        @Schema(example = "HR") String department
) {
}
