package com.employeerestservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeerestservice.model.Department;
import com.employeerestservice.model.Employee;
import com.employeerestservice.repository.DepartmentRepository;
import com.employeerestservice.repository.employeeRepository;

@RestController
public class EmployeeRepoController {

	@Autowired
	employeeRepository employeeRepo;

	@Autowired
	DepartmentRepository departmentRepo;

	@GetMapping("/jpa/employees")
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	@GetMapping("/jpa/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		// Use findById method of the repository to get an employee by ID
		// Returns an Optional<Employee>, handle it appropriately
		Optional<Employee> optionalEmployee = employeeRepo.findById(id);

		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/jpa/employee/create")
	public ResponseEntity<Employee> SaveEmployee(@RequestBody Employee emp) {
		Employee employee = employeeRepo.save(emp);
		// Return the saved employee with HTTP status CREATED
		return new ResponseEntity<>(employee, HttpStatus.CREATED);

	}

	@PostMapping("/jpa/adddepartment/{empId}")
	public ResponseEntity<Employee> addDepartment(@PathVariable long empId, @RequestBody Department department) {
		Employee employee = employeeRepo.findById(empId).get();

		if (null == employee) {
			System.out.println("Employee not found");
		}

		department.setEmployee(employee);
		departmentRepo.save(department);

		// Return the saved employee with HTTP status CREATED
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

}
