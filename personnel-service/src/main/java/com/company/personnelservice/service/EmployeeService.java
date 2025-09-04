package com.company.personnelservice.service;

import com.company.personnelservice.dto.EmployeeCreateRequest;
import com.company.personnelservice.dto.EmployeeDto;
import com.company.personnelservice.dto.EmployeePatchRequest;
import com.company.personnelservice.dto.EmployeeUpdateRequest;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getAll();
    EmployeeDto getById(Long id);
    EmployeeDto create(EmployeeCreateRequest req);
    EmployeeDto update(Long id, EmployeeUpdateRequest req);
    EmployeeDto patch(Long id, EmployeePatchRequest req);
    void delete(Long id);
}
