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
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@Tag(name = "Employees", description = "Personel CRUD, arama, sayfalama, sıralama")
@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {

        this.service = service;
    }

    @Operation(
            summary = "Personel listesini getir (arama/sayfalama/sıralama)",
            description = "query ile isim/email/department içinde arar; department ile filtreler; pageable ile sayfa/sort kontrol edilir",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste döndü")
            }
    )

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(
            @Parameter(description = "Serbest metin araması (isim/email/department)")
            @RequestParam(required = false) String query,
            @Parameter(description = "Departmana göre filtre (örn: IT)")
            @RequestParam(required = false) String department,
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.search(query, department, pageable));
    }

    @Operation(summary = "ID ile personel getir")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(service.getById(id));

    }

    @Operation(summary = "Yeni personel oluştur")
    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeCreateRequest req) {

        return ResponseEntity.ok(service.create(req));
    }

    @Operation(summary = "Personel bilgilerini güncelle")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody EmployeeUpdateRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @Operation(summary = "Personel bilgilerini kısmı güncelle")
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> patch(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody EmployeePatchRequest req) {
        return ResponseEntity.ok(service.patch(id, req));
    }

    @Operation(summary = "Personeli sil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}




