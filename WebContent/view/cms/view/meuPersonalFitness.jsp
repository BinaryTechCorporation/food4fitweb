<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<script>
	$(document).ready(function() {
		$.ajax({
			url : "../../ListarMeuPersonalFitness",
			type: "POST",
			success : function(data) {
				$('#tabelaPersonalFitness').html(data);
			}
		});
		
	});
</script>
<div class="coluna-12">
	<table id="tabelaPersonalFitness"></table>
</div>