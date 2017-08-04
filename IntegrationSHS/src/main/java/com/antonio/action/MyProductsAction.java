package com.antonio.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Product;
import com.antonio.service.ProductService;
import com.antonio.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("MyProducts")
@ResultPath("/WEB-INF/views")
@Results({ @Result(name = "success", location = "ProductList.jsp") })
public class MyProductsAction extends ActionSupport {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	private List<Product> myProducts;
	private List<Integer> post = new ArrayList<Integer>();
	private Boolean mySold;

	public List<Product> getMyProducts() {
		return myProducts;
	}

	public Boolean getMySold() {
		return mySold;
	}

	@Override
	public String execute() throws Exception {
		
		HttpServletRequest attrs = ServletActionContext.getRequest();

		Map session = ActionContext.getContext().getSession();
		
		String id = session.get("user_id").toString();

		myProducts = productService.listMyProductById(Integer.parseInt(id));

		if (attrs.getParameter("sold") != null) {
			
			//myProducts.get(0).getDetailBuy().iterator().next();
			/*for (int i = 0; i < myProducts.size(); i++) {
				if (myProducts.get(i).getDetailBuy().isEmpty() || myProducts.get(i).getDetailBuy().size() == 0) {
					try {
						post.add(i);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			for (int i = 0; i < post.size(); i++) {
				myProducts.remove(post.get(i));
			}*/
			
			mySold = true;
			return SUCCESS;
		}
		
		return SUCCESS;
	}

	public String saludo(int idUser) {
		return userService.getUser(idUser).getName();
	}
	
}
