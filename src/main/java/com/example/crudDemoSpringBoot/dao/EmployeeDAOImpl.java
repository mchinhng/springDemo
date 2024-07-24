
package com.example.crudDemoSpringBoot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.crudDemoSpringBoot.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// define field for entitymanger
	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		// create a query

		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

		// excute query and get result list
		List<Employee> employees = theQuery.getResultList();

		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// get employee
		Employee theEmployee = entityManager.find(Employee.class, theId);

		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		// save employee
		Employee dbEmployee = entityManager.merge(theEmployee);

		return dbEmployee;
	}

	@Override
	public void deleteById(int theId) {
		// find by id
		Employee theEmployee = entityManager.find(Employee.class, theId);

		// remove by id
		entityManager.remove(theEmployee);
	}

}
