package com.antonio.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;

@Action("/remove_product")
@ResultPath("/WEB-INF/views")
@Result(name = "success", type = "redirect", location = "listProduct")
public class RemoveProductAction extends ActionSupport {

	@Autowired
	ProductService productService;
	
	@Override
	public String execute() throws Exception {
		try {			
			int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
			productService.deleteProduct(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
	
}
