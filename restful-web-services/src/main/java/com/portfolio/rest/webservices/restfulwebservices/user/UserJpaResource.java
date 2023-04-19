package com.portfolio.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portfolio.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.portfolio.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	private UserRepository repository;
	private PostRepository postRepository;
	
	 

	public UserJpaResource(UserRepository repository,PostRepository postRepository) {
		super();
		this.repository = repository;
		
		this.postRepository=postRepository;
	}

	@GetMapping("/jpa/users")	
public List<User> retriveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")	
	public EntityModel<User> retriveUserById(@PathVariable int id){
		Optional <User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id"+id);
		}
		EntityModel<User>entityModel=EntityModel.of(user.get());
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	/*	
	@GetMapping("/jpa/users/{id}")	
	public User retriveUserById(@PathVariable int id){
		User user =repository.findById(id).get();
		return user;
		
	}
	*/
	@DeleteMapping("/jpa/users/posts/{id}")	
	public void deleteUserById(@PathVariable int id){
		postRepository.deleteById(id);
		}
	
	@GetMapping("/jpa/users/posts/{id}")	
	public List<Post> retrievePostsForUser(@PathVariable int id){
		User user=repository.findById(id).get();
		return user.getPosts();
		
		}
	
	@PostMapping("/jpa/users")	
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser=	repository.save(user);
			URI location=ServletUriComponentsBuilder.fromCurrentRequestUri()
					.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
			
			return ResponseEntity.created(location).build();
			
		}
	
	@PostMapping("/jpa/users/{id}/posts")	
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post){
		User user=repository.findById(id).get();
		post.setUser(user);
		Post savedPost=postRepository.save(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
		}

	//edit posts
	//("jpa/users/{id}/posts/{pid}"
	@PutMapping("jpa/users/{id}/posts/{pid}")
	public  void editPostById(@PathVariable int id,@Valid @RequestBody Post post,@PathVariable int pid){
		User user=repository.findById(id).get();
		Post posts=postRepository.findById(pid).get();
		posts.setDescription(post.getDescription());
		Post savedPost=postRepository.save(post);
	}
	
	public UserRepository getRepository() {
		return repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	public PostRepository getPostrepository() {
		return postRepository;
	}

	public void setPostrepository(PostRepository postrepository) {
		this.postRepository = postrepository;
	}

	


}
