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
<link rel="stylesheet" href='<c:url value = "/res/css/own.css" />' />
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
		<form method="post" id="valid2" action="${pageContext.request.contextPath}/admin/notificacion/search" >
			<h1>Buscar Notificaciones</h1>
			<h2>${result}</h2>
	
			<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
			
			<label >Fecha</label>
			<input type="text" id="datepickerFrom" name="dateFrom" placeholder="Desde"/>
			<input type="text" id="datepickerTo" name="dateTo" placeholder="Hasta"/>
			
			<label >Visibilidad</label>
			<input type="radio" name="visibilidad" checked="checked" value="Admin"> Administradores
			<input type="radio" name="visibilidad" value="Cliente"> Clientes
			
			<button type="submit">Buscar</button>
		</form>	
		
		
		
		<div class = "table1">
			<table>
				<tr>
					<td>Título</td>
					<td>Contenido</td>
					<td>Visibilidad</td>
					<td>Fecha de Creación</td>
					<td>Fecha de Publicación</td>
					<td>Estado</td>
				</tr>
				<c:forEach var="notif" items="${notificacionList}">
					<tr>
						<td>${notif.tituloNot}</td>
						<td>${notif.contenidoNot}</td>
						<td>${notif.visibilidad}</td>
						<td>${notif.fhCrea}</td>
						<td>${notif.fhPubNot}</td>
						<td>${notif.status}</td>
					</tr>
				</c:forEach>
			</table>
			
			
			<c:if test="${showPages == true}">
				<c:url var="firstUrl" value="/admin/notificacion/queryall/1" />
				<c:url var="lastUrl" value="/admin/notificacion/queryall/${totalPages}" />
				<c:url var="prevUrl" value="/admin/notificacion/queryall/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/admin/notificacion/queryall/${currentIndex + 1}" />
				<c:choose>
					<%-- Si la página actual es 1 deshabilitar botones << y < --%>
					<c:when test="${currentIndex == 1}">
						<a href="#">&lt;&lt;</a>
						<a href="#">&lt;</a>
					</c:when>
					<%-- Si no entonces colocarlos con sus urls correspondientes --%>
					<c:otherwise>
						<a href="${firstUrl}">&lt;&lt;</a>
						<a href="${prevUrl}">&lt;</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					<%-- Construir links a partir de los índices --%>
					<c:url var="pageUrl" value="/admin/notificacion/queryall/${i}" />
					<c:choose>
						<%-- Remarcar el índice actual --%>
						<c:when test="${i == currentIndex}">
							<span style="font-weight: bold;"><a href="${pageUrl}">${i}</a></span>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<%-- Si el índide actual es igual al total de páginas --%>
					<c:when test="${currentIndex == totalPages}">
						<a href="#">&gt;</a>
						<a href="#">&gt;&gt;</a>
					</c:when>
					<c:otherwise>
						<a href="${nextUrl}">&gt;</a>
						<a href="${lastUrl}">&gt;&gt;</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			
			
		
		</div>
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />