package com.antonio.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.antonio.dao.BuyDAO;
import com.antonio.model.Buy;
import com.antonio.model.DetailBuy;
import com.antonio.model.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/MyCart")
@ResultPath("/WEB-INF/views")
@Results({ @Result(name = "success", location = "MyCart.jsp") })
public class MyCart extends ActionSupport {

	private List<DetailBuy> myCartList = null;
	private int totalMyCart = 0;
	private BuyDAO buyDAO;

	public List<DetailBuy> getMyCartList() {
		return myCartList;
	}

	public int getTotalMyCart() {
		return totalMyCart;
	}

	@Override
	public String execute() throws Exception {

		@SuppressWarnings("rawtypes")
		Map session = ActionContext.getContext().getSession();
		Buy buy = (Buy) session.get("buy");
		myCartList = parseToList(buy);
		totalMyCart = getTotalMyList(myCartList);
		String action = ServletActionContext.getRequest().getParameter("action");
		int idProduct = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		
		switch (action) {
			case "delete":
				break;
			case "edit":
				break;
		    	
		}


		return SUCCESS;
	}

	/**
	 * Parsea un objeto BUY, el cual contiene un Set de Objetos, a una List de
	 * DetalleCompra
	 * 
	 * @param buy
	 *            Objeto a parsear.
	 * @return Lista de Objetos DetalleCompra.
	 */
	public List<DetailBuy> parseToList(Buy buy) {
		Set<DetailBuy> MyListDetail = (Set<DetailBuy>) buy.getDetailBuy();
		Iterator<DetailBuy> itr = MyListDetail.iterator();
		List<DetailBuy> MyListProducts = new ArrayList<DetailBuy>();

		while (itr.hasNext()) {
			MyListProducts.add(itr.next());
		}

		return MyListProducts;
	}

	/**
	 * Obtiene el precio total de una compra detallada.
	 * 
	 * @param list
	 *            List a recorrer.
	 * @return Devuelve la cantidad total.
	 */
	public int getTotalMyList(List<DetailBuy> list) {
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			total += list.get(i).getTotal();
		}
		return total;
	}
}