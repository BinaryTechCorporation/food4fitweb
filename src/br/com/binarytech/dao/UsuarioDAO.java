package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.Medida;
import br.com.binarytech.model.Pessoa;
import br.com.binarytech.model.Usuario;

public class UsuarioDAO {

	public static Boolean gravar(Usuario usuario) {
		return true;
	}

	public static Boolean editar(Usuario usuario) {
		return true;
	}

	public static Boolean deletar(int idUsuario) {
		return true;
	}

	public static Boolean ativar(int idUsuario) {
		return true;
	}

	public static Boolean desativar(int idUsuario) {
		return true;
	}

	public static Usuario buscar(int idUsuario) {
		String sql = "SELECT * FROM usuario where idUsuario = ?";

		Usuario usuario = new Usuario();

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idUsuario);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = PessoaDAO.buscar(rs.getInt("idPessoa"));
				
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPeso(rs.getFloat("peso"));
				usuario.setAltura(rs.getFloat("altura"));
				usuario.setFace(rs.getString("face"));
				usuario.setDigital(rs.getString("digital"));
				usuario.setIdPessoa(rs.getInt("idPessoa"));
				
				usuario.setIdPessoa(pessoa.getIdPessoa());
				usuario.setNomeSocial(pessoa.getNomeSocial());
				usuario.setEmail(pessoa.getEmail());
				usuario.setDataNascimento(pessoa.getDataNascimento());
				usuario.setCpf(pessoa.getCpf());
				usuario.setRg(pessoa.getRg());
				usuario.setSexo(pessoa.getSexo());
				usuario.setNome(pessoa.getNome());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}

	public static ArrayList<Usuario> listar() {
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();

		String sql = "SELECT * FROM usuario";

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setIdPessoa(rs.getInt("idPessoa"));
				usuario.setCpf(PessoaDAO.buscar(usuario.getIdPessoa()).getCpf());
				usuario.setDataNascimento(PessoaDAO.buscar(usuario.getIdPessoa()).getDataNascimento());
				usuario.setEmail(PessoaDAO.buscar(usuario.getIdPessoa()).getEmail());
				usuario.setNome(PessoaDAO.buscar(usuario.getIdPessoa()).getNome());
				usuario.setNomeSocial(PessoaDAO.buscar(usuario.getIdPessoa()).getNomeSocial());
				usuario.setRg(PessoaDAO.buscar(usuario.getIdPessoa()).getRg());
				usuario.setSexo(PessoaDAO.buscar(usuario.getIdPessoa()).getSexo());
				usuario.setTelefone(PessoaDAO.buscar(usuario.getIdPessoa()).getTelefone());
				
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPeso(rs.getFloat("peso"));
				usuario.setAltura(rs.getFloat("altura"));
				usuario.setFace(rs.getString("face"));
				usuario.setDigital(rs.getString("digital"));
				
				listaUsuarios.add(usuario);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsuarios;
	}

}