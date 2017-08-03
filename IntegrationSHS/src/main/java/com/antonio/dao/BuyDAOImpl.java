package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.antonio.model.Buy;

@Repository
public class BuyDAOImpl implements BuyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public BuyDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int createaBuy(Buy buy) {
		sessionFactory.getCurrentSession().saveOrUpdate(buy);
		String id = sessionFactory.getCurrentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()
				.toString();

		return Integer.parseInt(id);
	}

	public void deleteBuy(int idBuy) {
	}

	public void getBuy(int idBuy) {
	}

	public List<Buy> listBuy() {
		return null;
	}

	public Buy getBuyUser(int userId, String status) {

		String select = "FROM Buy WHERE userId=" + userId + " AND status='" + status + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(select);

		@SuppressWarnings("unchecked")
		List<Buy> listBuy = (List<Buy>) query.list();

		if (listBuy != null && !listBuy.isEmpty()) {
			return listBuy.get(0);
		}
		return null;
	}

	@Override
	public void addProductToMyList(Buy buy) {
	}

	@Override
	public void deleteProduct(int idProduct) {
	}

}
