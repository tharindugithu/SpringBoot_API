package com.project_1.project01.service;

import com.project_1.project01.entity.Department;
import com.project_1.project01.error.DepartmentNotFoundException;
import com.project_1.project01.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @MockBean//will call this what is doing
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("IT").departmentCode("IT-01").departmentId(1L).departmentAddress("ULLALA").build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);//whenever call this method this department obj return
        Mockito.when(departmentRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(department));
    }

    @Test
    public void  whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "IT";
        Department found = departmentService.fetchByDepName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }
    @Test
    public void  whenValidDepartmentId_thenDepartmentShouldFound() throws DepartmentNotFoundException {
        Long departmentID = 1L;
        Department found = departmentService.fetchByDepId(departmentID);
        assertEquals(departmentID,found.getDepartmentId());
    }
}