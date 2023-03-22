package com.project_1.project01.service;

import com.project_1.project01.entity.Department;
import com.project_1.project01.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchAllDeps();

    public Department fetchByDepId(Long depId) throws DepartmentNotFoundException;

    public String deleteByDepId(Long depId);

    public Department updateDepById(Long depId, Department department);

    public Department fetchByDepName(String depName);
}
