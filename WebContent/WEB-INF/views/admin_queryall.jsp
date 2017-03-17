<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
<c:import url="/res/js/dateScript.txt" />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>

	<!-- Article - Formulario -->
	<article class="featured">
	
		<sf:form method="post" id="valid"
			action="${pageContext.request.contextPath}/admin/save" 
	 		commandName="personaAdminWrapper">
			
		<h1>Registrar Nuevo Administrador</h1>
		<h2>${result}</h2>
			<fieldset>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="nombrePer" placeholder="Nombre(s)"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="apPatPer" placeholder="Apellido Paterno"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="apMatPer" placeholder="Apellido Materno"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="emailPer" placeholder="Correo eléctrico"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="curpPer" placeholder="CURP"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="areaAd" placeholder="Área"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="cargoAd" placeholder="Cargo"/>
			<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="telefonoAd" placeholder="Teléfono"/>		
			
			<sf:select path="rolAd" class="validate[required]">
				<sf:option value="">Elige una opción</sf:option>
				<sf:option value="ROLE_ROOT">Super Administrador</sf:option>
				<sf:option value="ROLE_ADMIN">Administrador</sf:option>
			</sf:select>
			</fieldset>
			
			<button type="submit">Guardar</button>
		</sf:form>
				
		<br/><br/>
		
		<c:forEach var="admin" items="${adminList}">
			${admin} <br/> ${admin.persona}<br/>
			<a href="<c:url value='/secure/cmanage/conyuge/update' />">Modificar</a>  
			<a href="<c:url value='/secure/cmanage/conyuge/update' />">Eliminar</a>
			<br/><br/>
		</c:forEach>
		
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />