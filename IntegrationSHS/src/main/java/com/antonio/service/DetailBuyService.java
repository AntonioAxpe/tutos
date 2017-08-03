package com.antonio.service;

import java.util.List;

import com.antonio.model.DetailBuy;

public interface DetailBuyService {

	public List<DetailBuy> getDetailBuyByBuyId(int buyId);

	public void deleteDetailBuyFromMyCart(DetailBuy detailBuy);
}
