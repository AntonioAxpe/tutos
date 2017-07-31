package com.antonio.dao;

import java.util.List;

import com.antonio.model.DetailBuy;

public interface DetailBuyDAO {

	public List<DetailBuy> getDetailBuyByBuyId(int buyId);
	
}
