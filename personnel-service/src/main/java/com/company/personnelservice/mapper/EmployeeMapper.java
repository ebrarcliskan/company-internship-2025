package com.company.personnelservice.mapper;

import com.company.personnelservice.dto.EmployeeCreateRequest;
import com.company.personnelservice.dto.EmployeeDto;
import com.company.personnelservice.dto.EmployeeUpdateRequest;
import com.company.personnelservice.dto.EmployeePatchRequest;
import com.company.personnelservice.entity.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    @Mappings({
            @Mapping(target ="id", ignore = true)
    })

    Employee toEntity(EmployeeCreateRequest req);

    void update(@MappingTarget Employee entity, EmployeeUpdateRequest req);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(@MappingTarget Employee entity, EmployeePatchRequest req);

    void updateEntityFromDto(EmployeeUpdateRequest req,@MappingTarget Employee entity);

}
