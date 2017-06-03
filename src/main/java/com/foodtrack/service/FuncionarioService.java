package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	public boolean isValid(Funcionario funcionario) throws Exception {
		
		boolean valid = false;
		
		if (!StringUtils.isEmpty(funcionario.getNome()) && 
				!StringUtils.isEmpty(funcionario.getAtivo())) {
			
			if (Funcionario.ATIVO.equals(funcionario.getAtivo()) || 
					Funcionario.INATIVO.equals(funcionario.getAtivo())) {
				
				valid = true;

			} else {
				
				throw new Exception("O valor informado para o parametro {ativo}(" + funcionario.getAtivo() + ") não é válido. "
						+ "Preencher com um dos valores (\"" + Funcionario.ATIVO + "\", \"" + Funcionario.INATIVO + "\"). ");
			}
		} else {
			
			throw new Exception("Os parametros {nome} e {ativo} devem ser devidamente informados");
		}
		
		return valid;
	}
	
	public void updateFuncionario(Funcionario funcionario) throws Exception {
		if (isValid(funcionario)) {
			this.funcionarioDao.update(funcionario);
		}
	}

	public void insertFuncionario(Funcionario funcionario) throws Exception {
		if (isValid(funcionario)) {
			this.funcionarioDao.insert(funcionario);
		}
	}
}
