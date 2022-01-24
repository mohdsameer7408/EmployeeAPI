package com.example.employee_api.employee;

import java.util.List;

import com.example.employee_api.department.Department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public static Logger logger = LogManager.getLogger(EmployeeController.class);

    @RequestMapping("/departments/{departmentId}/employees")
    public List<Employee> getAllEmployees(@PathVariable Integer departmentId) {
        logger.info("Retrieving all employees with departmentId: " + departmentId);
        return employeeService.getAllEmployees(departmentId);
    }

    @RequestMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        logger.info("Retrieving Employee with id: " + id);
        return employeeService.getEmployee(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/departments/{departmentId}/employees")
    public Employee addEmployee(@PathVariable Integer departmentId, @RequestBody Employee employee) {
        logger.info("Creating an employee with departmentId: " + departmentId);
        employee.setDepartment(new Department(departmentId, ""));
        return employeeService.addEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/departments/{departmentId}/employees/{id}")
    public Employee updateEmployee(@PathVariable Integer departmentId, @PathVariable Integer id,
            @RequestBody Employee employee) {
        logger.info("Updating an employee having id: " + id + " with departmentId: " + departmentId);
        employee.setDepartment(new Department(departmentId, ""));
        return employeeService.updateEmployee(id, employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        logger.info("Deleting an employee having id: " + id);
        employeeService.deleteEmployee(id);
    }

}
