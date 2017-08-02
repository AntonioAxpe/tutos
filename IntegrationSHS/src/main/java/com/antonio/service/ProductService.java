package com.antonio.service;

import java.util.List;

import com.antonio.model.Product;

public interface ProductService {

	void insertProduct(Product product);

	void deleteProduct(int idProduct);

	Product getProduct(int idProduct);

	List<Product> listProduct();

	List<Product> listMyProductById(int idUser);

}
