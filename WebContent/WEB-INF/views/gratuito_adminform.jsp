<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="" />
<meta name="keywords" content="" />
<script src='<c:url value = "/res/js/jquery-1.8.1.min.js" />'></script>
<link href='http://fonts.googleapis.com/css?family=Nunito:400,300'	rel='stylesheet' type='text/css'>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic" rel="stylesheet" type="text/css" />	
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/jquery.datetimepicker.css" />' />
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<script src='<c:url value = "/res/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=40&amp;mobileUI.openerWidth=60" />'></script>
<script src='<c:url value = "/res/js/jquery.dropotron-1.1.js" />'></script>
<script src='<c:url value = "/res/js/jquery.slidertron-1.2.js" />'></script>
<script src='<c:url value = "/res/js/init.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.datetimepicker.full.js" />'></script>
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />

<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#valid").validationEngine();
		$(".datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			yearRange : "1930:2100"
		});
	});
</script>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />

<!-- Article - Formulario -->
<article class="featured">

	<input type="hidden" id="path" value="${pageContext.request.contextPath}" />
	
	<sf:form method="post" id="valid"
		action="${pageContext.request.contextPath}/gratuitorg"
 		commandName="gratuito"> 
		
	<h1>Consulta de reserva de espacio gratuito</h1>
	<h2>${result}</h2>
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos del Solicitante
			</legend>
			<sf:input type="hidden" path="idGra" />
			<sf:input type="hidden" path="statusGra" />
			<label class="light">Nombre(s)</label>
				<sf:input type="text"   path="nombreUsrGra" readonly="true"/>
			<label class="light">Apellido Paterno</label>
				<sf:input type="text"    path="apPatUsrGra" readonly="true"/>
			<label class="light">Apellido Materno(s)</label>
				<sf:input type="text"   path="apMatUsrGra" readonly="true"/>
			<label class="light">Cargo</label>
				<sf:input type="text"   path="cargoUsrGra" readonly="true"/>
			<label class="light">Teléfono</label>
				<sf:input type="text"   path="telefonoUsrGra" readonly="true"/>
			<label class="light">Correo electrónico</label>
				<sf:input type="text"   path="emailUsrGra" readonly="true"/>
			<label class="light">Sexo</label> <br />	
				<sf:input type="text"   path="sexoUsrGra" readonly="true"/>	
			</fieldset>
		
			<fieldset>
				<legend>
					<span class="number">&nbsp;</span>&nbsp;Datos del Evento
				</legend>
			<label class="light">Nombre del evento</label>
				<sf:input type="text"   path="nombreEveGra" readonly="true"/>
			<label class="light">Objetivo</label>	
				<sf:input type="text"   path="objetivoEveGra" readonly="true"/>
			<label class="light">Impacto</label>	
				<sf:input type="text"   path="impactoEveGra" readonly="true"/>
			<label>Área solicitada para el evento</label>
				<input type="hidden" id="idArea" name="idArea" value="${idArea}"/>
				<input type="text" value="${nombreArea}"  readonly="readonly"/>
			<label class="light">Número de Asistentes</label>	
				<sf:input type="number"   path="numAsistEveGra" readonly="true"/>
			<label class="light">Fecha del Evento</label>
				<input type="text" readonly="readonly" name="fInicioEveGra" id="fInicioEveGra" value="${fInicioEveGra}"/>
			<label class="light">Hora de inicio del Evento</label>
				<input type="text" readonly="readonly" name="hInicioEveGra" id="hInicioEveGra" value="${hInicioEveGra}"/>
			<label class="light">Hora de fin del Evento</label>
				<input type="text" readonly="readonly" name="hFinEveGra" id="hFinEveGra" value="${hFinEveGra}" />
			<label class="light">Población objetivo</label>
				<sf:input type="text" readonly="true" path="poblacionObjEveGra" />	
			</fieldset>
		
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos de la organización
			</legend>
			<label class="light">Siglas</label>
				<input type="text" name="siglasOrg" readonly="readonly" value="${siglasOrg}"/>
			<label class="light">Nombre</label>
				<input type="text" name="nombreOrg" readonly="readonly" value="${nombreOrg}"/>
		</fieldset>
		
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos del Responsable del Evento
			</legend>
			<label class="light">Nombre(s)</label>
				<sf:input type="text"   path="nombreRespGra" readonly="true"/>
			<label class="light">Apellido Paterno</label>
				<sf:input type="text"    path="apPatRespGra" readonly="true"/>
			<label class="light">Apellido Materno(s)</label>
				<sf:input type="text"   path="apMatRespGra" readonly="true"/>
			<label class="light">Cargo</label>
				<sf:input type="text"   path="cargoRespGra" readonly="true"/>
		</fieldset>
		
		
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Decisión de la Reservación
			</legend>
			
			<label>Decisión</label> 
				<sf:select path="decisionGra" id="decisionGra">
					<sf:option value="Aceptada">Aceptada</sf:option>
					<sf:option value="Rechazada">Rechazada</sf:option>
				</sf:select>
			
			<label>Comentarios</label> 
				<sf:textarea class="validate[required]" data-prompt-position="bottomLeft:20,5" path="comentariosGra"></sf:textarea>
				
			<input type="hidden" id="idAd" name="idAd" value="${idAd}"/>
		</fieldset>
		
		<button type="submit">Guardar</button>
	</sf:form>
			
	<br/><br/>
</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />