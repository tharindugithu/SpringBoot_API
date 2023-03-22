package com.project_1.project01.service;

import com.project_1.project01.entity.Department;
import com.project_1.project01.error.DepartmentNotFoundException;
import com.project_1.project01.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImplimentation implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDeps() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchByDepId(Long depId) throws DepartmentNotFoundException {

        Department d;

        Optional<Department> optional = departmentRepository.findById(depId);;
        if (!optional.isPresent()) {
           throw new DepartmentNotFoundException("Department Not Available");
        }
        d = optional.get();
        return d;
    }

    @Override
    public String deleteByDepId(Long depId) {
        departmentRepository.deleteById(depId);
        return "deleted";
    }

    @Override
    public Department updateDepById(Long depId, Department department) {
        Optional<Department> optional = departmentRepository.findById(depId);
        Department dep=null;
        if (optional.isPresent()){
            dep = optional.get();
            if(Objects.nonNull(dep.getDepartmentName()) && !"".equalsIgnoreCase(dep.getDepartmentName())){
                dep.setDepartmentName(department.getDepartmentName());
            }

            if(Objects.nonNull(dep.getDepartmentAddress()) && !"".equalsIgnoreCase(dep.getDepartmentAddress())){
                dep.setDepartmentAddress(department.getDepartmentAddress());
            }

            if(Objects.nonNull(dep.getDepartmentCode()) && !"".equalsIgnoreCase(dep.getDepartmentCode())){
                dep.setDepartmentCode(department.getDepartmentCode());
            }
            return departmentRepository.save(dep);
        }
        return null;



    }

    @Override
    public Department fetchByDepName(String depName) {
        //return departmentRepository.findByDepartmentName(depName);
        return departmentRepository.findByDepartmentNameIgnoreCase(depName);
    }
}
