package com.antonio.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.dao.BuyDAO;
import com.antonio.dao.UserDAO;
import com.antonio.model.Buy;
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
	@Autowired
	private BuyDAO buyDao;
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
		// Comprobación del usuario y contraseña de LogIn
		User u = userDAO.userLogIn(user.getUsername(), user.getPassword());
		
		// Creación del Mapa de Sesión
		Map session = ActionContext.getContext().getSession();
		if (u != null) {
			// En caso de que el usuario sí exista se guarda sus datos en la sessión actual
			session.put("username", u.getUsername());
			session.put("password", u.getPassword());
			session.put("name", u.getName());
			session.put("user_id", u.getId());
			
			// Comprueba si el usuario tiene una compra activa
			Buy existBuy = buyDao.getBuyUser(u.getId(), "active");
			
			if (existBuy != null) {
				session.put("id_buy_actually", existBuy.getId());
				session.put("buy", existBuy);
				//System.out.println("YA EXISTE UNA COMPRA: " + existBuy.getId());
			}else {
				Buy newBuy = new Buy();
				newBuy.setStatus("active");
				newBuy.setUserId(u.getId());
				newBuy.setTotal(0);
				newBuy.setDate();
				int idBuy = buyDao.createaBuy(newBuy);
				session.put("id_buy_actually", idBuy);
				//System.out.println("ID DE LA COMPRA CREADA: " + idBuy);
			}
			
			return SUCCESS;
		}
		
		// En caso de que el usuario no exista que devuelva un ERROR.
		session.put("login", "false");
		return ERROR;
	}

}
