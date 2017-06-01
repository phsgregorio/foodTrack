package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodtrack.dao.GenericDao;
import com.foodtrack.entity.Restaurante;

/**
 * Restaurante Business Logic 
 * @author pedro.gregorio
 *
 */
@Service
public class RestauranteService {

	@Autowired
	@Qualifier("fake")
	private GenericDao<Restaurante> restauranteDao;
	
	public Collection<Restaurante> getAllRestaurantes() {
		return this.restauranteDao.getAll();
	}
	
	public Restaurante getRestauranteById(int id) {
		return (Restaurante) this.restauranteDao.getById(id);
	}

	public void removeRestauranteById(int id) {
		this.restauranteDao.removeById(id);
	}
	
	public void updateRestaurante(Restaurante restaurante) {
		this.restauranteDao.update(restaurante);
	}

	public void insertRestaurante(Restaurante restaurante) {
		this.restauranteDao.insert(restaurante);
	}
}