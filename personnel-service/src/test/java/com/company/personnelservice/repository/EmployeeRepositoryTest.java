package com.company.personnelservice.repository;

import com.company.personnelservice.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repo;

    @Test
    void search_shouldFilterByQueryAndDepartment() {

        repo.save(new Employee(null, "Ahmet", "ahmet@ex.com", "IT"));
        repo.save(new Employee(null, "Elif",  "elif@ex.com",  "IT"));
        repo.save(new Employee(null, "Mehmet","mehmet@ex.com","HR"));


        Page<Employee> p1 = repo.search("ahm", null, PageRequest.of(0, 20));
        assertThat(p1.getTotalElements()).isEqualTo(1);


        Page<Employee> p2 = repo.search(null, "IT", PageRequest.of(0, 20));
        assertThat(p2.getTotalElements()).isEqualTo(2);


        Page<Employee> p3 = repo.search("eli", "IT", PageRequest.of(0, 20));
        assertThat(p3.getTotalElements()).isEqualTo(1);
        assertThat(p3.getContent().get(0).getName()).isEqualTo("Elif");
    }
}
