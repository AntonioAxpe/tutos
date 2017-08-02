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

/**
 * Clase encargada de almacenar los productos pedidos en la cesta del usuario.
 * @author Antonio Soto
 */
@Action("/buy_product")
@ResultPath("/WEB-INF/views")
@Result(name = "success", type = "redirect", location = "listProduct")
public class BuyProduct extends ActionSupport {

	@Autowired
	private BuyDAO buyDAO;
	@Autowired
	private ProductDAO productDAO;
	private Product product;
	private Buy buy;
	private Set<DetailBuy> miCesta;
	
	@Override
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		int buyId = (int) session.get("id_buy_actually");
		
		// Producto a añadir a la cesta
		int id = Integer.parseInt(ServletActionContext.getRequest().getParameter("id"));
		product = productDAO.getProduct(id);
		
		// Se obtiene la compra activa del usuario
		buy = (Buy) session.get("buy");

		// Se extrae la lista DetalleCompra de la compra activa del usuario
		miCesta = (Set<DetailBuy>) buy.getDetailBuy();

		// En caso de estar vacia se le añade el primer producto.
		if (miCesta != null) {
			// En caso de no estar vacia, se comprueba si el producto se encuentra ya en la lista.
			if (getMyProduct(buy, id)) 
				editProductInMyBuy(miCesta, product);
			else 
				insertInMyBuy();
		}else {
			insertInMyBuy();
		}
		
		
		generateTotalBuy(buy);
		
		// Se encarga de crear o modificar la compra en la tabla BUY.
		buyDAO.createaBuy(buy);
		session.put("buy", buy);
		
		return SUCCESS;
	}

	/**
	 * Consulta en la cesta del usuario si el producto ya se encuentra añadido.
	 * @param myListProduct Cesta del usuario
	 * @param idProduct Producto a consultar.
	 * @return Devuelve true o false si el producto se encuentra o no en la cesta.
	 */
	public Boolean getMyProduct(Buy myListProduct, int idProduct) {
		Set<DetailBuy> cesta = (Set<DetailBuy>) myListProduct.getDetailBuy();
		Iterator<DetailBuy> iterador = cesta.iterator();
		
		while(iterador.hasNext()) {
			DetailBuy detalles = iterador.next();
			if (detalles.getProduct().getId() == idProduct) {
				return true;
			}
		}
		
		return false;
	}
	
	/** 
	 * Itera la cesta del usuario para añadir la cantidad y el precio del producto a añadir.
	 * @param cesta Lista de productos ya añadidos.
	 * @param newProduct Nuevo producto a modificar.
	 */
	public void editProductInMyBuy(Set<DetailBuy> cesta, Product newProduct) {
		Iterator<DetailBuy> iterador = cesta.iterator();
		
		// Se va extrayendo cada uno de los objetos y se comprueba si coinciden en ID
		// Si coinciden se modifica su cantidad y total y se actualiza el registro de la lista
		// de Detalles de Compra.
		while(iterador.hasNext()) {
			DetailBuy detalles = iterador.next();
			Product producto = detalles.getProduct();
			if (producto.getId() == newProduct.getId()) {
				detalles.setQuantity(detalles.getQuantity() + 1);
				detalles.setTotal(detalles.getTotal() + product.getPrice());
				buy.addDetailBuy(detalles);
			}
		}
	}
	
	/**
	 * Inserta un producto en caso de ser nuevo en la lista.
	 */
	public void insertInMyBuy() {
		DetailBuy detailBuy = new DetailBuy();
		detailBuy.setBuy(buy);
		detailBuy.setProduct(product);
		detailBuy.setQuantity(1);
		detailBuy.setTotal(product.getPrice());
		buy.addDetailBuy(detailBuy);
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
