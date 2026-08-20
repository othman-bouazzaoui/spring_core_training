package com.oth.web;

import com.oth.model.User;
import com.oth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	private static final String USERS = "users";

	@Autowired
	private UserService userService;

	@GetMapping(value = {"",  "/users" })
	public String getAllUsers(Model model) {
		model.addAttribute(USERS, userService.getAllUsers());
		model.addAttribute("user", new User());
		return "manageUsers";
	}


	@PostMapping(value = "/users/add")
	public String addUser(@ModelAttribute User user, Model model) {
		userService.createUser(user);
		model.addAttribute(USERS, userService.getAllUsers());
		return "redirect:/users";
	}

	@GetMapping(value = "/users/edit/{id}")
	public String editUser(@PathVariable Long id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		model.addAttribute(USERS, userService.getAllUsers());
		return "manageUsers";
	}

	@PostMapping(value = "/users/update")
	public String updateUser(@ModelAttribute User user, Model model) {
		userService.modifyUser(user);
		model.addAttribute(USERS, userService.getAllUsers());
		model.addAttribute("user", new User());
		return "redirect:/users";
	}

	@GetMapping(value = "/users/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}

}
