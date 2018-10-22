<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
    $(document).ready(function(){
    	$.ajax({
            url:"../../ListarCasosSucesso",
            type: "POST",
            success: function(data) {
                $('#tabelaCasosSucesso').html(data);
            }
        });
    	$('.botao_adicionar').click(function(){
    		$.ajax({
                url:"view/cadastroCasosSucesso.jsp",
                type: "POST",
                success: function(data) {
                    $('#conteudoCms').html(data);
                }
            });
    	});
    });
    </script>
<div class="coluna-12">
	<div class="linha" id="nome_pagina">
		Casos de Sucesso <img src="../media/img/cms/adicionar.png"
			alt="Adicionar" class="botao_adicionar">
	</div>

	<table id="tabelaCasosSucesso"></table>
</div>