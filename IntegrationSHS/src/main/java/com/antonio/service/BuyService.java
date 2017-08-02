package com.antonio.service;

import java.util.List;

import com.antonio.model.Buy;

public interface BuyService {

	int createaBuy(Buy buy);

	void deleteBuy(int idBuy);

	void getBuy(int idBuy);

	List<Buy> listBuy();
	
	Buy getBuyUser(int userId, String status);
	
	void addProductToMyList(Buy buy);
	
	void deleteProduct(int idProduct);
	
}
