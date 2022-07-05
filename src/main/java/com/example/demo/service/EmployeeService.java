package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.IEmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee findEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this ID: " + employeeId));
		return employee;
	}

	public Employee updateEmployeeById(Long employeeId, Employee employeeDetail) throws ResourceNotFoundException {
		Employee employee = findEmployeeById(employeeId);
		employee.setFirstName(employeeDetail.getFirstName());
		employee.setLastName(employeeDetail.getLastName());
		employee.setEmailId(employeeDetail.getEmailId());
		return employeeRepository.save(employee);
	}

	public Map<String, Boolean> deleteEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = findEmployeeById(employeeId);
		Map<String, Boolean> response = new HashMap<>();
		employeeRepository.delete(employee);
		response.put("employeeDeleted", Boolean.TRUE);
		return response;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

}
