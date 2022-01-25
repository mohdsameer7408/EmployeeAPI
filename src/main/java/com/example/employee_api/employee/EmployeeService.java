package com.example.employee_api.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.employee_api.department.Department;
import com.example.employee_api.department.DepartmentRepository;
import com.example.employee_api.exception.CustomIdNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

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

    private List<Employee> readEmployeesFromCSV(MultipartFile file) throws IOException, ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String record;

        while ((record = bufferedReader.readLine()) != null) {
            String[] data = record.split(",");
            Department department = departmentRepository.findById(Integer.parseInt(data[3]))
                    .orElseThrow(() -> new CustomIdNotFoundException("Invalid department id!"));
            Employee employee = new Employee(data[0], data[1], data[2]);
            employee.setDepartment(department);
            employees.add(employee);
        }

        return employees;
    }

    public List<Employee> addEmployees(MultipartFile file) throws IOException, ParseException {
        List<Employee> employees = readEmployeesFromCSV(file);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (Employee employee : employees) {
            Runnable process = new Runnable() {
                @Override
                public void run() {
                    employeeRepository.save(employee);
                }
            };
            executorService.execute(process);
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
        }

        return employees;
    }
}
