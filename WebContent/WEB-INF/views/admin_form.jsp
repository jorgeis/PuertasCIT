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
	        return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
	    });
		var path = $("#path").val();
		jQuery("#busqueda").autocomplete({
			source: path + "/json/search/admin",
		});
		jQuery("#valid").validationEngine();
		jQuery("#valid2").validationEngine();
	});
</script>

	<!-- Article - Formulario -->
	<article class="featured">
	
		<sf:form method="post" id="valid"
			action="${pageContext.request.contextPath}/admin/save" 
	 		commandName="personaAdminWrapper">
			
		<h1>Registrar Nuevo Administrador</h1>
		<h2>${result}</h2>
			<fieldset>
			<sf:input type="hidden" path="idAd" />
			
			<label >Nombre(s):</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombrePer" />
			
			<label >Apellido Paterno</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apPatPer" />
			
			<label >Apellido Materno</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apMatPer" />
			
			<label >Correo electrónico</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="emailPer" />
			
			<label >CURP</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="curpPer" />
			
			<label >Área</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="areaAd" />
			
			<label >Cargo</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="cargoAd" />
			
			<label >Teléfono</label>
			<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="telefonoAd" />		
			
			<label >Permisos</label>
			<sf:select path="rolAd" class="validate[required]">
				<sf:option value="">Elige una opción</sf:option>
				<sf:option value="ROLE_ROOT">Super Administrador</sf:option>
				<sf:option value="ROLE_ADMIN">Administrador</sf:option>
			</sf:select>
			</fieldset>
			
			<button type="submit">Guardar</button>
		</sf:form>
	</article>
	
	
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />