package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.antonio.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insertProduct(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
	}

	public void deleteProduct(int idProduct) {
		Product product = this.getProduct(idProduct);
		sessionFactory.getCurrentSession().delete(product);
	}

	public Product getProduct(int idProduct) {
		String hql = "FROM Product WHERE id = " + idProduct;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Product> listProducts = (List<Product>) query.list();

		if (listProducts != null && !listProducts.isEmpty()) {
			return listProducts.get(0);
		}

		return null;
	}

	public List<Product> listProduct() {
		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>) sessionFactory.getCurrentSession().createQuery("FROM Product").list();
		return listProduct;
	}

	public List<Product> listMyProductById(int idUser) {

		String hql = "FROM Product WHERE userId = " + idUser;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Product> listProducts = (List<Product>) query.list();

		if (listProducts != null && !listProducts.isEmpty()) {
			return listProducts;
		}

		return null;
	}

}
