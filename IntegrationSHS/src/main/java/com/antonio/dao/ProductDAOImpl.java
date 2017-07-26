package com.antonio.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.antonio.model.Product;

public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;

	public ProductDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
    public void insertProduct(Product product) {
    	sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

	@Transactional
	public void deleteProduct(int idProduct) {
		Product product = this.getProduct(idProduct);
		sessionFactory.getCurrentSession().delete(product);
	}
	
	@Transactional
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
	
	@Transactional
	public List<Product> listProduct() {
		@SuppressWarnings("unchecked")
		List<Product> listProduct = (List<Product>) sessionFactory.getCurrentSession().createCriteria(Product.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listProduct;
	}

	@Transactional
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
