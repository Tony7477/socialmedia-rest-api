package com.portfolio.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService service;
	
	

	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/users")	
public List<User> retriveAllUsers(){
		return service.findAllUsers();
	}
	
	@GetMapping("/users/{id}")	
	public User retriveUserById(@PathVariable int id){
		User user=service.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id"+id);
		}
			return user;
		}
	
	@DeleteMapping("/users/{id}")	
	public void deleteUserById(@PathVariable int id){
		service.deleteById(id);
		
		}
	
	@PostMapping("/users")	
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser=	service.save(user);
			URI location=ServletUriComponentsBuilder.fromCurrentRequestUri()
					.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
			
		}


}
