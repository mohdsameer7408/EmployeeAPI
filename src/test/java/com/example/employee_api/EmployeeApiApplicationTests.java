package com.example.employee_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;

import com.example.employee_api.department.Department;
import com.example.employee_api.department.DepartmentRepository;
import com.example.employee_api.department.DepartmentService;
import com.example.employee_api.employee.Employee;
import com.example.employee_api.employee.EmployeeRepository;
import com.example.employee_api.employee.EmployeeService;
import com.example.employee_api.exception.CustomIdNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class EmployeeApiApplicationTests {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private DepartmentRepository departmentRepository;

	@MockBean
	private EmployeeRepository employeeRepository;

	Department department1 = new Department(1, "Frontend");
	Department department2 = new Department(2, "Backend");

	Employee employee1 = new Employee(1, "John", "john@gmail.com", "20000", 1);
	Employee employee2 = new Employee(2, "Bob", "Bob@gmail.com", "40000", 1);

	@Test
	void testGetAllDepartments() {
		when(departmentRepository.findAll())
				.thenReturn(Stream.of(department1, department2).collect(Collectors.toList()));
		assertEquals(2, departmentService.getAllDepartments().size());
	}

	@Test
	void testGetDepartment() throws CustomIdNotFoundException {
		when(departmentRepository.findById(department1.getId())).thenReturn(Optional.of(department1));
		assertNotNull(departmentService.getDepartment(department1.getId()));
	}

	@Test
	void testAddDepartment() {
		when(departmentRepository.save(department1)).thenReturn(department1);
		assertEquals(department1, departmentService.addDepartment(department1));
	}

	@Test
	void testUpdateDepartment() {
		when(departmentRepository.findById(department1.getId())).thenReturn(Optional.of(department1));
		when(departmentRepository.save(department1)).thenReturn(department1);
		assertEquals(department1, departmentService.updateDepartment(department1.getId(), department1));
	}

	@Test
	void testDeleteDepartment() {
		when(departmentRepository.existsById(department1.getId())).thenReturn(true);
		doNothing().when(departmentRepository).deleteById(department1.getId());
		departmentService.deleteDepartment(department1.getId());
		verify(departmentRepository, times(1)).deleteById(department1.getId());
	}

	@Test
	void testGetAllEmployeesByDepartmentId() {
		when(employeeRepository.findByDepartmentId(department1.getId()))
				.thenReturn(Stream.of(employee1, employee2).collect(Collectors.toList()));
		assertEquals(2, employeeService.getAllEmployees(department1.getId()).size());
	}

	@Test
	void testGetEmployee() throws Exception {
		when(employeeRepository.existsById(employee1.getId()))
				.thenReturn(true);
		when(employeeRepository.findById(employee1.getId()))
				.thenReturn(Optional.of(employee1));
		assertNotNull(employeeService.getEmployee(employee1.getId()));
	}

	@Test
	void testAddEmployee() {
		when(employeeRepository.save(employee1)).thenReturn(employee1);
		assertEquals(employee1, employeeService.addEmployee(employee1));
	}

	@Test
	void testUpdateEmployee() {
		when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.of(employee1));
		when(employeeRepository.save(employee1)).thenReturn(employee1);
		assertEquals(employee1, employeeService.updateEmployee(employee1.getId(), employee1));
	}

	@Test
	void testDeleteEmployee() {
		when(employeeRepository.existsById(employee1.getId())).thenReturn(true);
		doNothing().when(employeeRepository).deleteById(employee1.getId());
		employeeService.deleteEmployee(employee1.getId());
		verify(employeeRepository, times(1)).deleteById(employee1.getId());
	}

}
