<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>


<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />

<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.loading.block.js" />'></script>
<script>
	jQuery(document).ready(function(){
		jQuery(".confirm").on("click", function() {
	        return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
	    });
		var path = $("#path").val();
		jQuery("#busqueda").autocomplete({
			source: path + "/json/search/admin",
			minLength: 2
		});
		jQuery("#valid").validationEngine();
		jQuery("#valid2").validationEngine();
		
		// Prevenir form para ser enviado
	    jQuery("#valid").submit(function(e){
	        e.preventDefault();
	    });
		
	    $("#datepickerFrom").datepicker({
	    	dateFormat: "yy-mm-dd",
	        changeYear: true
	    });
	    
	    $("#datepickerTo").datepicker({
	    	dateFormat: "yy-mm-dd",
	        changeYear: true
	    });
	    
	    jQuery('#ajaxTask').click(function(event) {
	           var url = path + "/admin/notificacion/send";
	           $.ajax({ 
	        	    beforeSend: function() {
	        	    	$.loadingBlockShow({
	        	    		imgPath: path +'/res/images/default.svg',
       	    			 	text: 'Enviando notificaciones, espera...'
       	    			});
	        		},
	                url: url, 
	                data: { 
	                	tituloNot: $("#tituloNot").val(),
	                	contenidoNot: $("#contenidoNot").val(),
	                	visibilidadNot: $("#visibilidadNot").val()
	                }, 
	                complete: function(){
	                	$.loadingBlockHide();
	                },
	                success: function (data) { 
	                	$.loadingBlockHide();
	                	$('#ajaxResult').empty();
	                    $.each(data, function(index, element) {
	                    	$('#ajaxResult').append(': <h2>' + element + '</h2><br/>');
	                    });
	                }
	            });
	        });
	});
</script>

	<!-- Article - Formulario -->
	<article class="featured">
	
		<form id="valid"> 
			
		<h1>Crear Notificación</h1>
		<h2>${result}</h2>
			<fieldset>
			
			<label >Título</label>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" id="tituloNot" />
			
			<label >Contenido</label>
			<textarea class="validate[required]" data-prompt-position="bottomLeft:20,5" id="contenidoNot" ></textarea>
			
			<label >Visibilidad</label>
			<select id="visibilidadNot" class="validate[required]">
				<option value="">Elige una opción</option>
				<option value="Admin">Administradores</option>
				<option value="Cliente">Clientes</option>
			</select>
			</fieldset>
			
			<button type="submit" id="ajaxTask">Enviar</button>
		</form>
		<div id="ajaxResult"> </div>
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />