package com.antonio.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Buy;
import com.antonio.model.DetailBuy;
import com.antonio.service.BuyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/MyBuys")
@ResultPath("/WEB-INF/views")
@ParentPackage("json-default")
@Results({ 
	@Result(name = "success", location = "MyBuys.jsp"),
	@Result(name = "none", type = "json") 
})
public class MyBuys extends ActionSupport {

	@Autowired
	private BuyService buyService;
	private List<Buy> myBuys;
	private String oneBuySelect;
	private Set<DetailBuy> detailsBuy;

 	public List<Buy> getMyBuys() {
		return myBuys;
	}

	public String getOneBuySelect() {
		return oneBuySelect;
	}

	public void setOneBuySelect(String oneBuySelect) {
		this.oneBuySelect = oneBuySelect;
	}
	
	public Set<DetailBuy> getDetailsBuy() {
		return detailsBuy;
	}

	public void setDetailsBuy(Set<DetailBuy> detailsBuy) {
		this.detailsBuy = detailsBuy;
	}

	@Override
	public String execute() throws Exception {

		Map session = ActionContext.getContext().getSession();
		myBuys = buyService.listBuy(Integer.parseInt(session.get("user_id").toString()));

		if (oneBuySelect != null) {
			for (int i = 0; i < myBuys.size(); i++) {
				if (myBuys.get(i).getId() == Integer.parseInt(oneBuySelect)) {
					detailsBuy = myBuys.get(i).getDetailBuy();
				}
			}
			return NONE;
		}


		return SUCCESS;
	}

}
