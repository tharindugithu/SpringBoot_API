package com.project_1.project01.controller;

import com.project_1.project01.Test;
import com.project_1.project01.entity.Department;
import com.project_1.project01.error.DepartmentNotFoundException;
import com.project_1.project01.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    public Test test;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/deps")
    public Department saveDepartment(@Valid @RequestBody Department department){
      LOGGER.info("Inside saveDepartmentList of DepartmentController");//maintain logs
      return departmentService.saveDepartment(department);
    }

    @GetMapping("/deps")
    public List<Department> fetchAllDeps(){
        test.printName();
        return departmentService.fetchAllDeps();
    }

    @GetMapping("/deps/{id}")
    public Department fetchByDepId(@PathVariable("id") Long depId) throws DepartmentNotFoundException {
        return departmentService.fetchByDepId(depId);
    }

    @DeleteMapping("/deps/{id}")
    public String deleteByDepId(@PathVariable("id") Long depId){
        return departmentService.deleteByDepId(depId);
    }

    @PutMapping("deps/{id}")
    public Department updateDepById(@RequestBody Department department,@PathVariable("id") Long depId){
        return departmentService.updateDepById(depId,department);
    }

    @GetMapping("deps/name/{name}")
    public Department fetchDepByName(@PathVariable("name") String depName){
        return departmentService.fetchByDepName(depName);
    }

}
