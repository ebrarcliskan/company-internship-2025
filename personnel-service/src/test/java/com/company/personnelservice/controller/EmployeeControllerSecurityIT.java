package com.company.personnelservice.controller;

import com.company.personnelservice.PersonnelServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = PersonnelServiceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EmployeeControllerSecurityIT {

    @Autowired
    MockMvc mvc;


    @Test
    void post_withoutAuth_shouldReturn401() throws Exception {
        String body = "{\"name\":\"Ali\",\"email\":\"a@a.com\",\"department\":\"IT\"}";
        mvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isUnauthorized());
    }
}
