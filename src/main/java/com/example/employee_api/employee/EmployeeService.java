package com.example.employee_api.employee;

import java.util.ArrayList;
import java.util.List;

import com.example.employee_api.exception.CustomIdNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(Integer departmentId) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByDepartmentId(departmentId).forEach(employees::add);
        return employees;
    }

    public Employee getEmployee(Integer id) {
        boolean doesEmployeeExists = employeeRepository.existsById(id);
        if (!doesEmployeeExists)
            throw new CustomIdNotFoundException("Invalid employee id!");

        return employeeRepository.findById(id).get();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee modifiedEmployee) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setName(modifiedEmployee.getName());
        employee.setEmail(modifiedEmployee.getEmail());
        employee.setSalary(modifiedEmployee.getSalary());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        boolean doesEmployeeExists = employeeRepository.existsById(id);
        if (!doesEmployeeExists)
            throw new CustomIdNotFoundException("Invalid employee id!");

        employeeRepository.deleteById(id);
    }
}
