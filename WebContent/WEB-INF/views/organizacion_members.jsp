<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
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

	<!-- Article - Formulario -->
	<article class="featured">		
		<form method="post" action="${pageContext.request.contextPath}/clienteform">
			<h1>Miembros de ${siglasOrg}</h1>
			<input type="hidden" name="idOrgParam" value="${idOrg}"/>
			<button type="submit">Agregar Nuevo Miembro</button>
		</form>	
		
		<div class = "table1">
			<table>
				<tr>
					<td>Nombre(s)</td>
					<td>Apellido Paterno</td>
					<td>Apellido Materno</td>
					<td>Correo electrónico</td>
					<td>Nivel</td>
				</tr>
				<c:forEach var="cliente" items="${clienteList}" varStatus="status">
					<tr>
						<td>${cliente.persona.nombrePer}</td>
						<td>${cliente.persona.apPatPer}</td>
						<td>${cliente.persona.apMatPer}</td>
						<td>${cliente.persona.emailPer}</td>
						
						<td>${cargoList[status.index]}</td>
						
						<td>
							<a href="<c:url value='/cliente/update/${cliente.idCli}' />">Opt1</a> &nbsp;  
							<a class="confirmDelete" href="<c:url value='/cliente/delete/${cliente.idCli}' />">Opt2</a><br/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />