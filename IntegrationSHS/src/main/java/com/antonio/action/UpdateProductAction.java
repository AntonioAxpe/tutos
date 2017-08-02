package com.antonio.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Product;
import com.antonio.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@Action("/edit_product")
@ResultPath("/WEB-INF/views")
@Results({
	@Result(name = "none", location = "ProductData.jsp"),
	@Result(name = "success", type = "redirect", location = "listProduct")
})
public class UpdateProductAction extends ActionSupport {

	@Autowired
	private ProductService productService;
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String execute() throws Exception {

		if (product != null) {
			System.out.println("--- PRODUCTO ACTUALIZADO");
			System.out.println("NUEVA DESCRIPCION: " + product.getDescription());
			productService.insertProduct(product);
			return SUCCESS;
		}

		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		product = productService.getProduct(id);
		return NONE;
	}

}
