package com.example.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entity.Address;
import com.example.entity.Subject;
import com.example.entity.User;
import com.example.repository.AddressRepository;
import com.example.repository.SubjectRepository;
import com.example.repository.UserRepository;
import com.example.request.CreateSubjectRequest;
import com.example.request.CreateUserRequest;
import com.example.request.UpdateUserRequest;
import com.example.response.UserResponse;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//	public User createUser(CreateUserRequest createUserRequest) {
//		User user = new User(createUserRequest);
//		user = userRepository.save(user);
//		return user;
//	}
	
	public User createUser(CreateUserRequest createUserRequest) {
		User user = new User(createUserRequest);
		Address address = new Address();
		address.setStreet(createUserRequest.getStreet());
		address.setCity(createUserRequest.getCity());
		
		address = addressRepository.save(address);
		
		user.setAddress(address);
		user = userRepository.save(user);
		List<Subject> subjectsList = new ArrayList<Subject>();
		if(createUserRequest.getSubjectsLearning() != null) {
			for(CreateSubjectRequest createSubjectRequest : createUserRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarks(createSubjectRequest.getMarks());
				subject.setUser(user);
				subjectsList.add(subject);
			}
			subjectRepository.saveAll(subjectsList);
		}
		user.setLearningSubjects(subjectsList);
		System.out.println("ddd" + user);
		return user;
	}

	public User updateUser(UpdateUserRequest updateUserRequest) {
		User user = userRepository.findById(updateUserRequest.getId()).get();
		// todo

		user = userRepository.save(user);
		return user;
	}

	public String deleteUser(long id) {
		userRepository.deleteById(id);
		return "User has been deleted successfully";
	}

	public List<User> getByName(String name) {
		return userRepository.findByName(name);
	}

	public User getByNameAndEmail(String name, String email) {
		return userRepository.getByNameAndEmail(name, email);
	}
	
	public List<User> getAllUsersWithPagination (int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		return userRepository.findAll(pageable).getContent();
	}
	
	public List<User> getAllUsersWithSorting () {
		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		
		return userRepository.findAll(sort);
	}
	
	public Integer updateUserWithJpql(Long id, String name) {
		return userRepository.updateName(id, name);
	}
	
//	public List<User> getByCity (String city) {
//		return userRepository.findByAddressCity(city);
//	}
}
