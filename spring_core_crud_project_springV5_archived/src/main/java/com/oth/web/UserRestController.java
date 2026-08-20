package com.oth.web;

import com.oth.model.User;
import com.oth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping(produces = "application/json;charset=UTF-8")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.createUser(user));
	}
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> modifyUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.modifyUser(user));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.findUserById(id));
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> deleteUserById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}

}
