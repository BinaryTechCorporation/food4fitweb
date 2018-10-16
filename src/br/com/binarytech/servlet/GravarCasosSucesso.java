package br.com.binarytech.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import br.com.binarytech.dao.CasosSucessoDAO;
import br.com.binarytech.dao.MidiaDAO;
import br.com.binarytech.model.CasosSucesso;
import br.com.binarytech.model.InformacaoSite;
import br.com.binarytech.model.Midia;
import br.com.binarytech.util.Foto;

/**
 * Servlet implementation class GravarCasosSucesso
 */
@WebServlet("/GravarCasosSucesso")
public class GravarCasosSucesso extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:\\Users\\hugoh\\Desktop\\Food4Fit\\WebContent\\view\\media\\img\\casosSucesso";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GravarCasosSucesso() {
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
		PrintWriter printer = response.getWriter();
		CasosSucesso casoSucesso = new CasosSucesso();
		/* Se o local de salvar não existir, crie */
		if(!new File(UPLOAD_DIRECTORY).exists()){
			new File(UPLOAD_DIRECTORY).mkdir();
		}
		try {
		/* Resgata o formulário */
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_DIRECTORY, /* 15MB */ 15360 * 15360, new DefaultFileRenamePolicy());
		/* Salva a foto já cortada na pasta desejada e retorna o caminho dela */
		String foto = Foto.salvarFoto(UPLOAD_DIRECTORY, multipartRequest);
		//Grava o caso de sucesso
		casoSucesso.setIdUsuario(Integer.parseInt(multipartRequest.getParameter("usuarioCaso")));
		casoSucesso.setTexto(multipartRequest.getParameter("textoCaso"));
		casoSucesso.setTitulo(multipartRequest.getParameter("tituloCaso"));
		CasosSucessoDAO.gravar(casoSucesso);
		
		//Grava a foto selecionada
		Midia midia = new Midia();
		midia.setCaminho(foto);
		midia.setIdExterno(CasosSucessoDAO.buscarUltimoId());
		midia.setIdTabela(8);
		midia.setLegenda(multipartRequest.getParameter("legendaImagem"));
		midia.setTitulo(multipartRequest.getParameter("tituloImagem"));
		MidiaDAO.gravar(midia);
		} catch (Exception erro) {
			printer.println("Upload da foto falhou devido a " + erro);
		}
	}

}
