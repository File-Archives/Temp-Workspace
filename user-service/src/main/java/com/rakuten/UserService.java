package com.rakuten;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // This annotation tells the class to be treated as a service.
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	Integer save(User user) {
		repository.save(user);
		System.out.println("User saved in the repository.");
		return user.getId();
	}
	
	List<User> getUserByName(String name){
		
		if(name.isBlank() || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		
		List<User> filteredUsers = getAllUsers().stream().filter((user)->user.getName().equals(name)).collect(Collectors.toList());
		return filteredUsers;
		
		//Try the same thing with for each loop. There's some error.
		
//		users.forEach((User single_user) -> {
//		if(single_user.getName().equals(name)) {
//				return single_user;
//			}
//		});
		
		//The same thing using for loop, works like a charm.
//		for(int i=0; i<users.size(); i++) {
//			if(users.get(i).getName().equals(name)) {
//				return users.get(i);
//			}
//		}
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public List<User> getUserByAge(int age) {
		
		if(age<0) {
			throw new IllegalArgumentException("Age cannot be negative " + age);
		}
		 List<User> filteredUsers = getAllUsers().stream().filter((user)->user.getAge() == age).collect(Collectors.toList());
		 return filteredUsers;
	}

}
