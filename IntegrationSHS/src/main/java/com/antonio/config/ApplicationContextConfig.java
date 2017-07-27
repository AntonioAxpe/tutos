package com.antonio.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonio.dao.BuyDAO;
import com.antonio.dao.BuyDAOImpl;
import com.antonio.dao.ProductDAO;
import com.antonio.dao.ProductDAOImpl;
import com.antonio.dao.UserDAO;
import com.antonio.dao.UserDAOImpl;
import com.antonio.model.Buy;
import com.antonio.model.Product;
import com.antonio.model.User;

@Configuration
@ComponentScan("com.antonio")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/productos");
		dataSource.setUsername("Antonio");
		dataSource.setPassword("1234.");

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.addAnnotatedClasses(Product.class, User.class, Buy.class);

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "productDAO")
	public ProductDAO getProductDAO(SessionFactory sessionFactory) {
		return new ProductDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "buyDAO")
	public BuyDAO getBuyDAO(SessionFactory sessionFactory) {
		return new BuyDAOImpl(sessionFactory);
	}

}
