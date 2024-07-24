
package com.example.crudDemoSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crudDemoSpringBoot.dao.EmployeeDAO;
import com.example.crudDemoSpringBoot.dao.EmployeeRepository;
import com.example.crudDemoSpringBoot.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// private EmployeeDAO employeeDAO;
	//
	// @Autowired
	// public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
	// employeeDAO =} theEmployeeDAO;
	// }
	//
	// @Override
	// public List<Employee> findAll() {
	// return employeeDAO.findAll();
	// }
	//
	// @Override
	// @Transactional
	// public Employee findById(int theId) {
	// return employeeDAO.findById(theId);
	// }
	//
	// @Override
	// @Transactional
	// public Employee save(Employee theEmployee) {
	// return employeeDAO.save(theEmployee);
	// }
	//
	// @Override
	// @Transactional
	// public void deleteById(int theId) {
	// employeeDAO.deleteById(theId);
	//
	// }

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("not found this");
		}

		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		return employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);

	}
}
