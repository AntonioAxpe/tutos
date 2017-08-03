package com.antonio.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Buy;
import com.antonio.service.BuyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/PayBuy")
@ResultPath("/WEB-INF/views")
@Result(name = "success", location = "MyCart.jsp")
public class PayBuyAction extends ActionSupport{

	@Autowired
	private BuyService buyService;
	private Boolean buyPayed = false;
	
	public Boolean getBuyPayed() {
		return this.buyPayed;
	}
	
	@Override
	public String execute() throws Exception {
		
		Map session = ActionContext.getContext().getSession();
		
		Buy myBuy = (Buy) session.get("buy");
		myBuy.setStatus("payed");
		
		
		buyService.createaBuy(myBuy);
		buyPayed = true;
		
		session.remove("buy");
		session.remove("id_buy_actually");
		
		return SUCCESS;
	}

	
}
