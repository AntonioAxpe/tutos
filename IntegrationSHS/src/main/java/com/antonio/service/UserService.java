package com.antonio.service;

import java.util.List;

import com.antonio.model.User;

public interface UserService {

	public List<User> listUsers();

	public User getUser(int idUser);

	public void insertUser(User user);

	public void deleteUser(int idUser);

	public User userLogIn(String username, String password);
}
