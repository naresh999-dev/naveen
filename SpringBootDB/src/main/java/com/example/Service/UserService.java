package com.example.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Dao.UserRepository;
import com.example.Dto.User;
@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public User addUser(User user) {
		User data = repository.save(user);
		System.out.println("UserService"+data.getUname());
		return data;
		
		
	}

	public List<User> getAllUsers() {
		List<User> list= new ArrayList<>();
		Iterable<User> users = repository.findAll();
		for (User user : users) {
			list.add(user);
		}
		return list;
		
		
	}

	public void delteById(int id) {
		repository.deleteById(id);
		
	}

	public void deleteAllUsers() {
		repository.deleteAll();
		
	}
	
	
	
	
	//@Transactional(propagation =Propagation.REQUIRES_NEW)
	public User getUser(int id) {
		Optional<User> users=repository.findById(id);
		User user=new User();
		if(users.isPresent()) {
			 user = users.get();
			}
		System.out.println("userService"+user.getUname());
		return user;
		
		
	}

	public User updateUser(User user) {
		// TODO Auto-generated method stub
		User dbUser=getUser(user.getId());
		dbUser.setUname(user.getUname());
		User updateUser=repository.save(dbUser);
		return updateUser;
	}

	public User getUserByUersName(String username) {
		return repository.findByUsername(username);
	}

	public User findByMobileNumber(String number) {
		// TODO Auto-generated method stub
		return repository.findBymobileNumber(number);
	}
	

}
