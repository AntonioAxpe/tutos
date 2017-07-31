package com.antonio.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.dao.BuyDAO;
import com.antonio.dao.DetailBuyDAO;
import com.antonio.dao.ProductDAO;
import com.antonio.model.Buy;
import com.antonio.model.DetailBuy;
import com.antonio.model.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Action("/buy_product")
@ResultPath("/WEB-INF/views")
@Result(name = "success", type = "redirect", location = "listProduct")
public class BuyProduct extends ActionSupport {

	@Autowired
	BuyDAO buyDAO;
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public String execute() throws Exception {
		
		/**
		 * INTEGRACIÓN DE LAS TABLAS BUY - DETAILS - PRODUCT.
		 * Un producto es añadido a una compra, la compra registra un detalle de todos los productos comprados.
		 * 
		 * Producto ->(guarda)-> Detalle de Compra ->(asociada)-> Compra
		 */
		Map session = ActionContext.getContext().getSession();
		int buyId = (int) session.get("id_buy_actually");
		
		// Se obtiene el producto
		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		Product product = productDAO.getProduct(id);
		
		// Se obtiene la compra actual del usuario
		Buy buy = (Buy) session.get("buy");

		// Se extrae la lista de productos asociadas a la compra actual
		@SuppressWarnings("unchecked")
		Set<DetailBuy> cesta = (Set<DetailBuy>) buy.getDetailBuy();
		
		// En caso de que la lista esté vacia se una nueva y se le añade el producto
		// En caso de no estar vacia se itera la lista.
		if (cesta != null) {
			Iterator<DetailBuy> iterador = cesta.iterator();
			
			// Se va extrayendo cada uno de los objetos y se comprueba si coinciden en ID
			// Si coinciden se modifica su cantidad y total y se actualiza el registro de la lista
			// de Detalles de Compra.
			while(iterador.hasNext()) {
				DetailBuy detalles = iterador.next();
				Product producto = detalles.getProduct();
				if (producto.getId() == product.getId()) {
					detalles.setQuantity(detalles.getQuantity() + 1);
					detalles.setTotal(detalles.getTotal() + product.getPrice());
					buy.addDetailBuy(detalles);
				}
			}
		}else {
			DetailBuy detailBuy = new DetailBuy();
			detailBuy.setBuy(buy);
			detailBuy.setProduct(product);
			detailBuy.setQuantity(1);
			detailBuy.setTotal(product.getPrice());
			buy.addDetailBuy(detailBuy);
		}
		
		// Se encarga de crear o modificar la compra en la tabla BUY.
		buyDAO.createaBuy(buy);
		session.put("buy", buy);
		
		return SUCCESS;
	}

}
