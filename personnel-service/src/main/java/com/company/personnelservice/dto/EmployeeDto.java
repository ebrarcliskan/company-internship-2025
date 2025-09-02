package com.company.personnelservice.dto;

public record EmployeeDto(

        Long id,
        String name,
        String email,
        String department
) {
}
