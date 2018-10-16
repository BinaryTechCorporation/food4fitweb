package br.com.binarytech.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.IngredienteDAO;
import br.com.binarytech.dao.NutricionalDAO;

/**
 * Servlet implementation class ExcluirIngrediente
 */
@WebServlet("/ExcluirIngrediente")
public class ExcluirIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExcluirIngrediente() {
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
		// Pega o ID do ingrediente
		int idIngrediente = Integer.parseInt(request.getParameter("id"));
		// Deleta os valores nutricionais ligados �quele ingrediente
		NutricionalDAO.deletarPorIngrediente(idIngrediente);
		// Deleta o ingrediente
		IngredienteDAO.deletar(idIngrediente);
	}

}
