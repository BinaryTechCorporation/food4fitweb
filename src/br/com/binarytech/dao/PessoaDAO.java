package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.Medida;
import br.com.binarytech.model.Pessoa;

public class PessoaDAO {

	public static Boolean gravar(Pessoa pessoa) {
		return true;
	}

	public static Boolean editar(Pessoa pessoa) {
		return true;
	}

	public static Boolean deletar(int idPessoa) {
		return true;
	}

	public static Boolean ativar(int idPessoa) {
		return true;
	}

	public static Boolean desativar(int idPessoa) {
		return true;
	}

	public static Pessoa buscar(int idPessoa) {
		String sql = "SELECT * FROM pessoa WHERE idPessoa = ?";

		Pessoa pessoa = new Pessoa();

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idPessoa);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setDataNascimento(rs.getDate("nascimento"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setIdPessoa(rs.getInt("idPessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setNomeSocial(rs.getString("nomeSocial"));
				pessoa.setRg(rs.getString("rg"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setTelefone(TelefoneDAO.buscar(pessoa.getIdPessoa(), 2));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pessoa;
	}

	public static Pessoa listar() {
		return new Pessoa();
	}

}