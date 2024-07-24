
package com.example.crudDemoSpringBoot.service;

import java.util.List;

import com.example.crudDemoSpringBoot.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(int theId);

	Employee save(Employee theEmployee);

	void deleteById(int theId);
}
