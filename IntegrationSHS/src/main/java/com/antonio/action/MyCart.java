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
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Buy;
import com.antonio.model.DetailBuy;
import com.antonio.service.BuyService;
import com.antonio.service.DetailBuyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/MyCart")
@ResultPath("/WEB-INF/views")
@Results({ @Result(name = "success", location = "MyCart.jsp") })
public class MyCart extends ActionSupport {

	@Autowired
	private BuyService buyService;
	@Autowired
	private DetailBuyService detailBuyService;
	private List<DetailBuy> myCartList = null;
	private float totalMyCart = 0;
	
	public List<DetailBuy> getMyCartList() {
		return myCartList;
	}

	public float getTotalMyCart() {
		return totalMyCart;
	}

	@Override
	public String execute() throws Exception {
		/**
		 * Este actión ejecutará 3 acciones:
		 * - Ver mi carrito: Lista todos los productos en pantalla del carrito del usuario.
		 * - Editar producto: Puede modificar la cantidad de compra del producto en el carrito.
		 * - Eliminar producto: Elimina el producto del carrito de compra.
		 * 
		 * Al momento de listar no envia ningún parametro.
		 * Por parte de eliminar y editar se envia un parametro 'action' con el ID del producto en el carrito.
		 */
		
		@SuppressWarnings("rawtypes")
		Map session = ActionContext.getContext().getSession();
		Buy buy = (Buy) session.get("buy");
		myCartList = parseToList(buy);
		totalMyCart = getTotalMyList(myCartList);
		
		if (ServletActionContext.getRequest().getParameter("action") != null 
				&& ServletActionContext.getRequest().getParameter("id") != null) {
			String action = ServletActionContext.getRequest().getParameter("action");
			int idProduct = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));

			switch (action) {
				case "delete":
					buy = deleteProductFromMyCart(buy, idProduct);
					myCartList = parseToList(buy);
					totalMyCart = getTotalMyList(myCartList);
					break;
				case "edit":
					break;
			}
		}
		
		generateTotalBuy(buy);
		buyService.createaBuy(buy);

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
	public float getTotalMyList(List<DetailBuy> list) {
		float total = 0;
		for (int i = 0; i < list.size(); i++) {
			total += list.get(i).getTotal();
		}
		return total;
	}

	/**
	 * Elimina de una Compra un producto a través de su ID.
	 * @param myCart Compra actual de todos los productos del usuario.
	 * @param idProduct Identificador del producto a eliminar.
	 * @return Devuelve la compra actualizada.
	 */
    public Buy deleteProductFromMyCart(Buy myCart, int idProduct) {
    	// Se obtiene la lista DetalleCompra de la Compra y se itera.
    	Set<DetailBuy> myCartList = (Set<DetailBuy>) myCart.getDetailBuy();
    	Iterator<DetailBuy> itr = myCartList.iterator();
    	
    	// Cada recorrido comprueba si el objeto de la lista coincide con el id del producto a eliminar.
    	while (itr.hasNext()) {
    		DetailBuy db = itr.next();
			if (db.getProduct().getId() == idProduct) {
				detailBuyService.deleteDetailBuyFromMyCart(db);
				itr.remove();
			}
		}
    	
    	// Una vez eliminado el producto, devuelve la compra actualizada.
    	return myCart;
    }

    public void generateTotalBuy(Buy buy) {
    	Set<DetailBuy> myDetailBuy = buy.getDetailBuy();
		Iterator<DetailBuy> itr = myDetailBuy.iterator();
		float buyTotal = 0;
		
    	while (itr.hasNext()) {
			DetailBuy detail = itr.next();
			buyTotal += detail.getTotal();
		}
    	
    	buy.setTotal(buyTotal);
    }
}