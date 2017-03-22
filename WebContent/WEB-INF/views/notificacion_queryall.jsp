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
	});
</script>

	<!-- Article - Formulario -->
	<article class="featured">
	
		<sf:form method="post" id="valid"
			action="${pageContext.request.contextPath}/admin/notificacion/save" 
	 		commandName="notificacion"> 
			
		<h1>Crear Notificación</h1>
		<h2>${result}</h2>
			<fieldset>
			<sf:input type="hidden" path="idNot" />
			
			<label >Título</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="tituloNot" />
			
			<label >Contenido</label>
			<sf:textarea type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="contenidoNot" ></sf:textarea>
			
			<label >Visibilidad</label>
			<sf:select path="visibilidadNot" class="validate[required]">
				<sf:option value="">Elige una opción</sf:option>
				<sf:option value="ROLE_ROOT">Super Administrador</sf:option>
				<sf:option value="ROLE_ADMIN">Administrador</sf:option>
			</sf:select>
			</fieldset>
			
			<button type="submit">Guardar</button>
		</sf:form>
	</article>
	
	<article class="featured"> 
		<form method="post" id="valid2" action="${pageContext.request.contextPath}/admin/search" >
			<h1>Buscar Notificaciones</h1>
			<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
			<input type="text" name="busqueda" id="busqueda" class="validate[required]" />
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
						<td>${notif.visibilidadNot}</td>
						<td>${notif.fhCreaNot}</td>
						<td>${notif.fhPubNot}</td>
						<td>${notif.status}</td>
						<td>
							<a href="<c:url value='/admin/update/${admin.idAd}' />">Modificar</a> &nbsp;  
							<a class="confirm" href="<c:url value='/admin/delete/${admin.idAd}' />">Eliminar</a><br/>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			
			<c:if test="${showPages == true}">
				<c:url var="firstUrl" value="/notificacion/queryall/1" />
				<c:url var="lastUrl" value="/notificacion/queryall/${totalPages}" />
				<c:url var="prevUrl" value="/notificacion/queryall/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/notificacion/queryall/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/notificacion/queryall/${i}" />
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