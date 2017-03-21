<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<c:import url="/res/js/dateScript.txt" />
<script>
$(function() {
    $('#otroOcupacion').hide(); 
    $('#ocupacionCli').change(function(){
        if($('#ocupacionCli').val() == 'Otro') {
            $('#otroOcupacion').show(200); 
        } else {
            $('#otroOcupacion').hide(200); 
        } 
    });
});
</script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>

	<!-- Article - Formulario -->
	<article class="featured">
	
		<sf:form method="post" id="valid"
			action="${pageContext.request.contextPath}/clientesave" 
	 		commandName="personaClienteWrapper"> 
			
		<h1>Registrar Nuevo Cliente</h1>
		<h2>${result}</h2>
			<fieldset>
				<legend>
					<span class="number">1</span>Información Básica
				</legend>
				<label class="light">Nombre(s)</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombrePer" />
				<label class="light">Apellido Paterno</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apPatPer" />
				<label class="light">Apellido Materno</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apMatPer" />
				<label class="light">Correo electrónico</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="emailPer" />
				<label class="light">CURP</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="curpPer" />
				<label class="light">Correo electrónico alterno</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="emailAltCli" />
					
				<label class="light">Sexo</label> <br />		
					<sf:radiobutton path="sexoCli" value="M" /><label for="Masculino" class="light">Masculino</label><br> 
					<sf:radiobutton path="sexoCli" value="F" /><label for="Femenino" class="light">Femenino</label><br /><br />
											
				<label class="light">Teléfono fijo</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="telFijoCli" />	
				<label class="light">Teléfono móvil</label>	
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="telMovilCli" />		
				<label for="fNacCli" class="light">Fecha de Nacimiento</label>
					<sf:input type="text" id="datepicker" class="validate[required]" data-prompt-position="bottomLeft:20,5" 
						path="fNacCli" />
				
				<label>Ocupación</label> 
					<sf:select path="ocupacionCli" id="ocupacionCli">
						<sf:option value="Freelancer">Freelancer</sf:option>
						<sf:option value="Empresario">Empresario</sf:option>
						<sf:option value="Investigador">Investigador</sf:option>
						<sf:option value="Emprendedor">Emprendedor</sf:option>
						<sf:option value="Estudiante">Estudiante</sf:option>
						<sf:option value="Servicios Profesionales">Servicios Profesionales</sf:option>
						<sf:option value="Otro">Otro</sf:option>
					</sf:select>
				
				<div id="otroOcupacion">
					<label>Escribe tu ocupación</label> 
					<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="otroOcupacion" />	
				</div>
			
				<label>Objetivo</label> 
					<sf:textarea class="validate[required]" data-prompt-position="bottomLeft:20,5" path="objetivoCli"></sf:textarea>
					
				<label>Avatar</label> 
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="avatarCli" />
					
					
				<label>Estado</label> 
					<select id="estadoSel">
						<c:forEach var="estado" items="${estadoList}"> 
							<option value="${estado.idEst}"> ${estado.nombreEst} </option> 
						</c:forEach> 
					</select>
			</fieldset>
			
<!-- 			<fieldset> -->
<!-- 				<legend> -->
<!-- 					<span class="number">2</span>Dirección -->
<!-- 				</legend> -->
<!-- 				<label class="light">Calle</label> -->
<%-- 					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="calleDir" /> --%>
<!-- 				<label class="light">Número Exterior</label> -->
<%-- 					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numExtDir" /> --%>
<!-- 				<label class="light">Número Interior</label> -->
<%-- 					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numIntDir" /> --%>
<!-- 				<label class="light">Colonia</label> -->
<%-- 					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="coloniaDir" /> --%>
<!-- 				<label class="light">Código Postal</label> -->
<%-- 					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="cpDir" /> --%>
				
				
			
<!-- 			</fieldset> -->
			
			
			
			
			
			
			<button type="submit">Guardar</button>
		</sf:form>
				
		<br/><br/>
			
		
		
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />