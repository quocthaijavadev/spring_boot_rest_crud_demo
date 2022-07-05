package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.findEmployeeById(employeeId);
		return ResponseEntity.ok(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetail) throws ResourceNotFoundException {
		Employee employee = employeeService.updateEmployeeById(employeeId, employeeDetail);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/employee/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(name = "id") Long employeeId)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = employeeService.deleteEmployeeById(employeeId);
		return response;
	}
}
