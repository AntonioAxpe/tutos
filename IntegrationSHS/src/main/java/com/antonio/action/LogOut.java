package com.antonio.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("LogOut")
@ResultPath("/WEB-INF/views")
@Result(name = "success", location = "index.jsp")
public class LogOut extends ActionSupport{

	@Override
	public String execute() throws Exception {
		ActionContext.getContext().getSession().clear();
		
		return SUCCESS;
	}

	
}
