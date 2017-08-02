package com.antonio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.dao.ProductDAO;
import com.antonio.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional
	public void insertProduct(Product product) {
		productDAO.insertProduct(product);
	}

	@Override
	@Transactional
	public void deleteProduct(int idProduct) {
		productDAO.deleteProduct(idProduct);
	}

	@Override
	@Transactional
	public Product getProduct(int idProduct) {
		return productDAO.getProduct(idProduct);
	}

	@Override
	@Transactional
	public List<Product> listProduct() {
		return productDAO.listProduct();
	}

	@Override
	@Transactional
	public List<Product> listMyProductById(int idUser) {
		return productDAO.listMyProductById(idUser);
	}

}
