package com.example.employee_api.department;

import java.util.ArrayList;
import java.util.List;

import com.example.employee_api.exception.CustomIdNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        ;
        return departments;
    }

    public Department getDepartment(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new CustomIdNotFoundException("Invalid department id!"));
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Integer id, Department modifiedDepartment) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new CustomIdNotFoundException("Invalid department id!"));
        department.setName(modifiedDepartment.getName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id) {
        boolean doesDeapartmentExists = departmentRepository.existsById(id);
        if (doesDeapartmentExists) {
            departmentRepository.deleteById(id);
            return;
        }

        throw new CustomIdNotFoundException("Invalid department id!");
    }

}
