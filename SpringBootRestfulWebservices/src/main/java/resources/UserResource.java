package resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dao.UserDaoService;
import exceptions.UserNotFoundException;
import models.User;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)
	{
		User user = userDaoService.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id-"+ id);   //Adding custom exception for user not present.
		
		// Adding HATEOS concepts
		// Adding link to all resources	//retrieveAllUsers
		EntityModel<User> entityModel = EntityModel.of(user);
		Link linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
										.methodOn(this.getClass())
										.retrieveAllUsers())
										.withRel("all-users");
		entityModel.add(linkTo);
		return entityModel;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User newSavedUser =  userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													.path("/{id}")
													.buildAndExpand(newSavedUser.getId())
													.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		User user = userDaoService.deleteUserById(id);
		if(user==null)
			throw new UserNotFoundException("id-"+ id);   //Adding custom exception for user not present.
	}
}
