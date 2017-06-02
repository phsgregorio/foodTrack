package com.foodtrack.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
	
	public boolean isRestauranteJaEscolhido(Restaurante restaurante, String data) {
		return votacaoDao.isRestauranteJaEscolhido(restaurante, data);
	}
	
	public Votacao getRestauranteDia(String data) {
		return votacaoDao.getRestauranteDia(data);
	}
	
	public Votacao getVotacaoByRestauranteData(int idRestaurante, String data) {
		return votacaoDao.getVotacaoByRestauranteData(idRestaurante, data);
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
			throw new Exception("As propriedades do objeto VotoFuncionario {idRestaurante} e {dataVotacao} devem ser devidamente informadas.");
		}
	}
}
