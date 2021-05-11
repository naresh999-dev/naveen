package com.example.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Dto.User;
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
	
	public User findByUsername(String name);
	public User findBymobileNumber(String name);
	

}
