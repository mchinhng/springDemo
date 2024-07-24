
package com.example.crudDemoSpringBoot.dao;

import java.util.List;

import com.example.crudDemoSpringBoot.entity.Employee;

public interface EmployeeDAO {

	List<Employee> findAll();

	Employee findById(int theId);

	Employee save(Employee theEmployee);

	void deleteById(int theId);
}
