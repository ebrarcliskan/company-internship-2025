package com.company.personnelservice.repository;

import com.company.personnelservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
            SELECT e FROM Employee e
            WHERE (:q IS NULL OR
                    lower(e.name) LIKE lower(concat('%', :q, '%')) OR 
                    lower(e.email) LIKE lower(concat('%', :q, '%')) OR
                    lower(e.department) LIKE lower(concat('%', :q, '%')))
             AND (:dept IS NULL OR lower(e.department) = lower(:dept))
             """)
    Page<Employee> search(@Param("q") String q,
                          @Param("dept") String dept,
                          Pageable pageable);

}
