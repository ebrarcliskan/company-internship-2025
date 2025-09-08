package com.company.personnelservice.controller;

import com.company.personnelservice.dto.EmployeeDto;
import com.company.personnelservice.dto.EmployeeUpdateRequest;
import com.company.personnelservice.dto.EmployeeCreateRequest;
import com.company.personnelservice.dto.EmployeePatchRequest;
import com.company.personnelservice.service.EmployeeService;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {

        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String department,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.search(query, department, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeCreateRequest req) {

        return ResponseEntity.ok(service.create(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody EmployeeUpdateRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> patch(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody EmployeePatchRequest req) {
        return ResponseEntity.ok(service.patch(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}




