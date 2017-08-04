package com.antonio.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.model.Product;
import com.antonio.service.ProductService;
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
	private ProductService productService;
	private List<Product> listProduct;

	public List<Product> getListProduct() {
		return listProduct;
	}

	public String execute() {
		listProduct = productService.listProduct();
		return SUCCESS;
	}

}
