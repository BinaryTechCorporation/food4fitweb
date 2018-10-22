package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.Medida;
import br.com.binarytech.model.Publicacao;

public class PublicacaoDAO {

	public static Boolean gravar(Publicacao publicacao) {
		return true;
	}

	public static Boolean editar(Publicacao publicacao) {
		return true;
	}

	public static int deletar(int idPublicacao) {
		String sql = "DELETE FROM publicacao WHERE idPublicacao = ?";

		int rs = 0;

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idPublicacao);

			rs = str.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return rs;
	}

	public static Boolean ativar(int idPublicacao) {
		return true;
	}

	public static Boolean desativar(int idPublicacao) {
		return true;
	}

	public static Publicacao buscar(int idPublicacao) {
		String sql = "SELECT * FROM publicacao WHERE idPublicacao = ?";

		Publicacao publicacao = new Publicacao();

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idPublicacao);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				publicacao.setIdPublicacao(rs.getInt("idPublicacao"));
				publicacao.setIdExterno(rs.getInt("idExterno"));
				publicacao.setTitulo(rs.getString("titulo"));
				publicacao.setTexto(rs.getString("texto"));
				publicacao.setIdTabela(rs.getInt("idTabela"));
				publicacao.setData(rs.getDate("data"));
				publicacao.setCurtida(rs.getInt("curtida"));
				publicacao.setVisualizacao(rs.getInt("vizualizacao"));
				publicacao.setCompartilhamento(rs.getInt("compartilhamento"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return publicacao;
	}

	public static ArrayList<Publicacao> listar(int idTabela) {
		ArrayList<Publicacao> listaPublicacao = new ArrayList<>();

		String sql = "SELECT * FROM publicacao WHERE idTabela = ?";

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);
			
			str.setInt(1, idTabela);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {

				Publicacao publicacao = new Publicacao();
				
				publicacao.setIdPublicacao(rs.getInt("idPublicacao"));
				publicacao.setIdExterno(rs.getInt("idExterno"));
				publicacao.setTitulo(rs.getString("titulo"));
				publicacao.setTexto(rs.getString("texto"));
				publicacao.setIdTabela(rs.getInt("idTabela"));
				publicacao.setData(rs.getDate("data"));
				publicacao.setCurtida(rs.getInt("curtida"));
				publicacao.setVisualizacao(rs.getInt("visualizacao"));
				publicacao.setCompartilhamento(rs.getInt("compartilhamento"));
				
				listaPublicacao.add(publicacao);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return listaPublicacao;
	}

}