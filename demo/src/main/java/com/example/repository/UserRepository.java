package com.example.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Address;
import com.example.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByName(String name);
	User findByNameAndEmail (String name, String email);
	List<User> findByNameIn(List<String> names);
	List<User> findByNameContains(String name);
	
	@Query("from User where name = :name and email = :email")
	User getByNameAndEmail (String name, String email);
	
	@Modifying
	@Transactional
	@Query("Update User set name = :name where id = :id")
	Integer updateName(Long id, String name);
	
	List<User> findByAddressCity(String city);
//	
//	@Query("From User where address.city = :city")
//	List<User> getByAddressCity (String city);
}
