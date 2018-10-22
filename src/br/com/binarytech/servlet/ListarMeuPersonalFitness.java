package br.com.binarytech.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.PublicacaoDAO;
import br.com.binarytech.dao.UsuarioCmsDAO;
import br.com.binarytech.model.Publicacao;

/**
 * Servlet implementation class ListarMeuPersonalFitness
 */
@WebServlet("/ListarMeuPersonalFitness")
public class ListarMeuPersonalFitness extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarMeuPersonalFitness() {
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
		ArrayList<Publicacao> listaPublicacao = PublicacaoDAO.listar(9);
		
		
		for(Publicacao publicacao : listaPublicacao) {
			writer.println("<tr class='header_tabela'>"
					+ "<td>idPublicacao</td>"
					+ "<td>Autor</td>"
					+ "<td>Titulo</td>"
					+ "<td>Texto</td>"
					+ "<td>Data</td>"
					+ "<td>Curtidas</td>"
					+ "<td>Visualizações</td>"
					+ "<td>Compartilhamentos</td>"
					+ "<td>Excluir</td>"
					+ "</tr>");
			writer.println("<tr>"
					+ "<td>"+publicacao.getIdPublicacao()+"</td>"
					+ "<td>"+UsuarioCmsDAO.buscar(publicacao.getIdExterno()).getNome()+"</td>"
					+ "<td>"+publicacao.getTitulo()+"</td>"
					+ "<td>"+publicacao.getTexto()+"</td>"
					+ "<td>"+publicacao.getData()+"</td>"
					+ "<td>"+publicacao.getCurtida()+"</td>"
					+ "<td>"+publicacao.getVisualizacao()+"</td>"
					+ "<td>"+publicacao.getCompartilhamento()+"</td>"
					+ "<td><p class='excluirPersonalFitness' data-idpersonal='"+publicacao.getIdPublicacao()+"'>Excluir</p></td>"
					+ "</tr>");
		}
		writer.println("<script>");
		writer.println("$(document).ready(function() {");
		writer.println("$('.excluirPersonalFitness').click(function(){\r\n" + 
				"			$.ajax({\r\n" + 
				"				url : '../../ExcluirPersonalFitness',\r\n" + 
				"				type: 'POST',\r\n" + 
				"				data : {id : $(this).data('idpersonal')},\r\n" + 
				"				success : function(data) {\r\n" + 
				"					alert('Meu Personal Fitness excluído!');\r\n" + 
				"				}\r\n" + 
				"			});\r\n" + 
				"		});");
		writer.println("});");
		writer.println("</script>");
	}

}
