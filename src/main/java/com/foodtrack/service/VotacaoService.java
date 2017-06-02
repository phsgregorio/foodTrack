package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.foodtrack.dao.VotacaoDao;
import com.foodtrack.entity.Restaurante;
import com.foodtrack.entity.Votacao;
import com.foodtrack.entity.VotoFuncionario;

/**
 * Votacao Business Logic 
 * @author pedro.gregorio
 *
 */
@Service
public class VotacaoService {

	@Autowired
	@Qualifier("fake")
	private VotacaoDao votacaoDao;
	
	public Collection<Votacao> getAll() {
		return this.votacaoDao.getAll();
	}
	
	public Collection<Votacao> getAllByDate(String data) {
		return votacaoDao.getAllByDate(data);
	}
	
	public boolean isRestauranteJaEscolhido(Restaurante restaurante) {
		return votacaoDao.isRestauranteJaEscolhido(restaurante);
	}
	
	public Votacao getRestauranteDia(String data) {
		return votacaoDao.getRestauranteDia(data);
	}
	
	public Votacao getVotacaoByRestauranteData(int idRestaurante, String data) {
		return votacaoDao.getVotacaoByRestauranteData(idRestaurante, data);
	}

	public void insertVotacao(Votacao votacao) throws Exception {
		
		if (votacao.getIdRestaurante()!=0 && 
				!StringUtils.isEmpty(votacao.getDataVotacao())) {
			
			this.votacaoDao.insert(votacao);
		} else {
			
			throw new Exception("O Objeto Votacao deve ter as propriedades {id}, {idRestaurante} e {dataVotacao} devidamente informadas.");
		}
	}

	public void insertVotacao(VotoFuncionario votoFuncionario) throws Exception {

		if (votoFuncionario.getRestaurante()!=null && votoFuncionario.getDataVotacao()!=null) {
			
			Votacao votacao = getVotacaoByRestauranteData(votoFuncionario.getIdRestaurante(), votoFuncionario.getDataVotacao());
			
			if (votacao==null) {
				
				votacao = new Votacao(this.votacaoDao.getLastId(), votoFuncionario.getIdRestaurante(), 1, votoFuncionario.getDataVotacao());
				this.votacaoDao.insert(votacao);
			} else {
				
				votacao.setNumeroVotos(votacao.getNumeroVotos()+1);
				this.votacaoDao.update(votacao);
			}
		} else {
			throw new Exception("Os parâmetros idRestaurante e dataVotacao devem ser devidamente informados");
		}
	}
}
