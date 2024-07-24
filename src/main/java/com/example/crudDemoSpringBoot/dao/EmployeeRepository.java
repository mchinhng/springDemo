package com.example.crudDemoSpringBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudDemoSpringBoot.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
}
