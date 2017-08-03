package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.antonio.model.Buy;
import com.antonio.model.DetailBuy;
import com.antonio.model.Product;

@Repository
public class DetailBuyDAOImpl implements DetailBuyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public DetailBuyDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<DetailBuy> getDetailBuyByBuyId(int buyId) {
		String consult = "FROM DetailBuy WHERE primaryKey.product.id = 7";
		Query query = sessionFactory.getCurrentSession().createQuery(consult);

		@SuppressWarnings("unchecked")
		// List<Buy> u = (List<Buy>) query.list();
		List<DetailBuy> listProducts = (List<DetailBuy>) query.list();

		if (listProducts != null && !listProducts.isEmpty()) {
			return listProducts;
		}

		return null;
	}

	@Override
	public void deleteDetailBuyFromMyCart(DetailBuy detailBuy) {
		try {
			sessionFactory.getCurrentSession().delete(detailBuy);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
