package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.CasosSucesso;

public class CasosSucessoDAO {

	public static int gravar(CasosSucesso casosSucesso) {
		int sucesso = 0;

		String sql = "INSERT INTO casos_sucesso SET idUsuario = ?, titulo = ?, texto = ?";

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);
			
			str.setInt(1, casosSucesso.getIdUsuario());
			str.setString(2, casosSucesso.getTitulo());
			str.setString(3, casosSucesso.getTexto());

			sucesso = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BancoWEB.fecharConexao();

		return sucesso;
	}

	public static Boolean editar(CasosSucesso casosSucesso) {
		return true;
	}

	public static Boolean deletar(int idCasosSucesso) {
		return true;
	}

	public static Boolean ativar(int idCasosSucesso) {
		return true;
	}

	public static Boolean desativar(int idCasosSucesso) {
		return true;
	}

	public static CasosSucesso buscar() {
		return new CasosSucesso();
	}
	
	public static int buscarUltimoId() {
		String sql = "SELECT MAX(idCasosSucesso) FROM casos_sucesso";
		int id = 0;

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				id = rs.getInt("MAX(idCasosSucesso)");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();

		return id;
	}

	public static CasosSucesso listar() {
		return new CasosSucesso();
	}

}