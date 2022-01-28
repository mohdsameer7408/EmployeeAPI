package com.example.employee_api.employee;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.employee_api.department.Department;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String salary;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    public Employee() {
    }

    public Employee(String name, String email, String salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public Employee(String name, String email, String salary, Integer departmentId) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = new Department(departmentId, "");
    }

    public Employee(int id, String name, String email, String salary, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = new Department(departmentId, "");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
