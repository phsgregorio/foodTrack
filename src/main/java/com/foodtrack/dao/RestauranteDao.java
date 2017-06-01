package com.foodtrack.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Restaurante;

@Repository
@Qualifier("fake")
public class RestauranteDao implements GenericDao<Restaurante> {

	private static Map<Integer, Restaurante> restaurantes;
	
	static {
		
		restaurantes = new HashMap<Integer, Restaurante>() {

			private static final long serialVersionUID = 1L;

			{
				put(1, new Restaurante(1, "Pedro", "ativo"));
				put(2, new Restaurante(2, "Henrique", "inativo"));
				put(3, new Restaurante(3, "Luiz", "ativo"));
			}
		};
	}
	
	@Override
	public Collection<Restaurante> getAll() {
		return restaurantes.values();
	}

	@Override
	public Restaurante getById(int id) {
		return restaurantes.get(id);
	}

	@Override
	public void removeById(int id) {
		restaurantes.remove(id);
	}
	
	@Override
	public void update(Restaurante Restaurante) {
		
		Restaurante r = restaurantes.get(Restaurante.getId());
		r.setNome(Restaurante.getNome());
		r.setEndereco(Restaurante.getEndereco());
		
		restaurantes.put(Restaurante.getId(), r);
	}
	
	@Override
	public void insert(Restaurante Restaurante) {
		restaurantes.put(Restaurante.getId(), Restaurante);
	}
}