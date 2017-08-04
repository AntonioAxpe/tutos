package com.antonio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.dao.UserDAO;
import com.antonio.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> listUsers() {
		userDAO.listUsers();
		return null;
	}

	@Override
	@Transactional
	public User getUser(int idUser) {
		return userDAO.getUser(idUser);
	}

	@Override
	@Transactional
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(int idUser) {
		userDAO.deleteUser(idUser);
	}

	@Override
	@Transactional
	public User userLogIn(String username, String password) {
		return userDAO.userLogIn(username, password);
	}

}
