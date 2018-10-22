<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
        $(document).ready(function(){
            $.ajax({
                url:"../../ListarIngredientes",
                type: "POST",
                success: function(data) {
                    $('#tabelaIngredientes').html(data);
                }
            });
            $(".editarIngrediente").click(function(){
            	$.ajax({
                    url:"view/edicaoIngrediente.jsp",
                    type: "POST",
                    data: {id : $(this).data('idIngrediente')},
                    success: function(retorno) {
                    	$('#conteudoCms').html(retorno);
                    }
                });
            });
        });
    </script>
<div class="coluna-12">
	<div class="linha" id="nome_pagina">Ingredientes Cadastrados</div>
	<div id="tabelaIngredientes"></div>
</div>