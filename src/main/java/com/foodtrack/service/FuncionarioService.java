package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodtrack.dao.GenericDao;
import com.foodtrack.entity.Funcionario;

/**
 * Funcionario Business Logic 
 * @author pedro.gregorio
 *
 */
@Service
public class FuncionarioService {

	@Autowired
	@Qualifier("fake")
	private GenericDao<Funcionario> funcionarioDao;
	
	public Collection<Funcionario> getAllFuncionarios() {
		return this.funcionarioDao.getAll();
	}
	
	public Funcionario getFuncionarioById(int id) {
		return (Funcionario) this.funcionarioDao.getById(id);
	}

	public void removeFuncionarioById(int id) {
		this.funcionarioDao.removeById(id);
	}
	
	public void updateFuncionario(Funcionario funcionario) {
		this.funcionarioDao.update(funcionario);
	}

	public void insertFuncionario(Funcionario funcionario) {
		this.funcionarioDao.insert(funcionario);
	}
}
