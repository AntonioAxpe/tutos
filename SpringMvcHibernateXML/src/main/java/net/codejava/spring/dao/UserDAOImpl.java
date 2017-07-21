package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.User;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}

	@Override
	@Transactional
	public User get(int idUser) {
		
		// Se realiza una consulta con el ID del usuario.
		// **NOTA**: La tabla y los campos de la consulta son nombres del POJO!!
		String hql = "from User where id=" + idUser;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		// De la consulta se obtiene la lista de usuarios consultada
		@SuppressWarnings("unchecked")
		List<User> listaUsuarios = (List<User>) query.list();
		
		// Si la lista obtenida no está vacia ni nula.
		if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
			return listaUsuarios.get(0);
		}
		
		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void delete(int idUser) {
		User usuario = new User();
		usuario.setId(idUser);
		sessionFactory.getCurrentSession().delete(usuario);
	}

}
