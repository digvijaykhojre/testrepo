package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.pojos.User;
public interface UserService {
	/**
	 * To fetches user details
	 * @param user
	 * @return
	 */
	public User getUser(User user);
	/**
	 * To save user to the database
	 * @param user
	 * @return
	 */
	public User saveUser(User user);
	/**
	 * To update existing user
	 * @param user
	 * @return
	 */
	public User updateUser(User user);
	/**
	 * To delete existing user
	 * @param user
	 * @return
	 */
	public User deleteUser(User user);
	
}
