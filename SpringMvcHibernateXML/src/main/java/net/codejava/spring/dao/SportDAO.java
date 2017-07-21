package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Sport;

public interface SportDAO {

	public void insertSport(Sport sport);
	public void deleteSport(int idSport);
	public void updateSport(Sport sport);
	public Sport findSport(int idSport);
	public List<Sport> listSport();
	
}
