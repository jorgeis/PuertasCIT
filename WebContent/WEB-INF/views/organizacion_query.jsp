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
<link rel="stylesheet" href='<c:url value = "/res/css/own.css" />' />
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
		<form>
			<h1>${pageTitle}</h1>
			<h2>${result}</h2>
		</form>
		
<%-- 		<form method="post" id="valid2" action="${pageContext.request.contextPath}/admin/search" > --%>
<!-- 			<h1>Buscar administradores</h1> -->
<%-- 			<input type="hidden" id="path" value="${pageContext.request.contextPath}"/> --%>
<!-- 			<input type="text" name="busqueda" id="busqueda" class="validate[required]" /> -->
<!-- 			<button type="submit">Buscar</button> -->
<!-- 		</form>	 -->

		

		<div class = "table1">
			<table>
				<tr>
					<td>Nombre</td>
					<td>Siglas</td>
					<td>RFC</td>
					<td colspan="2">Acción</td>
				</tr>
				<c:forEach var="org" items="${orgList}">
					<tr>
						<td>${org.nombreOrg}</td>
						<td>${org.siglasOrg}</td>
						<td>${org.rfcOrg}</td>
						<td>
							<form method="post" action="${pageContext.request.contextPath}/org/update" >
							<input type="hidden" name="idOrgParam" value="${org.idOrg}"/>
							<button type="submit">Modificar</button>
						</form>	
						</td>
						<td>
							<form method="post" action="${pageContext.request.contextPath}/org/querymembers" >
							<input type="hidden" name="param1" value="${org.idOrg}"/>
							<button type="submit">Miembros</button>
						</form>	
						</td>
					</tr>
				</c:forEach>
			</table>
			
			
			<!-- Paginación estándar -->
			<c:if test="${showPages == true}">
				<c:url var="firstUrl" value="/org/query${searchParam}/1" />
				<c:url var="lastUrl" value="/org/query${searchParam}/${totalPages}" />
				<c:url var="prevUrl" value="/org/query${searchParam}/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/org/query${searchParam}/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/org/query${searchParam}/${i}" />
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