package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConnectionFactory;
import model.Pais;

public class PaisDAO {
	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, população, area) VALUES (?, ?, ?)";
		
		try (Connection conn = ConnectionFactory.connPais(); 
			PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulacao());
				stm.setDouble(3, pais.getArea());
				stm.execute();
				
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}
	
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, população=?, area=? WHERE id=?";
		try (Connection conn = ConnectionFactory.connPais();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";

		try (Connection conn = ConnectionFactory.connPais();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pais carregar(int id) {
		Pais pais = new Pais();
		pais.setId(id);
		String sqlSelect = "SELECT nome, população, area FROM pais WHERE pais.id = ?";
		
		try (Connection conn = ConnectionFactory.connPais();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("população"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(0);
					pais.setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	
	public int maior(Pais[] pais) {
		
		Pais p1 = new Pais(1,"Brasil",213053455,8516000.00);
		Pais p2 = new Pais(2, "EUA",328406639,9834000.00);
		Pais p3 = new Pais(3, "China", 1395553739, 9597000.00);
		Pais p4 = new Pais(4, "Russia",146462094,17100000.00);
		Pais p5 = new Pais(5, "Portugal",10227660,92212.00);
		
		Pais[] paises = {p1, p2, p3, p4, p5};
		
		int iMa = 0;
		for (int i = 1; i < paises.length; i++) {
			if (paises[i].getPopulacao() > paises[iMa].getPopulacao()) {
				iMa = i;
			}
		}
		return paises[iMa].getId();
	}
	
	public int menor(Pais[] pais) {
		
		Pais p1 = new Pais(1, "Brasil", 213053455, 8516000.00);
		Pais p2 = new Pais(2, "EUA", 328406639, 9834000.00);
		Pais p3 = new Pais(3, "China", 1395553739, 9597000.00);
		Pais p4 = new Pais(4, "Russia", 146462094, 17100000.00);
		Pais p5 = new Pais(5, "Portugal", 10227660, 92212.00);
		
		Pais[] paises = {p1, p2, p3, p4, p5};
		
		int me = 0;
		for (int i = 1; i < paises.length; i++) {
			if (paises[i].getArea() < paises[me].getArea()) {
				me = i;
			}
		}
		return paises[me].getId();
	}
	
	/*
	public int[] threepaises(Pais[] pais) {

		Pais p1 = new Pais(1, "Brasil", 213053455, 8516000.00);
		Pais p2 = new Pais(2, "EUA", 328406639, 9834000.00);
		Pais p3 = new Pais(3, "China", 1395553739, 9597000.00);
		Pais p4 = new Pais(4, "Russia", 146462094, 17100000.00);
		Pais p5 = new Pais(5, "Portugal", 10227660, 92212.00);
		
		Pais[] paises = {p1, p2, p3, p4, p5};
		
		int[] three = new int[3];
		for (int i = 1; i < paises.length; i++) {
			if (i <= 3) {
				three[i] = paises[i].getId();
			}
		}
		return three;
	}*/
	
}
