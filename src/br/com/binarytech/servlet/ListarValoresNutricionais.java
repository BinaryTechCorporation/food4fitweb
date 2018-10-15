package br.com.binarytech.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.NutricionalDAO;
import br.com.binarytech.model.Nutricional;

/**
 * Servlet implementation class ListarValoresNutricionais
 */
@WebServlet("/ListarValoresNutricionais")
public class ListarValoresNutricionais extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarValoresNutricionais() {
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

		ArrayList<Nutricional> listaNutricional = new ArrayList<>();
		listaNutricional = NutricionalDAO.listar();

		PrintWriter writer = response.getWriter();

		writer.println("<table>");
		for (Nutricional nutriente : listaNutricional) {
			writer.println("<tr>");
			writer.println("<td>" + nutriente.getIdNutricional() + "</td>");
			writer.println("<td>" + nutriente.getInformacao() + "</td>");
			writer.println("<td>" + "<input required type='text' name='" + nutriente.getIdNutricional() + "'></td>");
			writer.println("<td>" + nutriente.getMedida() + "</td>");
			writer.println("<td>" + "<input required type='text' name='vd" + nutriente.getIdNutricional() + "'></td>");
			writer.println("</tr>");

		}
		writer.println("</table>");
		
		/*
		 * for(Nutricional nutricional : listaNutricional){
		 * 
		 * writer.println("<tr>"); writer.println("<td>" +
		 * nutricional.getIdNutricional() + "</td>"); writer.println("<td>" +
		 * nutricional.getInformacao() + "</td>");
		 * writer.println("<td><input required type="text"name="<%=
		 * nutricional.getIdNutricional() %>"></td>\");
		 * writer.println("<td><%= nutricional.getMedida() %></td>\");
		 * writer.println("<td><input required type="text" name="vd<%=
		 * nutricional.getIdNutricional() %>"></td>\"); writer.println("</tr>\"); }
		 */
	}

}
