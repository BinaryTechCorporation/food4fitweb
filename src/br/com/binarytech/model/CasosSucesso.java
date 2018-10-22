package br.com.binarytech.model;

import java.util.*;

/**
 * 
 */
public class CasosSucesso {

    /**
     * Default constructor
     */
    public CasosSucesso() {
    }

    private int idCasosSucesso;
    private int idUsuario;
    private String titulo;
    private String texto;
    private String caminhoFoto;
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}
	public int getIdCasosSucesso() {
		return idCasosSucesso;
	}
	public void setIdCasosSucesso(int idCasosSucesso) {
		this.idCasosSucesso = idCasosSucesso;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
    
    

}