package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.antonio.model.Buy;
import com.antonio.model.DetailBuy;

public class DetailBuyDAOImpl implements DetailBuyDAO {

	private SessionFactory seccionFactory;

	public DetailBuyDAOImpl(SessionFactory seccionFactory) {
		this.seccionFactory = seccionFactory;
	}

	@Override
	@Transactional
	public List<DetailBuy> getDetailBuyByBuyId(int buyId) {
		String consult = "FROM Buy WHERE total = 0";
		Query query = seccionFactory.getCurrentSession().createQuery(consult);

		@SuppressWarnings("unchecked")
		List<Buy> u = (List<Buy>) query.list();
		/*List<DetailBuy> listProducts = (List<DetailBuy>) query.list();

		if (listProducts != null && !listProducts.isEmpty()) {
			return listProducts;
		}*/

		return null;
	}

}
