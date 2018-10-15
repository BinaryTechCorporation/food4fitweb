package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.Cargo;
import br.com.binarytech.model.Medida;
import br.com.binarytech.model.Nutricional;

public class NutricionalDAO {

	public static Boolean gravar(Nutricional nutricional) {
		return true;
	}

	public static Boolean editar(Nutricional nutricional) {
		return true;
	}

	public static Boolean deletar(int idNutricional) {
		return true;
	}

	public static Boolean ativar(int idNutricional) {
		return true;
	}

	public static Boolean desativar(int idNutricional) {
		return true;
	}

	public static Nutricional buscar() {
		return new Nutricional();
	}
	
	public static int deletarPorIngrediente(int idIngrediente) {
		String sql = "DELETE FROM ingrediente_nutricional WHERE idIngrediente = ?";
		int rs = 0;

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idIngrediente);

			rs = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return rs;
	}

	public static ArrayList<Nutricional> buscarPorIngrediente(int idIngrediente) {
		String sql = "SELECT * FROM ingrediente_nutricional where idIngrediente = ?";
		
		ArrayList<Nutricional> listaNutrientes = new ArrayList<>();

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idIngrediente);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				Nutricional nutriente = new Nutricional();
				
				nutriente.setIdNutricional(rs.getInt("idNutricional"));
				nutriente.setInformacao(rs.getInt("quantidade"));
				nutriente.setVd(rs.getFloat("vd"));
				
				listaNutrientes.add(nutriente);
				
				/*medida.setIdMedida(rs.getInt("idMedida"));
				medida.setMedida(rs.getString("medida"));
				medida.setSigla(rs.getString("sigla"));*/
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaNutrientes;
	}

	public static ArrayList<Nutricional> listar() {
		ArrayList<Nutricional> listaNutricional = new ArrayList<>();

		String sql = "SELECT * FROM nutricional";

		MedidaDAO medidaDao = new MedidaDAO();

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {

				Nutricional nutricional = new Nutricional();
				nutricional.setIdNutricional((rs.getInt("idNutricional")));
				nutricional.setIdMedida(rs.getInt("idMedida"));
				nutricional.setMedida(medidaDao.buscar(nutricional.getIdMedida()).getMedida());//
				nutricional.setInformacao(rs.getInt("informacao"));
				nutricional.setSigla(medidaDao.buscar(nutricional.getIdMedida()).getSigla());

				listaNutricional.add(nutricional);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();

		return listaNutricional;
	}
}