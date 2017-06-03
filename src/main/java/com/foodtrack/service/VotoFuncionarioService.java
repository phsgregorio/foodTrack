package com.foodtrack.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.foodtrack.dao.VotoFuncionarioDao;
import com.foodtrack.entity.VotoFuncionario;

/**
 * VotoFuncionario Business Logic 
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
	
	/**
	 * Retorna todos os votos para a data informada
	 * @param data
	 * @return
	 */
	public Collection<VotoFuncionario> getAllByDate(String data) {
		return votoFuncionarioDao.getAllByDate(data);
	}

	/**
	 * Serviço para inserção de um novo voto de funcionário
	 * @param votoFuncionario
	 * @throws Exception
	 */
	public void insertVotoFuncionario(VotoFuncionario votoFuncionario) throws Exception {
		
		if (StringUtils.isEmpty(votoFuncionario.getDataVotacao())) {
			String dataHoje = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			votoFuncionario.setDataVotacao(dataHoje);
		}
		
		if (votoFuncionario.getFuncionario().isAtivo()) {

			VotoFuncionario votadoAnteriormente = votoFuncionarioDao.retrieveVotoFuncionarioByDate(votoFuncionario.getIdFuncionario(), votoFuncionario.getDataVotacao());
			
			if (votadoAnteriormente==null) {
				this.votoFuncionarioDao.insert(votoFuncionario);
			} else {
				throw new Exception("Funcionario votou anteriormente.");
			}
		} else {
			throw new Exception("Funcionario desativado.");
		}
	}
}
