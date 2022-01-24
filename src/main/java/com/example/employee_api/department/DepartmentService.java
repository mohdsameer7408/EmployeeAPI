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

    // private List<Department> departments = new
    // ArrayList<Department>(Arrays.asList(
    // new Department("1", "Backend"),
    // new Department("2", "Frontend"),
    // new Department("3", "DevOps"),
    // new Department("4", "QA")));

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        ;
        return departments;
    }

    public Department getDepartment(Integer id) {
        // return departments.stream().filter(department ->
        // department.getId().equals(id)).findFirst().get();
        return departmentRepository.findById(id)
                .orElseThrow(() -> new CustomIdNotFoundException("Invalid department id!"));
    }

    public Department addDepartment(Department department) {
        // departments.add(department);
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Integer id, Department modifiedDepartment) {
        // departments = departments.stream()
        // .map(department -> department.getId().equals(id) ? modifiedDepartment :
        // department)
        // .collect(Collectors.toList());
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new CustomIdNotFoundException("Invalid department id!"));
        department.setName(modifiedDepartment.getName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id) {
        // departments.removeIf(department -> department.getId().equals(id));
        boolean doesDeapartmentExists = departmentRepository.existsById(id);
        if (doesDeapartmentExists) {
            departmentRepository.deleteById(id);
            return;
        }

        throw new CustomIdNotFoundException("Invalid department id!");
    }

}
