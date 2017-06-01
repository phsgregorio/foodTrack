package com.foodtrack.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foodtrack.entity.Funcionario;

@Repository
@Qualifier("h2")
public class FuncionarioH2Dao implements GenericDao<Funcionario> {

	@Override
	public Collection<Funcionario> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Funcionario funcionario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(Funcionario funcionario) {
		// TODO Auto-generated method stub

	}

}
