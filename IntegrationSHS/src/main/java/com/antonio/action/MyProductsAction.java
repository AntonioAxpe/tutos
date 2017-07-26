package com.antonio.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.dao.ProductDAO;
import com.antonio.model.Product;
import com.opensymphony.xwork2.ActionSupport;

@Action("MyProducts")
@ResultPath("/WEB-INF/views")
@Results({ @Result(name = "success", location = "ProductList.jsp") })
public class MyProductsAction extends ActionSupport {

	@Autowired
	private ProductDAO productDAO;
	private List<Product> myProducts;

	public List<Product> getMyProducts() {
		return myProducts;
	}

	public void setMyProducts(List<Product> myProducts) {
		this.myProducts = myProducts;
	}

	@Override
	public String execute() throws Exception {
		String id = ServletActionContext.getRequest().getParameter("userId");
		myProducts = productDAO.listMyProductById(Integer.parseInt(id));
		return SUCCESS;
	}

}
