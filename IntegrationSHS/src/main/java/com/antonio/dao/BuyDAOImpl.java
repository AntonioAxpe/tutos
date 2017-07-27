package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.antonio.model.Buy;

public class BuyDAOImpl implements BuyDAO {

	SessionFactory sessionFactory;

	public BuyDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public int createaBuy(Buy buy) {
		sessionFactory.getCurrentSession().save(buy);
		String id = sessionFactory.getCurrentSession().createSQLQuery("SELECT LAST_INSERT_ID()")
		.uniqueResult().toString();
		
		return Integer.parseInt(id);
	}

	@Transactional
	public void deleteBuy(int idBuy) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public void getBuy(int idBuy) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public List<Buy> listBuy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
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

}
