package br.com.binarytech.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.binarytech.dao.IngredienteDAO;
import br.com.binarytech.model.Ingrediente;

/**
 * Servlet implementation class EditarIngrediente
 */
@WebServlet("/EditarIngrediente")
public class EditarIngrediente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarIngrediente() {
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
		int idIngrediente = Integer.parseInt(request.getParameter("id"));
		Ingrediente ingrediente = IngredienteDAO.buscar(idIngrediente);
		PrintWriter writer = response.getWriter();
		writer.println("<%@ page import='br.com.binarytech.dao.MedidaDAO'%>\r\n" + 
				"<%@ page import='java.util.ArrayList'%>\r\n" + 
				"<%@ page import='br.com.binarytech.model.Medida'%>\r\n" + 
				"<%@ page import='br.com.binarytech.model.Nutricional'%>\r\n" + 
				"<%@ page import='br.com.binarytech.dao.NutricionalDAO'%>\r\n" + 
				"<%	\r\n" + 
				"	ArrayList<Medida> listaMedidas = MedidaDAO.listar();\r\n" + 
				"%>\r\n" + 
				"\r\n" + 
				"<script>\r\n" + 
				"	$(document).ready(function(){\r\n" + 
				"		$.ajax({\r\n" + 
				"            url:'../../ListarValoresNutricionais',\r\n" + 
				"            type: 'POST',\r\n" + 
				"            success: function(data) {\r\n" + 
				"                $('#tabelaNutrientes').html(data);\r\n" + 
				"            }\r\n" + 
				"        });\r\n" + 
				"		$.ajax({\r\n" + 
				"            url:'../../ListarMedidas',\r\n" + 
				"            type: 'POST',\r\n" + 
				"            success: function(data) {\r\n" + 
				"                $('#medida').html(data);\r\n" + 
				"            }\r\n" + 
				"        });\r\n" + 
				"	});\r\n" + 
				"\r\n" + 
				"    function updateCoords(im,obj){\r\n" + 
				"        $('#x').val(obj.x1);\r\n" + 
				"        $('#y').val(obj.y1);\r\n" + 
				"        $('#w').val(obj.width);\r\n" + 
				"        $('#h').val(obj.height);\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    //check coordinates\r\n" + 
				"    function checkCoords(){\r\n" + 
				"        if(parseInt($('#w').val())) return true;\r\n" + 
				"        alert('Please select a crop region then press submit.');\r\n" + 
				"        return false;\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"    $(function(){\r\n" + 
				"        //prepare instant image preview\r\n" + 
				"        var p = $('#filePreview');\r\n" + 
				"        $('#foto').change(function(){\r\n" + 
				"            //fadeOut or hide preview\r\n" + 
				"            p.fadeOut();\r\n" + 
				"\r\n" + 
				"            //prepare HTML5 FileReader\r\n" + 
				"            var oFReader = new FileReader();\r\n" + 
				"            oFReader.readAsDataURL(document.getElementById('foto').files[0]);\r\n" + 
				"\r\n" + 
				"            oFReader.onload = function (oFREvent) {\r\n" + 
				"                p.attr('src', oFREvent.target.result).fadeIn();\r\n" + 
				"            };\r\n" + 
				"        });\r\n" + 
				"\r\n" + 
				"        //implement imgAreaSelect plugin\r\n" + 
				"        $('img#filePreview').imgAreaSelect({\r\n" + 
				"            // set crop ratio (optional)\r\n" + 
				"            //aspectRatio: '2:1',\r\n" + 
				"\r\n" + 
				"            onSelectEnd: updateCoords\r\n" + 
				"        });\r\n" + 
				"        imagem = $('img#filePreview').imgAreaSelect({ fadeSpeed: 400, handles: true,\r\n" + 
				"                                                     instance: true });\r\n" + 
				"        $('img#filePreview').imgAreaSelect({ x1: 25, y1: 25, x2: 270, y2: 210 });\r\n" + 
				"\r\n" + 
				"        $('#formCadastroIngrediente').submit(function(event){\r\n" + 
				"            event.preventDefault();\r\n" + 
				"            $.ajax({\r\n" + 
				"                type: 'POST',\r\n" + 
				"                url: '../../GravarIngrediente',\r\n" + 
				"                data: new FormData($('#formCadastroIngrediente')[0]),\r\n" + 
				"                cache: false,\r\n" + 
				"                contentType: false,\r\n" + 
				"                processData: false,\r\n" + 
				"                async:true,\r\n" + 
				"                success: function(dados){\r\n" + 
				"                    $('#retornoScript').html(dados);\r\n" + 
				"                    alert('Ingrediente cadastrado com sucesso!');\r\n" + 
				"                }\r\n" + 
				"            });\r\n" + 
				"\r\n" + 
				"        });\r\n" + 
				"    });\r\n" + 
				"</script>\r\n" + 
				"<%@ page language='java' contentType='text/html; charset=utf-8'\r\n" + 
				"	pageEncoding='utf-8'%>\r\n" + 
				"<div class='coluna-12'>\r\n" + 
				"	<div class='linha' id='nome_pagina'>Editar Ingrediente</div>\r\n" + 
				"\r\n" + 
				"	<form name='formCadastroIngrediente' method='post'\r\n" + 
				"		enctype='multipart/form-data' class='formularios'\r\n" + 
				"		id='formCadastroIngrediente'>\r\n" + 
				"\r\n" + 
				"		<div class='linha'>\r\n" + 
				"			<div class='coluna-12'>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<label for='nomeIngrediente' class='label_formulario'> Nome\r\n" + 
				"						do ingrediente: </label> <br> <input type='text'\r\n" + 
				"						name='nomeIngrediente' required class='inputs_formulario'\r\n" + 
				"						maxlength='30' value='" + ingrediente.getIngrediente().toString() + "'>\r\n" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<label for='informacoes' class='label_formulario'>\r\n" + 
				"						Informações: </label> <br>\r\n" + 
				"					<div id='tabelaNutrientes'></div>\r\n" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<label for='medida' class='label_formulario'> Medida: </label> <br>\r\n" + 
				"					<select name='medida' id='medida'>\r\n" + 
				"					</select>\r\n" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<label for='estoque' class='label_formulario'> Estoque: </label> <br>\r\n" + 
				"					<input type='number' name='estoque' required\r\n" + 
				"						class='inputs_formulario' maxlength='11' value='" + ingrediente.getEstoque() + "'>" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<label for='margemSeguranca' class='label_formulario'>\r\n" + 
				"						Margem de Segurança: </label> <br> <input type='number'\r\n" + 
				"						name='margemSeguranca' required class='inputs_formulario'\r\n" + 
				"						maxlength='11' value='"+ingrediente.getMargemSeguranca()+"'>\r\n" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<span class='label_formulario'> Imagem: </span>\r\n" + 
				"\r\n" + 
				"					<div class='formularios_imagens' id='visualizarImg'>\r\n" + 
				"						<img\r\n" + 
				"							src='data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs%3D'\r\n" + 
				"							alt='Imagem' class='visualizarImg'>\r\n" + 
				"					</div>\r\n" + 
				"\r\n" + 
				"					<div class='linha'>\r\n" + 
				"						<div id='labelFile'>\r\n" + 
				"							<img id='filePreview'\r\n" + 
				"								src='data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs%3D'\r\n" + 
				"								alt='Imagem' class='visualizarImg'> <label for='foto'>\r\n" + 
				"								Escolher Imagem </label> <input id='foto' type='file' value=''\r\n" + 
				"								name='foto' required accept='image/*' /> <input type='hidden'\r\n" + 
				"								id='x' name='x' /> <input type='hidden' id='y' name='y' /> <input\r\n" + 
				"								type='hidden' id='w' name='w' /> <input type='hidden' id='h'\r\n" + 
				"								name='h' />\r\n" + 
				"						</div>\r\n" + 
				"					</div>\r\n" + 
				"\r\n" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<span class='label_formulario'> Título da foto: </span>\r\n" + 
				"					<input type='text' name='tituloImagem' id='tituloImagem' class='inputs_formulario' maxlength='75' value='\"+ingrediente.getTitulo()+\"'>\r\n" + 
				"				</div>\r\n" + 
				"				<div class='linha'>\r\n" + 
				"					<span class='label_formulario'> Legenda da foto: </span>\r\n" + 
				"					<textarea name='legendaImagem' id='legendaImagem' class='inputs_formulario' maxlength='240'>\"+ingrediente.getLegenda()+\"</textarea>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"		<div class='linha'>\r\n" + 
				"			<input type='submit' name='cadastrar' value='Salvar'\r\n" + 
				"				class='salvarCadastro'>\r\n" + 
				"		</div>\r\n" + 
				"	</form>\r\n" + 
				"\r\n" + 
				"</div>");
	}

}
