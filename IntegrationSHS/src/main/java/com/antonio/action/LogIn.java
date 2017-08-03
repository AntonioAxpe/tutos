package com.antonio.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Buy;
import com.antonio.model.User;
import com.antonio.service.BuyService;
import com.antonio.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase encargada de realizar el Login del usuario en la aplicación.
 * Además de comprobar si está logueado se le crea una compra activa en caso de no tener una.
 * @author Antonio Soto
 */
@Action("/Inicio")
@ResultPath("/WEB-INF/views")
@Results({
	//@Result(name = "success", location = "listProduct", type = "redirect"),
	@Result(name = "success", location = "Main.jsp"),
	@Result(name = "error", location = "index.jsp")
})
public class LogIn extends ActionSupport {

	@Autowired
	private UserService userService;
	@Autowired
	private BuyService buyService;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute(){
		Map session = ActionContext.getContext().getSession();

		// Comprobación del usuario y contraseña de LogIn
		User u = null;
		if (user != null) {			
			u = userService.userLogIn(user.getUsername(), user.getPassword());
		}else if (session.get("username") != "" && session.get("password") != "") {
			u = userService.userLogIn(String.valueOf(session.get("username")), String.valueOf(session.get("password")));
		}
		
		// Creación del Mapa de Sesión
		if (u != null) {
			// En caso de que el usuario sí exista se guarda sus datos en la sessión actual
			session.put("username", u.getUsername());
			session.put("password", u.getPassword());
			session.put("name", u.getName());
			session.put("user_id", u.getId());
			
			checkBuyUser(u, session);
			
			return SUCCESS;
		}
		
		// En caso de que el usuario no exista que devuelva un ERROR.
		session.put("login", "false");
		return ERROR;
	}

	/**
	 * Comprueba si el usuario tiene una compra Activa.
	 * En caso de no tenerla, se le crea una y se le asocia al usuario además de guardala en sesión.
	 * @param user Objeto usuario activo.
	 * @param session Sesión donde se almacena su compra activa.
	 */
	public void checkBuyUser(User user, Map session) {
		// Comprueba si el usuario tiene una compra activa.
		Buy existBuy = buyService.getBuyUser(user.getId(), "active");
		
		// Si tiene una compra Activa este se guarda en sesión.
		if (existBuy != null) 
		{
			session.put("id_buy_actually", existBuy.getId());
			session.put("buy", existBuy);
		}
		// En caso de no tener una, se crea una nueva y se le asocia al usuario.
		else 
		{
			Buy newBuy = new Buy();
			newBuy.setStatus("active");
			newBuy.setUserId(user.getId());
			newBuy.setTotal(0);
			newBuy.setDate();
			
			int idNewBuy = buyService.createaBuy(newBuy);
			newBuy.setId(idNewBuy);
			
			session.put("id_buy_actually", idNewBuy);
			session.put("buy", newBuy);
		}
	}
	
}
