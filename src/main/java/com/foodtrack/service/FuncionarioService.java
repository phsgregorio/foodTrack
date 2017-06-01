package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodtrack.dao.FuncionarioDAO;
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
	private FuncionarioDAO funcionarioDAO;
	
	public Collection<Funcionario> getAllFuncionarios() {
		return this.funcionarioDAO.getAllFuncionarios();
	}
	
	public Funcionario getFuncionarioById(int id) {
		return this.funcionarioDAO.getFuncionarioById(id);
	}

	public void removeFuncionarioById(int id) {
		this.funcionarioDAO.removeFuncionarioById(id);
	}
	
	public void updateFuncionario(Funcionario funcionario) {
		this.funcionarioDAO.updateFuncionario(funcionario);
	}

	public void insertFuncionario(Funcionario funcionario) {
		this.funcionarioDAO.insertFuncionario(funcionario);
	}
}
