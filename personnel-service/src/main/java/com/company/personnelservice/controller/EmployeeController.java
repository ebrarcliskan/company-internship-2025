package com.company.personnelservice.controller;

import com.company.personnelservice.dto.EmployeeDto;
import com.company.personnelservice.dto.EmployeeUpdateRequest;
import com.company.personnelservice.dto.EmployeeCreateRequest;
import com.company.personnelservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {

        this.service = service;
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeCreateRequest req) {

        return ResponseEntity.ok(service.create(req));
    }

}




