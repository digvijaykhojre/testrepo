package com.uxpsystems.assignment.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserRepository;
import com.uxpsystems.assignment.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User getUser(User user) {
		Optional<User> users = userRepository.findById(user.getUserId());
		if (users.isPresent()) {

			user = users.get();
			user.setErrorCode("00");
			user.setErrorDesc("Success");
			return user;
		} else {
			user.setErrorCode("01");
			user.setErrorDesc("User Not Found");
			return user;
		}

	}

	@Override
	public User saveUser(User user) {
		try {
			this.userRepository.save(user);
			user.setErrorCode("00");
			user.setErrorDesc("Success");
		} catch (Exception e) {
			System.err.println("User Save Failed:" + e.getMessage());
			user.setErrorCode("01");
			user.setErrorCode("Couldn't Save Failed");
		}

		return user;
	}

	@Override
	public User updateUser(User user) {
		Optional<User> users = userRepository.findById(user.getUserId());
		if (users.isPresent()) {
			// Keeping old user id and updating rest
			User newUser = users.get();
			newUser.setPassword(user.getPassword());
			newUser.setUserName(user.getUserName());
			newUser.setStatus(user.getStatus());
			this.userRepository.save(newUser);
			user.setErrorCode("00");
			user.setErrorDesc("Success");
			return user;

		} else {
			user.setErrorCode("02");
			user.setErrorDesc("User Couln't Be Updated");
			return user;
		}

	}

	@Override
	public User deleteUser(User user) {
		Optional<User> users = userRepository.findById(user.getUserId());
		if (users.isPresent()) {
			this.userRepository.delete(user);
			user.setErrorCode("00");
			user.setErrorDesc("Success");
			return user;
		} else {
			user.setErrorCode("01");
			user.setErrorDesc("User Not Found");
			return user;
		}

	}

}
