package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.CasosSucesso;
import br.com.binarytech.model.Medida;

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

	public static int deletar(int idCasosSucesso) {
		String sql = "DELETE FROM casos_sucesso WHERE idCasosSucesso = ?";

		int rs = 0;

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idCasosSucesso);

			rs = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return rs;
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

	public static ArrayList<CasosSucesso> listar() {
		ArrayList<CasosSucesso> listaCasos = new ArrayList<>();

		String sql = "SELECT * FROM casos_sucesso";

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {

				CasosSucesso casoSucesso = new CasosSucesso();

				casoSucesso.setIdCasosSucesso(rs.getInt("idCasosSucesso"));
				casoSucesso.setIdUsuario(rs.getInt("idUsuario"));
				casoSucesso.setTexto(rs.getString("texto"));
				casoSucesso.setTitulo(rs.getString("titulo"));
				casoSucesso.setCaminhoFoto(MidiaDAO.buscar(8, casoSucesso.getIdCasosSucesso()).getCaminho());

				listaCasos.add(casoSucesso);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return listaCasos;
	}

}