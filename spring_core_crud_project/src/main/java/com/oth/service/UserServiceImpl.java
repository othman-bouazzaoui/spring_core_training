package com.oth.service;

import com.oth.dao.jpa.UserRepository;
import com.oth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	List<User> users = loadUsers();

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User modifyUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public int deleteUser(Long id) {
		userRepository.deleteById(id);
		return 1;
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	private List<User> loadUsers() {
		ArrayList<User> usersList = new ArrayList<>();
		User u1 = new User(1L, "Othman", "BOUAZZAOUI", 29L);
		User u2 = new User(2L, "Mohamed", "Hamed", 35L);
		User u4 = new User(4L, "Khalid", "Somayla", 32L);
		User u3 = new User(3L, "Saad", "Chaaban", 25L);
		usersList.add(u1);
		usersList.add(u2);
		usersList.add(u3);
		usersList.add(u4);
		return usersList;
	}

}
