package com.example.employee_api.department;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static Logger logger = LogManager.getLogger(DepartmentController.class);

    @GetMapping
    @Cacheable(value = "departments")
    public List<Department> getAllDepartments() {
        logger.info("Retrieving all departments");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "departments", key = "#id")
    public Department getDepartment(@PathVariable Integer id) {
        logger.info("Retrieving Department with id: " + id);
        return departmentService.getDepartment(id);
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department department) {
        logger.info("Creating a department");
        return departmentService.addDepartment(department);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "departments", key = "#id")
    public Department updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        logger.info("Updating a department with id: " + id);
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "departments", allEntries = false, key = "#id")
    public void deleteDepartment(@PathVariable Integer id) {
        logger.info("Deleting a department with id: " + id);
        departmentService.deleteDepartment(id);
    }
}
