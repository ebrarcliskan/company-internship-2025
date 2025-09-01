package com.company.personnelservice.controller;

import com.company.personnelservice.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import com.company.personnelservice.entity.Employee;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee req) {

        Employee saved =employeeRepository.save(req);
        return ResponseEntity
                .created(URI.create("/api/employees/"+ saved.getId()))
                .body(saved);
    }

}




