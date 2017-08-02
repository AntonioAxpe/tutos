package com.antonio.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Product;
import com.antonio.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/new_product")
@ResultPath("/WEB-INF/views")
@Results({ 
	@Result(name = "success", type = "redirect", location = "listProduct"),
	@Result(name = "none", location = "ProductData.jsp")
})
public class NewProductAction extends ActionSupport {

	@Autowired
	private ProductService productService;
	private Product product;
	private Map session;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String execute() throws Exception {

		if (product != null) {
			session = ActionContext.getContext().getSession();
			product.setUserId(Integer.parseInt(session.get("user_id").toString()));
			productService.insertProduct(product);
			return SUCCESS;
		}else {
			return NONE;
		}

	}

}
