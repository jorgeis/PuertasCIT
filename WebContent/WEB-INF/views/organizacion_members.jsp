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

<c:import url="/res/js/dateScript.txt" />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>

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



	<!-- Article - Formulario -->
	<!-- Vista si se hace la consulta de los miembros de una organización -->
	<c:if test="${actionView != 'orgrespsc'}">
		<article class="featured">		
			<form method="post">
				<h1>${siglasOrg}</h1>
				<h2>${result}</h2>
			</form>		
		
			<br /><br />
		
			<form method="post" action="${pageContext.request.contextPath}/clienteform">
				<h1>Miembros Activos</h1>
			</form>	
			<div class="table1">
				<table>
					<tr style="border-radius: 5px;">
						<td>Nombre(s)</td>
						<td>Apellido Paterno</td>
						<td>Apellido Materno</td>
						<td>Correo electrónico</td>
						<td>Nivel</td>
						<td>Acción</td>
					</tr>
					<c:forEach var="cliente" items="${clienteList}" varStatus="status">
						<c:if test="${statusList[status.index] == 'Activo'}">
							<tr>
								<td>${cliente.persona.nombrePer}</td>
								<td>${cliente.persona.apPatPer}</td>
								<td>${cliente.persona.apMatPer}</td>
								<td>${cliente.persona.emailPer}</td>
								<td>${cargoList[status.index]}</td>
								<td>
									<form method="post" action="${pageContext.request.contextPath}/org/deletemembersc" >
									<input type="hidden" name="idOrgParam" value="${idOrg}"/>
									<input type="hidden" name="idCliParam" value="${cliente.idCli}"/>
									<button type="submit">Eliminar</button>
								</form>	
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			
			<br /><br />
			
			<form method="post" action="${pageContext.request.contextPath}/clienteform">
				<h1>Miembros Pendientes</h1>
			</form>	
			<div class="table1">
				<table>
					<tr style="border-radius: 5px;">
						<td>Nombre(s)</td>
						<td>Apellido Paterno</td>
						<td>Apellido Materno</td>
						<td>Correo electrónico</td>
					</tr>
					<c:forEach var="cliente" items="${clienteList}" varStatus="status">
						<c:if test="${statusList[status.index] == 'Pendiente'}">
							<tr>
								<td>${cliente.persona.nombrePer}</td>
								<td>${cliente.persona.apPatPer}</td>
								<td>${cliente.persona.apMatPer}</td>
								<td>${cliente.persona.emailPer}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			
			<br /><br />
			
			<form method="post" action="${pageContext.request.contextPath}/clienteform">
				<h1>Miembros Borrados</h1>
			</form>	
			<div class="table1">
				<table>
					<tr style="border-radius: 5px;">
						<td>Nombre(s)</td>
						<td>Apellido Paterno</td>
						<td>Apellido Materno</td>
						<td>Correo electrónico</td>
						<td>Acción</td>
					</tr>
					<c:forEach var="cliente" items="${clienteList}" varStatus="status">
						<c:if test="${statusList[status.index] == 'Borrado'}">
							<tr>
								<td>${cliente.persona.nombrePer}</td>
								<td>${cliente.persona.apPatPer}</td>
								<td>${cliente.persona.apMatPer}</td>
								<td>${cliente.persona.emailPer}</td>
								<td>
									<form method="post" action="${pageContext.request.contextPath}/org/reinvitemembersc" >
									<input type="hidden" name="idOrgParam" value="${idOrg}"/>
									<input type="hidden" name="idCliParam" value="${cliente.idCli}"/>
									<button type="submit">Reinvitar</button>
								</form>	
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
			
			<br /> <br />
			
			<form method="post">
				<h1>Acciones</h1>
			</form>
			
			<form method="post" action="${pageContext.request.contextPath}/clienteform">
				<input type="hidden" name="idOrgParam" value="${idOrg}"/>
				<button type="submit">Agregar Nuevo Miembro</button>
			</form>	
			
			<form method="post" action="${pageContext.request.contextPath}/org/querymembers">
				<input type="hidden" name="param1" value="${idOrg}"/>
				<input type="hidden" name="param2" value="orgrespsc"/>
				<button type="submit">Cambiar Responsable</button>
			</form>	
		</article>
	</c:if>
	
	
	
	
	<!-- Vista si se desea cambiar el responsable de empresa -->
	<c:if test="${actionView == 'orgrespsc'}">
		<article class="featured">		
			<form method="post">
				<h1>${siglasOrg}</h1>
				<h2>${result}</h2>
			</form>		
		
			<br /><br />
		
			<form method="post" action="${pageContext.request.contextPath}/clienteform">
				<h1>Cambiar responsable</h1>
			</form>	
			<div class="table1">
				<table>
					<tr style="border-radius: 5px;">
						<td>Nombre(s)</td>
						<td>Apellido Paterno</td>
						<td>Apellido Materno</td>
						<td>Correo electrónico</td>
						<td>Nivel</td>
						<td>Acción</td>
					</tr>
					<c:forEach var="cliente" items="${clienteList}" varStatus="status">
						<c:if test="${statusList[status.index] == 'Activo'}">
							<tr>
								<td>${cliente.persona.nombrePer}</td>
								<td>${cliente.persona.apPatPer}</td>
								<td>${cliente.persona.apMatPer}</td>
								<td>${cliente.persona.emailPer}</td>
								<td>${cargoList[status.index]}</td>
								<td>
									<form method="post" action="${pageContext.request.contextPath}/org/respmembersc" >
									<input type="hidden" name="idOrgParam" value="${idOrg}"/>
									<input type="hidden" name="idCliParam" value="${cliente.idCli}"/>
									<button type="submit">Elegir nuevo responsable</button>
								</form>	
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</article>			
	</c:if>
	
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />