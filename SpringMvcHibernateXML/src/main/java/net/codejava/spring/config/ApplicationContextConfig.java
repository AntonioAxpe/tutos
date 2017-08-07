package net.codejava.spring.config;

import java.util.Properties;

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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import net.codejava.spring.dao.SportDAO;
import net.codejava.spring.dao.SportDAOImpl;
import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.dao.UserDAOImpl;
import net.codejava.spring.model.Sport;
import net.codejava.spring.model.User;


/**
 * Clase de configuración de ApplicationContextConfig, sustituye al xml. en
 * concreto al serlvet-context.xml
 * 
 * @author avicentesh
 */
@Configuration // Indica que es un archivo de configuración de Spring
@ComponentScan("net.codejava.spring") // Indica el paquete para las anotaciones
@EnableTransactionManagement // Habilita las transacciones hechas por anotaciones
public class ApplicationContextConfig {

	/**
	 * Configura la resolición de las vistas en páginas JSP
	 */
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Configura el DataSource el cual será utilizado con SessionFactory.
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/usuarios");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	/**
	 * Configuración del Bean SessionFactory
	 */
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		// Crea el objeto de Sessiond de Hibernate.
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		// Indica que carge la clase.
		sessionBuilder.addAnnotatedClasses(User.class, Sport.class);

		// En caso de agregar más clases
		// sessionBuilder.addAnnotatedClasses(User.class, Object.class);

		return sessionBuilder.buildSessionFactory();
	}

	/**
	 * Indica las propiedades de Hibernate.
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
	}

	/**
	 * Se encarga de realizar las transacciones automaticamente.
	 */
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = 
				new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	/**
	 * Bean que es inyectado en el Controlador.
	 */
	@Autowired
	@Bean(name = "userDao")
	public UserDAO getUserDao(SessionFactory sessionFactory) {
	    return new UserDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "sportDao")
	public SportDAO getSportDao(SessionFactory sessionFactory) {
		return new SportDAOImpl(sessionFactory);
	}
}
