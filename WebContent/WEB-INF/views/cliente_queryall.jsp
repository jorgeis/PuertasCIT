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
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />

<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />

<script>
	jQuery(document).ready(function(){
		jQuery(".confirmDelete").on("click", function() {
	        return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
	    });
		var path = $("#path").val();
		jQuery("#busqueda").autocomplete({
			source: path + "/json/search/cliente",
		});
	});
</script>


<script>
	jQuery(document).ready(function(){
		var path = $("#path").val();
		jQuery("#busqueda").autocomplete({
			source: path + "/json/search/cliente",
		});
		jQuery("#valid2").validationEngine();
	});
</script>

<c:import url="/res/js/dateScript.txt" />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
		
	<form method="post">

		<h1>${pageTitle}</h1>
		<h2>${result}</h2>
		<h2>${message1}</h2>
	</form>
		
	<article>
		<div class = "table1">
			<table>
				<tr>
					<td>Nombre(s)</td>
					<td>Apellido Paterno</td>
					<td>Apellido Materno</td>
					<td>Correo electrónico</td>
				</tr>
				<c:forEach var="cliente" items="${clienteList}">
					<tr>
						<td>${cliente.persona.nombrePer}</td>
						<td>${cliente.persona.apPatPer}</td>
						<td>${cliente.persona.apMatPer}</td>
						<td>${cliente.persona.emailPer}</td>
						<td>
							<a href="<c:url value='/cliente/update/${cliente.idCli}' />">Modificar</a> &nbsp;  
							<a class="confirmDelete" href="<c:url value='/cliente/delete/${cliente.idCli}' />">Eliminar</a><br/>
						</td>
					</tr>
				</c:forEach>
			</table>
		
		
		<!-- Paginación por búsqueda de clientes -->
			<c:if test="${sessionScope.showPagesFromSearch == true}">
				<h1>Siiiiii, ${totalPages}</h1>
				<c:url var="firstUrl" value="/cliente/search/1" />
				<c:url var="lastUrl" value="/cliente/search/${totalPages}" />
				<c:url var="prevUrl" value="/cliente/search/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/cliente/search/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/cliente/search/${i}" />
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
				<c:url var="firstUrl" value="/cliente/queryall/1" />
				<c:url var="lastUrl" value="/cliente/queryall/${totalPages}" />
				<c:url var="prevUrl" value="/cliente/queryall/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/cliente/queryall/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/cliente/queryall/${i}" />
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