package com.company.personnelservice.service.impl;

import com.company.personnelservice.dto.EmployeeDto;
import com.company.personnelservice.dto.EmployeeUpdateRequest;
import com.company.personnelservice.dto.EmployeeCreateRequest;
import com.company.personnelservice.dto.EmployeePatchRequest;
import com.company.personnelservice.entity.Employee;
import com.company.personnelservice.mapper.EmployeeMapper;
import com.company.personnelservice.repository.EmployeeRepository;
import com.company.personnelservice.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;
    private EmployeeMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public  EmployeeDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public EmployeeDto create(EmployeeCreateRequest req) {
        Employee employee = mapper.toEntity(req);
        return mapper.toDto(repository.save(employee));
    }

    @Override
    public EmployeeDto update(Long id, EmployeeUpdateRequest req) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        mapper.updateEntityFromDto(req, employee);
        return mapper.toDto(repository.save(employee));
    }

    @Override
    public EmployeeDto patch(Long id, EmployeePatchRequest req) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        mapper.patch(employee, req);
        return mapper.toDto(repository.save(employee));
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }

        repository.deleteById(id);
    }
}
