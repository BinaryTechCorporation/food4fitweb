package br.com.binarytech.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.MedidaDAO;
import br.com.binarytech.model.Medida;

/**
 * Servlet implementation class ListarMedidas
 */
@WebServlet("/ListarMedidas")
public class ListarMedidas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarMedidas() {
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
		//Pegando o Writer
		PrintWriter writer = response.getWriter();
		//Pegando a lista de Medidas
		ArrayList<Medida> listaMedidas = MedidaDAO.listar();
		for(Medida medida : listaMedidas) {
			writer.println("<option value='"+medida.getIdMedida()+"'>"+medida.getMedida()+"</option>");
		}
	}

}
