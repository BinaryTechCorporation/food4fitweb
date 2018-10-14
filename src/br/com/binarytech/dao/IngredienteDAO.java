package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.Ingrediente;
import br.com.binarytech.model.Nutricional;

public class IngredienteDAO {

	public static int gravar(Ingrediente ingrediente) {
		int sucesso = 0;

		String sql = "INSERT INTO ingrediente SET idIngrediente = ?, ingrediente = ?, idMedida = ?, estoque = ?, margemSeguranca = ?";

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			// str.setInt(1, usuarioCms.getIdFuncionario());
			str.setInt(1, ingrediente.getIdIngrediente());
			str.setString(2, ingrediente.getIngrediente());
			str.setInt(3, ingrediente.getIdMedida());
			str.setInt(4, ingrediente.getEstoque());
			str.setInt(5, ingrediente.getMargemSeguranca());

			sucesso = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BancoWEB.fecharConexao();

		return sucesso;
	}

	public static int buscarUltimoId() {
		String sql = "SELECT MAX(idIngrediente) from ingrediente";
		int id = 0;

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				id = rs.getInt("MAX(idIngrediente)");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();

		return id;
	}

	public static Boolean editar(Ingrediente ingrediente) {
		Ingrediente i = new Ingrediente();

		return true;
	}

	public static int gravarNutrientes(int id, ArrayList<Nutricional> listaNutrientes) {
		int sucesso = 0;

		String sql = "INSERT INTO ingrediente_nutricional SET idIngrediente = ?, idNutricional = ?, quantidade = ?, vd = ?";
		for (Nutricional n : listaNutrientes) {
			try {
				PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

				// str.setInt(1, usuarioCms.getIdFuncionario());
				str.setInt(1, id);
				str.setInt(2, n.getIdNutricional());
				str.setInt(3, Integer.parseInt(n.getInformacao()));
				str.setFloat(4, n.getVd());

				sucesso = str.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		BancoWEB.fecharConexao();

		return sucesso;
	}

	public static Boolean deletar(int idIngrediente) {
		return true;
	}

	public static Boolean ativar(int idIngrediente) {
		return true;
	}

	public static Boolean desativar(int idIngrediente) {
		return true;
	}

	public static Ingrediente buscar() {
		return new Ingrediente();
	}

	public static Ingrediente listar() {
		return new Ingrediente();
	}

}