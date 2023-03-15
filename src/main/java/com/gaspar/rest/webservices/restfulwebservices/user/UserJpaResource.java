package com.gaspar.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gaspar.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.gaspar.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	private UserRepository repository;
	private PostRepository postRepository;
	
	public UserJpaResource(UserRepository repository, PostRepository postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		User user = repository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		if (repository.findById(id).isEmpty()) {
			throw new UserNotFoundException("id: " + id);
		}
		repository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id){
		User user = repository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		return user.getPosts();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User saveduser = repository.save(user);
		URI location = getLocationURI(saveduser.getId());
		return ResponseEntity.created(location).build();
	}

	private URI getLocationURI(Integer id) {
		return ServletUriComponentsBuilder
							.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(id)
							.toUri();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createUserPosts(@PathVariable int id, @Valid @RequestBody Post post){
		User user = repository.findById(id).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		post.setUser(user);
		Post savedPost = postRepository.save(post);
		
		URI location = getLocationURI(savedPost.getId());
		return ResponseEntity.created(location).build();
		
	}
	
}
