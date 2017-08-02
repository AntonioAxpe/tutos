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
			
			// Comprueba si el usuario tiene una compra activa
			Buy existBuy = buyService.getBuyUser(u.getId(), "active");
			
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
				int idBuy = buyService.createaBuy(newBuy);
				session.put("id_buy_actually", idBuy);
				newBuy.setId(idBuy);
				session.put("buy", newBuy);
				//System.out.println("ID DE LA COMPRA CREADA: " + idBuy);
			}
			
			return SUCCESS;
		}
		
		// En caso de que el usuario no exista que devuelva un ERROR.
		session.put("login", "false");
		return ERROR;
	}

}
