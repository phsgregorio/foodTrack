package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodtrack.dao.VotoFuncionarioDao;
import com.foodtrack.entity.VotoFuncionario;

/**
 * Funcionario Business Logic 
 * @author pedro.gregorio
 *
 */
@Service
public class VotoFuncionarioService {

	@Autowired
	@Qualifier("fake")
	private VotoFuncionarioDao votoFuncionarioDao;
	
	public Collection<VotoFuncionario> getAll() {
		return this.votoFuncionarioDao.getAll();
	}
	
	public Collection<VotoFuncionario> getAllByDate(String data) {
		return votoFuncionarioDao.getAllByDate(data);
	}

	public void insertVotoFuncionario(VotoFuncionario votoFuncionario) throws Exception {
		
		if (votoFuncionario.getFuncionario().isAtivo()) {

			VotoFuncionario votadoAnteriormente = votoFuncionarioDao.retrieveVotoFuncionarioByDate(votoFuncionario.getIdFuncionario(), votoFuncionario.getDataVotacao());
			
			if (votadoAnteriormente==null) {
				this.votoFuncionarioDao.insert(votoFuncionario);
			} else {
				throw new Exception("Funcionário já votou anteriormente");
			}
		} else {
			throw new Exception("Funcionário está desativado no momento");
		}
	}
}
