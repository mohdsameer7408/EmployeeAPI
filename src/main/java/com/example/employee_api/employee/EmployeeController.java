package com.example.employee_api.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.example.employee_api.department.Department;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public static Logger logger = LogManager.getLogger(EmployeeController.class);

    @RequestMapping(method = RequestMethod.POST, value = "/employees", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public void addEmployees(@RequestParam MultipartFile[] files) throws IOException, ParseException {
        logger.info("Creating Employees from CSV: ");
        for (MultipartFile file : files) {
            employeeService.addEmployees(file);
        }
    }

    @RequestMapping("/departments/{departmentId}/employees")
    @Cacheable(value = "employees", key = "#departmentId")
    public List<Employee> getAllEmployees(@PathVariable Integer departmentId) {
        logger.info("Retrieving all employees with departmentId: " + departmentId);
        return employeeService.getAllEmployees(departmentId);
    }

    @RequestMapping("/employees/{id}")
    @Cacheable(value = "employee", key = "#id")
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
    @CacheEvict(value = "employee", key = "#id")
    public Employee updateEmployee(@PathVariable Integer departmentId, @PathVariable Integer id,
            @RequestBody Employee employee) {
        logger.info("Updating an employee having id: " + id + " with departmentId: " + departmentId);
        employee.setDepartment(new Department(departmentId, ""));
        return employeeService.updateEmployee(id, employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
    @CacheEvict(value = "employee", allEntries = false, key = "#id")
    public void deleteEmployee(@PathVariable Integer id) {
        logger.info("Deleting an employee having id: " + id);
        employeeService.deleteEmployee(id);
    }

}
