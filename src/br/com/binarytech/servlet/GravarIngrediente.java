package br.com.binarytech.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import br.com.binarytech.dao.IngredienteDAO;
import br.com.binarytech.dao.MedidaDAO;
import br.com.binarytech.dao.MidiaDAO;
import br.com.binarytech.dao.NutricionalDAO;
import br.com.binarytech.model.InformacaoSite;
import br.com.binarytech.model.Ingrediente;
import br.com.binarytech.model.Midia;
import br.com.binarytech.model.Nutricional;
import br.com.binarytech.util.Foto;

/**
 * Servlet implementation class GravarIngrediente
 */
@WebServlet("/GravarIngrediente")
public class GravarIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:\\Users\\hugoh\\Desktop\\Food4Fit\\WebContent\\view\\media\\img\\ingredientes";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GravarIngrediente() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printer = response.getWriter();
		Ingrediente ingrediente = new Ingrediente();
		/* Se o local de salvar não existir, crie */
		if (!new File(UPLOAD_DIRECTORY).exists()) {
			new File(UPLOAD_DIRECTORY).mkdir();
		}
		try {
			/* Resgata o formulário */
			MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_DIRECTORY,
					/* 15MB */ 15360 * 15360, new DefaultFileRenamePolicy());
			/* Salva a foto já cortada na pasta desejada e retorna o caminho dela */
			String foto = Foto.salvarFoto(UPLOAD_DIRECTORY, multipartRequest);
			/* Resgata os dados e monta o objeto ingrediente a ser gravado */
			ingrediente.setEstoque(Integer.parseInt(multipartRequest.getParameter("estoque")));
			ingrediente.setIdMedida(Integer.parseInt(multipartRequest.getParameter("medida")));
			ingrediente.setIngrediente(multipartRequest.getParameter("nomeIngrediente"));
			ingrediente.setMargemSeguranca(Integer.parseInt(multipartRequest.getParameter("margemSeguranca")));
			ingrediente
					.setMedida(MedidaDAO.buscar(Integer.parseInt(multipartRequest.getParameter("medida"))).getMedida());
			// ingrediente.setNutrientes(nutrientes);
			ArrayList<Nutricional> listaNutrientesCadastrados = NutricionalDAO.listar();
			/* Coloca a informação nutricional e o VD em cada objeto nutriente */
			for (Nutricional nutriente : listaNutrientesCadastrados) {
				nutriente.setInformacao(multipartRequest.getParameter(String.valueOf(nutriente.getIdNutricional())));
				nutriente.setVd(Float.parseFloat(multipartRequest.getParameter("vd"+nutriente.getIdNutricional())));
			}

			ingrediente.setNutrientes(listaNutrientesCadastrados);
			ingrediente
					.setSigla(MedidaDAO.buscar(Integer.parseInt(multipartRequest.getParameter("medida"))).getSigla());
			
			//Grava o objeto
			int gravacao = IngredienteDAO.gravar(ingrediente);
			//Pega o objeto que acaba de ser gravado
			int ultimoIdIngrediente = IngredienteDAO.buscarUltimoId();
			//Grava as informações nutricionais para o ingrediente que acaba de ser gravado
			int gravacaonutri = IngredienteDAO.gravarNutrientes(ultimoIdIngrediente, listaNutrientesCadastrados);
			
			/*Gravando a imagem*/
			Midia midia = new Midia();
			midia.setCaminho(foto);
			midia.setIdExterno(ultimoIdIngrediente);
			midia.setIdTabela(6);
			midia.setLegenda(multipartRequest.getParameter("legendaImagem"));
			midia.setTitulo(multipartRequest.getParameter("tituloImagem"));
			
			MidiaDAO.gravar(midia);

			/*Map<String, String> mapNutrientes = new HashMap<>();

			for (Nutricional n : listaNutrientesCadastrados) {
				mapNutrientes.put(String.valueOf(n.getIdNutricional()),
						multipartRequest.getParameter(String.valueOf(n.getIdNutricional())));
			}

			Map<String, String> mapVd = new HashMap<>();

			for (Nutricional n : listaNutrientesCadastrados) {
				mapNutrientes.put(String.valueOf(n.getIdNutricional()),
						multipartRequest.getParameter(String.valueOf(n.getIdNutricional())));
			}*/

		} catch (Exception erro) {
			printer.println("Upload da foto falhou devido a " + erro);
		}
	}

}
