package com.antonio.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.dao.ProductDAO;
import com.antonio.model.Product;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Clase de configuración Struts el cual ejecuta las acciones llamadas por el
 * usuario. Las anotaciones sustituyen al archivo xml, struts.xml.
 * 
 * @author Antonio Soto
 */
@Action("listProduct")
@ResultPath("/WEB-INF/views")
@Result(name = "success", location = "ProductList.jsp")
public class ListProductAction extends ActionSupport {

	@Autowired
	private ProductDAO productDAO;
	private List<Product> listProduct;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public String execute() {
		listProduct = productDAO.listProduct();
		return SUCCESS;
	}

}
