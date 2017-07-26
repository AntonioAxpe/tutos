package com.antonio.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.dao.ProductDAO;
import com.opensymphony.xwork2.ActionSupport;

@Action("/remove_product")
@ResultPath("/WEB-INF/views")
@Result(name = "success", type = "redirect", location = "listProduct")
public class RemoveProductAction extends ActionSupport {

	@Autowired
	ProductDAO productDAO;
	
	@Override
	public String execute() throws Exception {
		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		productDAO.deleteProduct(id);
		return SUCCESS;
	}
	
}
