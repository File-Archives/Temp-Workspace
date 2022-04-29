package com.rakuten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation makes a class as a rest controller or a spring controller.
@RequestMapping("/user")
public class UserController {
	
	//Controller is the dependent and service is the dependency.
	//Customer is dependent and shop is the dependency. 
	@Autowired //Tells spring that when this code will run, create an object of user service and assign it to the object service.
	UserService service; // Dependency injection.
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) // will create a status code of 201 for POST which is universally accepted.
	Integer saveUser(@Valid @RequestBody User user) {
		System.out.println(String.format("Received Name:: %s, Received Age:: %d", user.getName(), user.getAge()));
		return service.save(user);
	}
	
	@GetMapping
	List<User> getUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/name/{name}")
	List<User> getUserFromName(@PathVariable String name) {
		return service.getUserByName(name);
	}
	
	@GetMapping("age/{age}")
	List<User> getUserFromAge(@PathVariable int age) {
		return service.getUserByAge(age);
	}
	
	@DeleteMapping("/delete/{id}")
	void deleteUserFromId(@PathVariable int id) {
		service.removeUserFromId(id);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void handleEmptyResultDataAccessException() {
		
	}
	
	//We have added messages. now we shouldn't show the message as hackers can leverage the message info,so we will hide those using this below method.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = ((FieldError) error).getDefaultMessage();
			
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
