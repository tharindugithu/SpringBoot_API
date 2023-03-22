package com.project_1.project01.controller;

import com.project_1.project01.entity.Department;
import com.project_1.project01.error.DepartmentNotFoundException;
import com.project_1.project01.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class) //check the end points,this particular class only mock context create
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean//departmentcontroller calling departmentservice layer ,we need to mock it
    private DepartmentService departmentService;

    private Department outputDepartment;
    @BeforeEach
    void setUp() {
        outputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-01")
                .departmentId(1L)
                .departmentAddress("ULLALA")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-01")
                .departmentAddress("ULLALA")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(outputDepartment);
        mockMvc.perform(post("/deps")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"IT\",\n" +
                        "\t\"departmentAddress\":\"ULLALA\",\n" +
                        "\t\"departmentCode\":\"IT-01\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchByDepId() throws Exception {
        Mockito.when(departmentService.fetchByDepId(1L))
                .thenReturn(outputDepartment);

        mockMvc.perform(get("/deps/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").
                        value(outputDepartment.getDepartmentName()));
    }
}