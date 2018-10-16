<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<script>
	$(document).ready(function() {
		$.ajax({
			url : "../../ListarUsuarios",
			type : "POST",
			success : function(data) {
				$('#usuarioCaso').html(data);
			}
		});
		$('.selectUsuarios').select2();
		$("#formCasosSucesso").submit(function(event) {
			event.preventDefault();
			$.ajax({
				type : "POST",
				url : "../../GravarCasosSucesso",
				data : new FormData($('#formCasosSucesso')[0]),
				cache : false,
				contentType : false,
				processData : false,
				async : true,
				success : function(dados) {
					$("#retornoScript").html(dados);
				}
			});

		});
	});
	
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

    });
</script>
<div class="coluna-12">
	<div class="linha" id="nome_pagina">Casos de Sucesso</div>
	<form method="post" id="formCasosSucesso" name="formCasosSucesso"
		enctype="multipart/form-data">
		<div class="linha">
			<label for="tituloCaso" class="label_formulario"> Título: </label> <br>
			<input type="text" name="tituloCaso" required
				class="inputs_formulario" maxlength="75">
		</div>
		<div class="linha">
			<label for="textoCaso" class="label_formulario"> Texto: </label> <br>
			<textarea id="textoCaso" name="textoCaso" class="jqte-test" required
				maxlength="65535"></textarea>
			<script>
				/** sobre_titulo **/
				$('.jqte-test').jqte();

				// settings of status
				var jqteStatus = true;
				$(".status").click(function() {
					jqteStatus = jqteStatus ? false : true;
					$('.jqte-test').jqte({
						"status" : jqteStatus
					})
				});
			</script>
		</div>
		<div class="linha">
			<label for="usuarioCaso" class="label_formulario"> Usuário: </label>
			<br> <select name="usuarioCaso" id="usuarioCaso"
				class="selectUsuarios" style="width: 200px">
			</select>
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
						Escolher Imagem </label> <input id='foto' type='file' value='' name="foto"
						required accept="image/*" /> <input type="hidden" id="x" name="x" />
					<input type="hidden" id="y" name="y" /> <input type="hidden"
						id="w" name="w" /> <input type="hidden" id="h" name="h" />
				</div>
			</div>

		</div>
		<div class="linha">
			<span class="label_formulario"> Título da foto: </span> <input
				type="text" name="tituloImagem" id="tituloImagem"
				class="inputs_formulario" maxlength="75">
		</div>
		<div class="linha">
			<span class="label_formulario"> Legenda da foto: </span>
			<textarea name="legendaImagem" id="legendaImagem"
				class="inputs_formulario" maxlength="240"></textarea>
		</div>
		<div class="linha">
			<input type="submit" name="cadastrar" value="Salvar"
				class="salvarCadastro">
		</div>
	</form>
</div>