package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.request.CreateUserRequest;
import com.example.request.UpdateUserRequest;
import com.example.response.UserResponse;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user/")
public class UserController {
	
//	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping("getAll")
	public List<UserResponse> getAllUsers() {
//		logger.error("Inside Error");
//		logger.warn("Inside warn");
//		logger.info("Inside info");
//		logger.debug("Inside debug");
//		logger.trace("Inside trace");
		
		List<User> userList = userService.getAllUsers();
		List<UserResponse> userResponseList = new ArrayList<UserResponse>();

		userList.stream().forEach(user -> {
			userResponseList.add(new UserResponse(user));
		});

		return userResponseList;
	}

//	@PostMapping("create")
//	private UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
//		User user = userService.createUser(createUserRequest);
//		return new UserResponse(user);
//	}
	
	@PostMapping("create")
	private UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
//		logger.info("Inside info sfght");
		User user = userService.createUser(createUserRequest);
		System.out.println("bbbb");
//		logger.info("Inside info sss");
		return new UserResponse(user);
	}
	
	@PutMapping("update")
	public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
		User user = userService.updateUser(updateUserRequest);
		return new UserResponse(user);
	}
	
//	@DeleteMapping("delete")
//	public String deleteUser(@RequestParam long id) {
//		return userService.deleteUser(id);
//	}
	
	@DeleteMapping("delete/{id}")
	public String deleteUser(@PathVariable long id) {
		return userService.deleteUser(id);
	}
	
	@GetMapping("getByName/{name}")
	public List<UserResponse> getByName(@PathVariable String name){
		List<User> userList = userService.getByName(name);
		List<UserResponse> userResponseList = new ArrayList<UserResponse>();

		userList.stream().forEach(user -> {
			userResponseList.add(new UserResponse(user));
		});

		return userResponseList;
	}
	
	@GetMapping("getByNameAndEmail/{name}/{email}")
	public UserResponse getByNameAndEmail(@PathVariable String name, @PathVariable String email){
		return new UserResponse(userService.getByNameAndEmail(name, email));
	}
	
	@GetMapping("getAllWithPagination")
	public List<UserResponse> getAllUsersWithPagination (@RequestParam int pageNo, @RequestParam int pageSize){
		List<User> userList = userService.getAllUsersWithPagination(pageNo, pageSize);
		List<UserResponse> userResponsesList = new ArrayList<UserResponse>();
		userList.stream().forEach(user -> {
			userResponsesList.add(new UserResponse(user));
		});
		
		return userResponsesList;
	}
	
	@GetMapping("getAllWithSorting")
	public List<UserResponse> getAllUsersWithSorting (){
		List<User> userList = userService.getAllUsersWithSorting();
		List<UserResponse> userResponsesList = new ArrayList<UserResponse>();
		userList.stream().forEach(user -> {
			userResponsesList.add(new UserResponse(user));
		});
		
		return userResponsesList;
	}
	
	@PutMapping("updateName/{id}/{name}")
	public String updateUserWithJpql (@PathVariable Long id, @PathVariable String name) {
		return userService.updateUserWithJpql(id, name)+"user updated";
	}
	
//	@GetMapping("getByCity/{city}")
//	public List<UserResponse> getByCity (@PathVariable String city){
//		List<User> userList = userService.getByCity(city);
//		List<UserResponse> userResponsesList = new ArrayList<UserResponse>();
//		userList.stream().forEach(user -> {
//			userResponsesList.add(new UserResponse(user));
//		});
//		
//		return userResponsesList;
//	}
}
