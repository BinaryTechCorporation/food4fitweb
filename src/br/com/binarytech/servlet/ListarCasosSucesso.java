package br.com.binarytech.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.CasosSucessoDAO;
import br.com.binarytech.dao.UsuarioDAO;
import br.com.binarytech.model.CasosSucesso;

/**
 * Servlet implementation class ListarCasosSucesso
 */
@WebServlet("/ListarCasosSucesso")
public class ListarCasosSucesso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarCasosSucesso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		ArrayList<CasosSucesso> listaCasos = new ArrayList<>();
		listaCasos = CasosSucessoDAO.listar();
		
		for (CasosSucesso caso : listaCasos) {
			writer.print("<tr class='header_tabela'>"
					+ "<td>"
					+ "idCaso"
					+ "</td>"
					+ "<td>"
					+ "Usuario"
					+ "</td>"
					+ "<td>"
					+ "Título"
					+ "</td>"
					+ "<td>"
					+ "Texto"
					+ "</td>"
					+ "<td>"
					+ "Imagem"
					+ "</td>"
					+ "<td>"
					+ "Excluir"
					+ "</td>"
					+ "</tr>");
			writer.print("<tr>"
					+ "<td>"
					+ caso.getIdCasosSucesso()
					+ "</td>"
					+ "<td>"
					+ UsuarioDAO.buscar(caso.getIdUsuario()).getNome().toString()
					+ "</td>"
					+ "<td>"
					+ caso.getTitulo()
					+ "</td>"
					+ "<td>"
					+ caso.getTexto()
					+ "</td>"
					+ "<td>"
					+ "<img src='"+caso.getCaminhoFoto()+"' class='imagem_tabela'>"
					+ "</td>"
					+ "<td>"
					+ "<p class='excluir_caso' data-idcaso='"+caso.getIdCasosSucesso()+"'>Excluir</p>"
					+ "</td>"
					+ "</tr>");
		}
		writer.println("<script>");
		writer.println("$(document).ready(function(){");
		writer.println("\r\n" + 
				"    	$('.excluir_caso').click(function(){\r\n" + 
				"    		$.ajax({\r\n" + 
				"                url:\"../../ExcluirCasoDeSucesso\",\r\n" + 
				"                type: \"POST\",\r\n" + 
				"                data: {id : $(this).data('idcaso')},\r\n" + 
				"                success: function(data) {\r\n" + 
				"                    alert('Caso de Sucesso excluído!');\r\n" + 
				"                }\r\n" + 
				"            });\r\n" + 
				"    	});");
		writer.println("});");
		writer.println("</script>");
	}

}
