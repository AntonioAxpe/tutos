package com.antonio.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.dao.UserDAO;
import com.antonio.model.Product;
import com.antonio.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/Inicio")
@ResultPath("/WEB-INF/views")
@Results({
	//@Result(name = "success", location = "listProduct", type = "redirect"),
	@Result(name = "success", location = "Main.jsp"),
	@Result(name = "error", location = "index.jsp")
})
public class LogIn extends ActionSupport {

	@Autowired
	private UserDAO userDAO;
	private User user;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute(){
		User u = userDAO.userLogIn(user.getUsername(), user.getPassword());
		Map session = ActionContext.getContext().getSession();
		if (u != null) {
			session.put("username", u.getUsername());
			session.put("password", u.getPassword());
			session.put("name", u.getName());
			session.put("user_id", u.getId());
			return SUCCESS;
		}
		
		session.put("login", "false");
		return ERROR;
	}

}
