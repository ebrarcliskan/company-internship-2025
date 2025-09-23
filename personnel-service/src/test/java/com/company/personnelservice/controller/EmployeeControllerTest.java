package com.company.personnelservice.controller;

import com.company.personnelservice.dto.*;
import com.company.personnelservice.exception.GlobalExceptionHandler;
import com.company.personnelservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    EmployeeService service;

    @Test
    void getAll_shouldReturnPageOfDtos() throws Exception {
        var dto = new EmployeeDto(1L, "Hatice", "h@gmail.com", "IT");
        Page<EmployeeDto> page = new PageImpl<>(List.of(dto), PageRequest.of(0, 20), 1);
        Mockito.when(service.search(null, null, PageRequest.of(0, 20))).thenReturn(page);

        mvc.perform(get("/api/employees")
                .param("page","0").param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Hatice"));
    }

    @Test
    void getById_whenNotFound_shouldReturn404() throws Exception {
        Mockito.when(service.getById(99L)).thenThrow(new com.company.personnelservice.exception.NotFoundException("Employee not found"));

        mvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.error", is("Not Found")));
    }

    @Test
    void create_whenValidationFails_shouldReturn400() throws Exception {

        String body = "{" +
                "\"name\":\"\"," +
                "\"email\":\"wrong\"," +
                "\"department\":\"\"}";

        mvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", containsString("Validation")));

    }
}




















