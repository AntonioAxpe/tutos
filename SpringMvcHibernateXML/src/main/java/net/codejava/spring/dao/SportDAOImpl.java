package net.codejava.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.Sport;

public class SportDAOImpl implements SportDAO {

	private SessionFactory sessionFactory;
	
	/**
	 * Constructor
	 */
	public SportDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void insertSport(Sport sport) {
		sessionFactory.getCurrentSession().saveOrUpdate(sport);
	}

	@Override
	@Transactional
	public void deleteSport(int idSport) {
		Sport sportDelete = new Sport();
		sportDelete.setId(idSport);
		sessionFactory.getCurrentSession().delete(sportDelete);
	}

	@Override
	@Transactional
	public void updateSport(Sport sport) {
		sessionFactory.getCurrentSession().saveOrUpdate(sport);
	}

	@Override
	@Transactional
	public Sport findSport(int idSport) {
		String query = "from Sport where id = " + idSport;
		Query result = sessionFactory.getCurrentSession().createQuery(query);
		
		@SuppressWarnings("unchecked")
		List<Sport> lista = (List<Sport>) result.list();
		if (lista != null && !lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Sport> listSport() {
		@SuppressWarnings("unchecked")
		List<Sport> listaDeportes = null;
		listaDeportes = (List<Sport>) sessionFactory.getCurrentSession()
				.createCriteria(Sport.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		return listaDeportes;
	}

}
