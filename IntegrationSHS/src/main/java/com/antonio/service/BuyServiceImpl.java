package com.antonio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.dao.BuyDAO;
import com.antonio.model.Buy;

@Service
public class BuyServiceImpl implements BuyService {

	@Autowired
	private BuyDAO buyDAO;
	
	@Override
	@Transactional
	public int createaBuy(Buy buy) {
		return buyDAO.createaBuy(buy);
	}

	@Override
	@Transactional
	public void deleteBuy(int idBuy) {
		buyDAO.deleteBuy(idBuy);
	}

	@Override
	@Transactional
	public void getBuy(int idBuy) {
		buyDAO.getBuy(idBuy);
	}

	@Override
	@Transactional
	public List<Buy> listBuy() {
		return buyDAO.listBuy();
	}

	@Override
	@Transactional
	public Buy getBuyUser(int userId, String status) {
		return buyDAO.getBuyUser(userId, status);
	}

	@Override
	@Transactional
	public void addProductToMyList(Buy buy) {
		buyDAO.addProductToMyList(buy);
	}

	@Override
	@Transactional
	public void deleteProduct(int idProduct) {
		buyDAO.deleteProduct(idProduct);
	}

}
