package br.com.binarytech.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.IngredienteDAO;
import br.com.binarytech.model.Ingrediente;

/**
 * Servlet implementation class ListarIngredientes
 */
@WebServlet("/ListarIngredientes")
public class ListarIngredientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarIngredientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Pegando os ingredientes cadastrados */
		ArrayList<Ingrediente> listaIngredientes = new ArrayList<>();
		listaIngredientes = IngredienteDAO.listar();

		// Pegando o writer
		PrintWriter writer = response.getWriter();

		// Iniciando a tabela
		writer.println("<table>");
		writer.println("<tr>");
		writer.println("<td>");
		writer.println("id");
		writer.println("</td>");
		writer.println("<td>");
		writer.println("Nome");
		writer.println("</td>");
		writer.println("<td>");
		writer.println("Estoque");
		writer.println("</td>");
		writer.println("<td>");
		writer.println("Margem de Segurança");
		writer.println("</td>");
		writer.println("<td>");
		writer.println("Editar");
		writer.println("</td>");
		writer.println("<td>");
		writer.println("Excluir");
		writer.println("</td>");
		writer.println("</tr>");

		// Jogando os ingredientes na tabela
		for (Ingrediente ingrediente : listaIngredientes) {
			writer.println("<tr>");
			writer.println("<td>");
			writer.println(ingrediente.getIdIngrediente());
			writer.println("</td>");
			writer.println("<td>");
			writer.println(ingrediente.getIngrediente());
			writer.println("</td>");
			writer.println("<td>");
			writer.println(ingrediente.getEstoque());
			writer.println("</td>");
			writer.println("<td>");
			writer.println(ingrediente.getMargemSeguranca());
			writer.println("</td>");
			writer.println(
					"<td class='editarIngrediente' data-idingrediente='" + ingrediente.getIdIngrediente() + "'>");
			writer.println("Editar");
			writer.println("</td>");
			writer.println(
					"<td class='excluirIngrediente' data-idingrediente='" + ingrediente.getIdIngrediente() + "'>");
			writer.println("Excluir");
			writer.println("</td>");
			writer.println("</tr>");
		}

		// Fechando a tabela
		writer.println("</table>");

		// Função em JavaScript para excluir
		writer.println("<script>");
		writer.println("$('.excluirIngrediente').click(function(){");
		writer.println("$.ajax({");
		writer.println("url:'../../ExcluirIngrediente',");
		writer.println("type: 'POST',");
		writer.println("data: {id : $(this).data('idingrediente')},");
		writer.println("success: function(data) {");
		
		writer.println("$.ajax({");
		writer.println("url:'view/todosIngredientes.jsp',");
		writer.println("success: function(data) {");
		writer.println("$('#conteudoCms').html(data);");
		writer.println("}");
		writer.println("});");
		
		writer.println("alert('Ingrediente excluído com sucesso!');");
		writer.println("}");
		writer.println("});");
		writer.println(" });");
		writer.println("</script>");

		// Função que devolve a tela atualizada
		
	}

}
