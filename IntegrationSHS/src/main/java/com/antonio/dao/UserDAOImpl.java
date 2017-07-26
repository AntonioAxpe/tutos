package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.antonio.model.User;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public User getUser(int idUser) {
		/*String consult = "FROM User WHERE id = " + idUser;
		Query query = sessionFactory.getCurrentSession().createQuery(consult);

		@SuppressWarnings("unchecked")
		List<User> listUsers = (List<User>) query.list();

		if (listUsers != null && !listUsers.isEmpty()) {
			return listUsers.get(0);
		}*/
		return null;
	}

	public void insertUser(User user) {
		// TODO Auto-generated method stub

	}

	public void deleteUser(int idUser) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public User userLogIn(String username, String password) {
		
		String consult = "FROM User WHERE username = '" + username + "' AND password = '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(consult);

		@SuppressWarnings("unchecked")
		List<User> listUsers = (List<User>) query.list();

		if (listUsers != null && !listUsers.isEmpty()) {
			return listUsers.get(0);
		}
		return null;
	}

}
