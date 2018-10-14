<%@ page import="br.com.binarytech.dao.MedidaDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="br.com.binarytech.model.Medida"%>
<%@ page import="br.com.binarytech.model.Nutricional"%>
<%@ page import="br.com.binarytech.dao.NutricionalDAO"%>
<%
	NutricionalDAO nutricionalDao = new NutricionalDAO();
	ArrayList<Nutricional> listaNutricional = nutricionalDao.listar();
	
	MedidaDAO medidaDao = new MedidaDAO(); 
	ArrayList<Medida> listaMedidas = medidaDao.listar();
%>

<script>
    function updateCoords(im,obj){
        $('#x').val(obj.x1);
        $('#y').val(obj.y1);
        $('#w').val(obj.width);
        $('#h').val(obj.height);
    }

    //check coordinates
    function checkCoords(){
        if(parseInt($('#w').val())) return true;
        alert('Please select a crop region then press submit.');
        return false;
    }

    $(function(){
        //prepare instant image preview
        var p = $("#filePreview");
        $("#foto").change(function(){
            //fadeOut or hide preview
            p.fadeOut();

            //prepare HTML5 FileReader
            var oFReader = new FileReader();
            oFReader.readAsDataURL(document.getElementById("foto").files[0]);

            oFReader.onload = function (oFREvent) {
                p.attr('src', oFREvent.target.result).fadeIn();
            };
        });

        //implement imgAreaSelect plugin
        $('img#filePreview').imgAreaSelect({
            // set crop ratio (optional)
            //aspectRatio: '2:1',

            onSelectEnd: updateCoords
        });
        imagem = $('img#filePreview').imgAreaSelect({ fadeSpeed: 400, handles: true,
                                                     instance: true });
        $('img#filePreview').imgAreaSelect({ x1: 25, y1: 25, x2: 270, y2: 210 });

        $("#formCadastroIngrediente").submit(function(event){
            event.preventDefault();
            $.ajax({
                type: "POST",
                url: "../../GravarIngrediente",
                data: new FormData($('#formCadastroIngrediente')[0]),
                cache: false,
                contentType: false,
                processData: false,
                async:true,
                success: function(dados){
                    $("#retornoScript").html(dados);
                }
            });

        });
    });
</script>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="coluna-12">
	<div class="linha" id="nome_pagina">Novo Ingrediente</div>

	<form name='formCadastroIngrediente' method="post"
		enctype="multipart/form-data" class="formularios"
		id="formCadastroIngrediente">

		<div class="linha">
			<div class="coluna-12">
				<div class="linha">
					<label for="nomeIngrediente" class="label_formulario"> Nome
						do ingrediente: </label> <br> <input type="text"
						name="nomeIngrediente" required class="inputs_formulario"
						maxlength="30">
				</div>
				<div class="linha">
					<label for="informacoes" class="label_formulario">
						Informações: </label> <br>
					<table>
						<tr>
							<td>id</td>
							<td>Informação</td>
							<td>Valor</td>
							<td>Medida</td>
							<td>VD</td>
						</tr>

						<%for(Nutricional nutricional : listaNutricional){
                            	%>
						<tr>
							<td><%= nutricional.getIdNutricional() %></td>
							<td><%= nutricional.getInformacao() %></td>
							<td><input required type="text"
								name="<%= nutricional.getIdNutricional() %>"></td>
							<td><%= nutricional.getMedida() %></td>
							<td><input required type="text"
								name="vd<%= nutricional.getIdNutricional() %>"></td>
						</tr>
						<% }%>
					</table>
				</div>
				<div class="linha">
					<label for="medida" class="label_formulario"> Medida: </label> <br>
					<select name="medida">
						<%for(Medida medida : listaMedidas){
                                	%>
						<option value="<%= medida.getIdMedida() %>"><%= medida.getMedida() %></option>
						<%} %>

					</select>
				</div>
				<div class="linha">
					<label for="estoque" class="label_formulario"> Estoque: </label> <br>
					<input type="number" name="estoque" required
						class="inputs_formulario" maxlength="11">
				</div>
				<div class="linha">
					<label for="margemSeguranca" class="label_formulario">
						Margem de Segurança: </label> <br> <input type="number"
						name="margemSeguranca" required class="inputs_formulario"
						maxlength="11">
				</div>
				<div class="linha">
					<span class="label_formulario"> Imagem: </span>

					<div class="formularios_imagens" id="visualizarImg">
						<img
							src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs%3D"
							alt="Imagem" class="visualizarImg">
					</div>

					<div class="linha">
						<div id="labelFile">
							<img id="filePreview"
								src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs%3D"
								alt="Imagem" class="visualizarImg"> <label for="foto">
								Escolher Imagem </label> <input id='foto' type='file' value=''
								name="foto" required accept="image/*" /> <input type="hidden"
								id="x" name="x" /> <input type="hidden" id="y" name="y" /> <input
								type="hidden" id="w" name="w" /> <input type="hidden" id="h"
								name="h" />
						</div>
					</div>

				</div>
				<div class="linha">
					<span class="label_formulario"> Título: </span>
					<input type="text" name="tituloImagem" id="tituloImagem" class="inputs_formulario" maxlength="75">
				</div>
				<div class="linha">
					<span class="label_formulario"> Legenda: </span>
					<textarea name="legendaImagem" id="legendaImagem" class="inputs_formulario" maxlength="240"></textarea>
				</div>
			</div>
		</div>
		<div class="linha">
			<input type="submit" name="cadastrar" value="Salvar"
				class="salvarCadastro">
		</div>
	</form>

</div>