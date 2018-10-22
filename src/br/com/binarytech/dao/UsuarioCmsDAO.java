package br.com.binarytech.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.binarytech.jdbc.BancoWEB;
import br.com.binarytech.model.Medida;
import br.com.binarytech.model.UsuarioCms;

public class UsuarioCmsDAO{

	public static Boolean gravar(UsuarioCms usuarioCms){
		
		Boolean sucesso = false;

		String sql = "INSERT INTO usuario_cms SET idFuncionario = ?, idPermissao = ?, status = ?, senha = ?, login = ?";
		
		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);
			
			str.setInt(1, usuarioCms.getIdFuncionario());
			str.setInt(2, usuarioCms.getIdPermissao());
			str.setBoolean(3, usuarioCms.getStatus());
			str.setString(4, usuarioCms.getSenha());
			str.setString(5, usuarioCms.getLogin());
			
			sucesso = str.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BancoWEB.fecharConexao();
		
		return sucesso;

	}	
	public static Boolean editar(UsuarioCms UsuarioCms){ 
		
		Boolean sucesso = false;
		
		return sucesso;
		
	}  

	public static Boolean deletar(int idUsuarioCms){ 
		
		Boolean sucesso = false;
		
		return sucesso;
		
	}  

	public static Boolean mudarStatus (int idUsuarioCms, int status){ 
		
		Boolean sucesso = false;
		
		return sucesso;
		
	}  


	public static UsuarioCms buscar(int idUsuario){
		
		String sql = "SELECT * FROM usuario_cms where idUsuarioCms = ?";

		UsuarioCms usuarioCms = new UsuarioCms();

		try {
			PreparedStatement str = BancoWEB.abrirConexao().prepareStatement(sql);

			str.setInt(1, idUsuario);

			ResultSet rs = str.executeQuery();

			while (rs.next()) {

				usuarioCms.setIdUsuarioCms(rs.getInt("idUsuarioCms"));
				usuarioCms.setIdFuncionario(rs.getInt("idFuncionario"));
				usuarioCms.setIdPermissao(rs.getInt("idPermissao"));
				usuarioCms.setStatus(rs.getBoolean("status"));
				usuarioCms.setSenha(rs.getString("senha"));
				usuarioCms.setLogin(rs.getString("login"));
				/***Ainda é necessário implementas os setters herdados de Pessoa e Funcionário, tirando o setNome();***/
				usuarioCms.setNome(PessoaDAO.buscar(FuncionarioDAO.buscar(usuarioCms.getIdFuncionario()).getIdPessoa()).getNome());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BancoWEB.fecharConexao();
		return usuarioCms;
		
	} 

	public static UsuarioCms listar(){ 
		
		return new UsuarioCms();
		
	}    

}