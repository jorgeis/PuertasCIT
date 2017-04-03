<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page session="true" %>


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
	        return confirm("Este cambiará su estado a Activo. ¿Continuar?");
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
<%-- 		<form method="post" id="valid2" action="${pageContext.request.contextPath}/admin/search" > --%>
<!-- 			<h1>Buscar administradores</h1> -->
<%-- 			<input type="hidden" id="path" value="${pageContext.request.contextPath}"/> --%>
<!-- 			<input type="text" name="busqueda" id="busqueda" class="validate[required]" /> -->
<!-- 			<button type="submit">Buscar</button> -->
<!-- 		</form>	 -->

		<div class = "table1">
			<table>
				<tr>
					<td>Nombre(s)</td>
					<td>Apellido Paterno</td>
					<td>Apellido Materno</td>
					<td>Correo electrónico</td>
					<td>CURP</td>
					<td>Área</td>
					<td>Cargo</td>
					<td>Teléfono</td>
					<td>Permisos</td>
				</tr>
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.persona.nombrePer}</td>
						<td>${admin.persona.apPatPer}</td>
						<td>${admin.persona.apMatPer}</td>
						<td>${admin.persona.emailPer}</td>
						<td>${admin.persona.curpPer}</td>
						<td>${admin.areaAd}</td>
						<td>${admin.cargoAd}</td>
						<td>${admin.telefonoAd}</td>
						<td>${admin.rolAd}</td>
						<td> 
							<a class="confirm" href="<c:url value='/admin/reactivate/${admin.idAd}' />">Activar</a><br/>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			
			<!-- 	Paginación por búsqueda de administradores(implementar para buscar solo admin borrados, oculta por el momento
					desde el controlador) -->
			<c:if test="${sessionScope.showPagesFromSearch == true}">
				<h1>Siiiiii</h1>
				<c:url var="firstUrl" value="/admin/search/1" />
				<c:url var="lastUrl" value="/admin/search/${totalPages}" />
				<c:url var="prevUrl" value="/admin/search/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/admin/search/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/admin/search/${i}" />
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
			
			<!-- Paginación estándar -->
			<c:if test="${showPages == true}">
				<c:url var="firstUrl" value="/admin/querydeleted/1" />
				<c:url var="lastUrl" value="/admin/querydeleted/${totalPages}" />
				<c:url var="prevUrl" value="/admin/querydeleted/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/admin/querydeleted/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/admin/querydeleted/${i}" />
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