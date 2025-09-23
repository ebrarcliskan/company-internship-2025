package com.company.personnelservice.service;

import com.company.personnelservice.dto.*;
import com.company.personnelservice.entity.Employee;
import com.company.personnelservice.exception.NotFoundException;
import com.company.personnelservice.mapper.EmployeeMapper;
import com.company.personnelservice.repository.EmployeeRepository;
import com.company.personnelservice.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;


public class EmployeeServiceImplTest {

    private final EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
    private final EmployeeMapper mapper = Mockito.mock(EmployeeMapper.class);
    private final EmployeeServiceImpl service = new EmployeeServiceImpl(repository, mapper);

    @Test
    void create_shouldSaveEmployee() {
        var req = new  EmployeeCreateRequest("Fatmanur","fatmanur@gmail.com", "IT");
        var entity = new Employee(1L, "Fatmanur", "fatmanur@gmail.com", "IT");

        Mockito.when(mapper.toEntity(req)).thenReturn(entity);
        Mockito.when(repository.save(any(Employee.class))).thenReturn(entity);
        Mockito.when(mapper.toDto(entity)).thenReturn(new EmployeeDto(1L, "Fatmanur", "fatmanur@gmail.com", "IT"));

        var result = service.create(req);
        assertThat(result.name()).isEqualTo("Fatmanur");
        assertThat(result.id()).isEqualTo(1L);
    }

    @Test
    void getById_whenNotFound_shouldThrow() {
        Mockito.when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Employee not found");
    }

    @Test
    void getById_whenExists_shouldReturnDto() {
        var entity = new Employee(1L, "Hatice", "hatice@gmail.com", "IT");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toDto(entity)).thenReturn(new EmployeeDto(1L, "Hatice", "hatice@gmail.com", "IT"));

        var dto = service.getById(1L);
        assertThat(dto.email()).isEqualTo("hatice@gmail.com");
    }

    @Test
    void update_whenNotFound_shouldThrow() {
        var req = new EmployeeUpdateRequest("New Name", "x@gmail.com", "HR");
        Mockito.when(repository.findById(42L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.update(42L, req))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void update_whenOk_shouldSaveReturnDto() {
        var req = new EmployeeUpdateRequest("New Name", "n@gmail.com", "HR");
        var entity = new Employee(5L, "Old", "o@gmail.com", "IT");
        var saved = new Employee(5L, "New Name", "n@gmail.com", "HR");

        Mockito.when(repository.findById(5L)).thenReturn(Optional.of(entity));
        Mockito.doAnswer(inv -> {entity.setName(req.name()); entity.setEmail(req.email()); entity.setDepartment(req.department()); return null;})
                .when(mapper).updateEntityFromDto(eq(req), any(Employee.class));
        Mockito.when(repository.save(entity)).thenReturn(saved);
        Mockito.when(mapper.toDto(saved)).thenReturn(new EmployeeDto(5L, "New Name", "n@gmail.com", "HR"));

        var dto = service.update(5L, req);
        assertThat(dto.name()).isEqualTo("New Name");
        assertThat(dto.department()).isEqualTo("HR");
    }

    @Test
    void patch_whenNotFound_shouldThrow() {
        var req = new EmployeePatchRequest("N", null, null);
        Mockito.when(repository.findById(7L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.patch(7L, req))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void patch_whenOk_shouldApplyChanges() {
        var req = new EmployeePatchRequest("Yeni", null, "HR");
        var entity = new Employee(2L, "Eski", "e@gmail.com", "IT");
        var saved = new Employee(2L, "Yeni", "e@gmail.com", "HR");


        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(entity));
        Mockito.doAnswer(inv -> { entity.setName(req.name()); entity.setDepartment(req.department()); return null; })
                .when(mapper).patch(any(Employee.class), eq(req));
        Mockito.when(repository.save(entity)).thenReturn(saved);
        Mockito.when(mapper.toDto(saved)).thenReturn(new EmployeeDto(2L, "Yeni", "e@gmail.com", "HR"));


        var dto = service.patch(2L, req);
        assertThat(dto.department()).isEqualTo("HR");
    }

    @Test
    void delete_whenNotFound_shouldThrow() {
        Mockito.when(repository.existsById(123L)).thenReturn(false);
        assertThatThrownBy(() -> service.delete(123L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void delete_whenExists_shouldDelete() {
        Mockito.when(repository.existsById(3L)).thenReturn(true);
        assertThatCode(() -> service.delete(3L)).doesNotThrowAnyException();
        Mockito.verify(repository).deleteById(3L);
    }

    @Test
    void search_shouldMapEntitiesToDto() {
        var entity = new Employee(10L, "Ali", "ali@c.com", "IT");
        Page<Employee> page = new PageImpl<>(List.of(entity), PageRequest.of(0,20), 1);
        Mockito.when(repository.search(null, null, PageRequest.of(0,20))).thenReturn(page);
        Mockito.when(mapper.toDto(entity)).thenReturn(new EmployeeDto(10L, "Ali", "ali@c.com", "IT"));


        var result = service.search(null, null, PageRequest.of(0,20));
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).name()).isEqualTo("Ali");
    }
}
