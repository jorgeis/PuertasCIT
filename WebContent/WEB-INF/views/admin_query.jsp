<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page session="true" %>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="" />
<meta name="keywords" content="" />
<link href='http://fonts.googleapis.com/css?family=Nunito:400,300'	rel='stylesheet' type='text/css'>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic" rel="stylesheet" type="text/css" />	
<script src='<c:url value = "/res/js/jquery-1.8.1.min.js" />'></script>
<script src='<c:url value = "/res/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=40&amp;mobileUI.openerWidth=60" />'></script>
<script src='<c:url value = "/res/js/jquery.dropotron-1.1.js" />'></script>
<script src='<c:url value = "/res/js/jquery.slidertron-1.2.js" />'></script>
<script src='<c:url value = "/res/js/init.js" />'></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
<link rel="stylesheet" href='<c:url value = "/res/css/own.css" />' />

<script>
	jQuery(document).ready(function(){
		jQuery(".confirmActivate").on("click", function() {
	        return confirm("Este cambiará su estado a Activo. ¿Continuar?");
	    });
		jQuery(".confirmDelete").on("click", function() {
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

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />

	<!-- Article - Formulario -->
	<article class="featured"> 
<%-- 		<form method="post" id="valid2" action="${pageContext.request.contextPath}/admin/search" > --%>
<!-- 			<h1>Buscar administradores</h1> -->
<%-- 			<input type="hidden" id="path" value="${pageContext.request.contextPath}"/> --%>
<!-- 			<input type="text" name="busqueda" id="busqueda" class="validate[required]" /> -->
<!-- 			<button type="submit">Buscar</button> -->
<!-- 		</form>	 -->


		<form>
			<h1>${pageTitle}</h1>
			<c:if test="${result != null}">
				<h2>${result}</h2>
			</c:if>
			<c:if test="${message1 != null}">
				<h2>${message1}</h2>
			</c:if>
		</form>


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
					<td>Acción</td>
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
						
							<!-- Compara el título de la página para saber que hipervínculos de acción mostrar -->
							<c:if test="${pageTitle != 'Consultar Administradores Activos'}">
	  							<a class="confirmActivate" href="<c:url value='/admin/activate/${admin.idAd}' />">Activar</a><br/>
							</c:if>
								
								
							<c:if test="${pageTitle == 'Consultar Administradores Activos'}">
	 								<a href="<c:url value='/admin/update/${admin.idAd}' />">Modificar</a> &nbsp;  
								<a class="confirmDelete" href="<c:url value='/admin/delete/${admin.idAd}' />">Eliminar</a><br/>
							</c:if>
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
				<c:url var="firstUrl" value="/admin/query${searchParam}/1" />
				<c:url var="lastUrl" value="/admin/query${searchParam}/${totalPages}" />
				<c:url var="prevUrl" value="/admin/query${searchParam}/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/admin/query${searchParam}/${currentIndex + 1}" />
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
					<c:url var="pageUrl" value="/admin/query${searchParam}/${i}" />
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