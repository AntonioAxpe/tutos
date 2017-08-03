package com.antonio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.dao.DetailBuyDAO;
import com.antonio.model.DetailBuy;

@Service
public class DetailBuyServiceImpl implements DetailBuyService {

	@Autowired
	private DetailBuyDAO detailBuyDAO;
	
	@Override
	@Transactional
	public List<DetailBuy> getDetailBuyByBuyId(int buyId) {
		return detailBuyDAO.getDetailBuyByBuyId(buyId);
	}

	@Override
	@Transactional
	public void deleteDetailBuyFromMyCart(DetailBuy detailBuy) {
		detailBuyDAO.deleteDetailBuyFromMyCart(detailBuy);
	}

}
