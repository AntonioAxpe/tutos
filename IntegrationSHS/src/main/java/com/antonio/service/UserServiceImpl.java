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
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int idUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public User userLogIn(String username, String password) {
		return userDAO.userLogIn(username, password);
	}

}
