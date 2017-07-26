package com.antonio.dao;

import java.util.List;

import com.antonio.model.Product;

public interface ProductDAO {

	void insertProduct(Product product);
	
	void deleteProduct(int idProduct);
	
	Product getProduct(int idProduct);
	
	List<Product> listProduct();
	
	List<Product> listMyProductById(int idUser);
	
}