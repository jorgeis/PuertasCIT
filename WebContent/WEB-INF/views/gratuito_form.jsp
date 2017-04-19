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


<script type="text/javascript">
$(document).ready(function() {
    $("#fInicioEveGra").change(function() {
	$(".select-municipio select").empty();
	var path = $("#path").val();
    var url = path + "/json/search/daygratuito";
	$.ajax({ 
        url: url, 
        data: { term: $("#fInicioEveGra").val()}, 
        success: function (data) {
            $.each(data, function(index, element) {
            	$(".select-municipio select").append('<option value="'+ index +'">'+ element +'</option>');
            });
        }
    });
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
		
	<h1>Solicitud de reserva de espacio gratuito</h1>
	<h2>${result}</h2>
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos del Solicitante
			</legend>
			<label>Municipio</label>
				<div class="select-municipio" >
					<select name="nombreMun" id="municipioSel" class="validate[required]" data-prompt-position="bottomLeft:20,5">
					</select>
				</div>
			<label class="light">Nombre(s)</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombreUsrGra" />
			<label class="light">Apellido Paterno</label>
				<sf:input type="text"  class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apPatUsrGra" />
			<label class="light">Apellido Materno(s)</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apMatUsrGra" />
			<label class="light">Cargo</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="cargoUsrGra" />
			<label class="light">Teléfono</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="telefonoUsrGra" />
			<label class="light">Correo electrónico</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="emailUsrGra" />
			<label class="light">Sexo</label> <br />		
				<sf:radiobutton path="sexoUsrGra" value="Masculino" class="validate[required]" data-prompt-position="bottomLeft:20,26" /><label for="Masculino" class="light">Masculino</label><br> 
				<sf:radiobutton path="sexoUsrGra" value="Femenino" class="validate[required]" /><label for="Femenino" class="light">Femenino</label><br /><br />			
			</fieldset>
		
			<fieldset>
				<legend>
					<span class="number">&nbsp;</span>&nbsp;Datos del Evento
				</legend>
			<label class="light">Nombre del evento</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombreEveGra" />
			<label class="light">Objetivo</label>	
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="objetivoEveGra" />
			<label class="light">Impacto</label>	
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="impactoEveGra" />
			<label>Área solicitada para el evento</label>
				<select class="validate[required]" data-prompt-position="bottomLeft:20,5" name="idArea">
					<c:forEach var="area" items="${areaGratuitaList}"> 
						<option value="${area.idArea}"> ${area.nombreArea} </option>
					</c:forEach> 
				</select>
			<label class="light">Número de Asistentes</label>	
				<sf:input type="number" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numAsistEveGra" />
			<label class="light">Fecha del Evento</label>
				<input type="text" class="validate[required] datetimepicker1" data-prompt-position="bottomLeft:20,5" readonly="readonly" name="fInicioEveGra" id="fInicioEveGra"/>
			<label class="light">Hora de inicio del Evento</label>
				<input type="text" class="validate[required] datetimepicker2" data-prompt-position="bottomLeft:20,5" readonly="readonly" name="hInicioEveGra" />
			<label class="light">Hora de fin del Evento</label>
				<input type="text" class="validate[required] datetimepicker2" data-prompt-position="bottomLeft:20,5" readonly="readonly" name="hFinEveGra" />
			<label class="light">Población objetivo</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="poblacionObjEveGra" />
			<label class="light">Dia objetivo</label>
				<input type="text" id="dia1" />		
			<label class="light">Mes objetivo</label>
				<input type="text" id="mes1" />		
			<label class="light">Año objetivo</label>
				<input type="text" id="anio1" />
			<label class="light">Full objetivo</label>
				<input type="text" id="full1" />			
			</fieldset>
		
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos de la organización
			</legend>
			<label class="light">Siglas</label>
				<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="siglasOrg" />
			<label class="light">Nombre</label>
				<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="nombreOrg" />
		</fieldset>
		
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos del Responsable del Evento
			</legend>
			<label class="light">Nombre(s)</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombreRespGra" />
			<label class="light">Apellido Paterno</label>
				<sf:input type="text"  class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apPatRespGra" />
			<label class="light">Apellido Materno(s)</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apMatRespGra" />
			<label class="light">Cargo</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="cargoRespGra" />
		</fieldset>
		
		<button type="submit">Guardar</button>
	</sf:form>
			
	<br/><br/>
</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />

<script>
	function disabledWeekdays(date) {
	    var day = date.getDay();
	    //0 is Sunday, 1 is Monday, 2 is Tuesday , 3 is Wednesday, 4 is Thursday, 5 is Friday and 6 is Saturday
	    if (day == 0) {
	        return [false] ; 
	    } else { 
	        return [true] ;
	    }
	}

	$.datetimepicker.setLocale('es');
	var today = new Date();
	var myToday = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, 0, 0);
	var myMinTime = 0;
	
	$('.datetimepicker').datetimepicker({
		format : 'd/M/Y H:i',
		value : myToday, 
		beforeShowDay: disabledWeekdays,
		minDate: "1"}
		);
	
	$('.datetimepicker1').datetimepicker({
		timepicker : false,
		format : 'd/m/Y',
		beforeShowDay: disabledWeekdays,
		minDate:0, 
		onSelectDate: function(ct) {
			myToday = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, 0, 0);
			var selectedDay = new Date(ct.getFullYear(), ct.getMonth(), ct.getDate(), 0, 0, 0)
			var year = ct.getFullYear();
			var month = ct.getMonth() + 1;
			var day = ct.getDate();
			$('#dia1').val(selectedDay);
			$('#mes1').val(myToday);
	        if(		selectedDay.getDate() == myToday.getDate() && 
	        		selectedDay.getMonth() == myToday.getMonth() &&
	        		selectedDay.getFullYear() == myToday.getFullYear()) {
	        	 $('#anio1').val("Si son iguales");
	        	myMinTime = 0;
	        }
	        else {
	        	 $('#anio1').val("No son iguales");
	        	myMinTime = false; 
	        }
	        $('#full1').val(myMinTime);
	        $('.datetimepicker2').datetimepicker('reset');
	        $('.datetimepicker2').datetimepicker('setOptions', {minTime : myMinTime});
	    }}
		);
	
	$('.datetimepicker2').datetimepicker({
		datepicker : false,
		format : 'H:i',
		minTime : myMinTime}
		);
</script>