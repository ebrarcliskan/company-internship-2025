package com.company.personnelservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmployeeDto(

        @Schema(example ="1") Long id,
        @Schema(example = "Hatice Caliskan") String name,
        @Schema(example = "hatice@gmail.com") String email,
        @Schema(example = "IT") String department
) {
}
