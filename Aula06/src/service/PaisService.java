package service;

import model.Pais;
import dao.PaisDAO;

public class PaisService {
	PaisDAO dao = new PaisDAO();

	public int criar(Pais pais) {
		return dao.criar(pais);
	}

	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}

	public void excluir(int id) {
		dao.excluir(id);
	}

	public Pais carregar(int id) {
		return dao.carregar(id);
	}

	public int maior(Pais[] pais) {
		return dao.maior(pais);
	}

	public int menor(Pais[] pais) {
		return dao.menor(pais);
	}
	/*
	 * public Pais carregar2(int[] id) { return dao.carregar2(id); } public int[]
	 * threepaises(Pais[] pais) { return dao.threepaises(pais); }
	 */
}
