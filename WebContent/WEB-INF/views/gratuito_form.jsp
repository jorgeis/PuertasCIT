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

<style>
 .xdsoft_timepicker .xdsoft_time_box .xdsoft_time.disabled:hover {
    color:white !important;
    background: red !important;
}
.xdsoft_timepicker .xdsoft_time_box .xdsoft_time.disabled {
	color: white;
	background-color: red;
}

</style>

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
		
	<h1>Solicitud de reserva de espacio gratuito</h1>
	<h2>${result}</h2>
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos del Solicitante
			</legend>
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
				<input type="text" class="validate[required] datetimepicker2" data-prompt-position="bottomLeft:20,5" readonly="readonly" name="hInicioEveGra" id="hInicioEveGra"/>
			<label class="light">Hora de fin del Evento</label>
				<input type="text" class="validate[required] datetimepicker3" data-prompt-position="bottomLeft:20,5" readonly="readonly" name="hFinEveGra" id="hFinEveGra"/>
			<label class="light">Población objetivo</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="poblacionObjEveGra" />	
			<label class="light">Extra</label>
				<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" id="extra" name="extra" />	
			
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
	
	var specificDates = [];
	var hoursToTakeAway = [[14,15],[17]];
	var ind = -1;
	
	var startTime = 0;
	var turn = false;
	var maxTime = 50;

	
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
		minDate : 0, 
		inline: false,
		scrollInput : false,
		onSelectDate: function(ct) {
			myToday = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, 0, 0);
			var selectedDay = new Date(ct.getFullYear(), ct.getMonth(), ct.getDate(), 0, 0, 0);
			var year = ct.getFullYear();
			var month = ct.getMonth() + 1;
			var day = ct.getDate();		
			
			// Hora mínima segun el día
	        if(		selectedDay.getDate() == myToday.getDate() && 
	        		selectedDay.getMonth() == myToday.getMonth() &&
	        		selectedDay.getFullYear() == myToday.getFullYear()) {
	        	myMinTime = 0;
	        }
	        else {
	        	myMinTime = false; 
	        }
	        
	        // Reservaciones hechas
	        var diaBien = ("0" + ct.getDate()).slice(-2) + "/" + ("0" + (ct.getMonth() + 1)).slice(-2) + "/" + ct.getFullYear();
	        specificDates = [diaBien];
	        ind = specificDates.indexOf(diaBien);
			
			// Consultar horas reservadas en día seleccionado
			var path = $("#path").val();
   		 	var url = path + "/json/search/daygratuito";
   		 	$.ajax({ 
	   	        url: url, 
	   	        data: { term: diaBien}, 
   	        	success: function (data) {
   	        		hoursToTakeAway = [];
   	            	$.each(data, function(index, element) {
   	            		hoursToTakeAway.push([element]);
   	            	});
   	       		}
   		 	});
			$('.datetimepicker2').datetimepicker('reset');
	        $('.datetimepicker2').datetimepicker('setOptions', {minTime : myMinTime});
	        $('.datetimepicker3').datetimepicker('reset');
	        $('.datetimepicker3').datetimepicker('setOptions', {minTime : myMinTime});
	    }}
		);
	
// 	Deshabilitar segun sertvidor JSON en @RequestMapping(value="/json/search/org", produces="application/json")
	
	$('.datetimepicker2').datetimepicker({
		datepicker : false,
		format : 'H:i',
		minTime : myMinTime,
		// Bloquear horas reservadas
		onGenerate:function(ct,$i){
			$('.xdsoft_time_variant .xdsoft_time').show();
			if(ind !== -1) {
				$('.xdsoft_time_variant .xdsoft_time').each(function(index){
					for(i=0; i<hoursToTakeAway.length; i++) {
						if(hoursToTakeAway[i].indexOf(parseInt($(this).text())) !== -1) {
							$(this).addClass('disabled');
			                $(this).fadeTo(1,.3);
			                $(this).prop('disabled',true);
						}
					}
	          });
			}
		},
		
		onSelectTime:function(ct,$i){
			startTime = $("#hInicioEveGra").val().slice(0,2);
			$("#extra").val(startTime);
			turn = false;
			maxTime = 50;
			
			
			$('.datetimepicker3').datetimepicker('reset');
	        $('.datetimepicker3').datetimepicker('setOptions', {minTime : myMinTime});
		}, 
	}
	);
	
	
	$('.datetimepicker3').datetimepicker({
		datepicker : false,
		format : 'H:i',
		minTime : myMinTime,
		
		// Bloquear horas reservadas
		onGenerate:function(ct,$i){
			$('.xdsoft_time_variant .xdsoft_time').show();
			if(ind !== -1) {
				turn = false;
				maxTime = 50;
				console.log("Vamos a entrar a each index");
				
				// Habilita todas las horas, quitando las propiedades modificadas en la selección anterior por parte del usuario
				$('.xdsoft_time_variant .xdsoft_time').each(function(index){
					$(this).removeClass('disabled');
	                $(this).fadeTo(1,1);
	                $(this).prop('disabled',false);
				});
				
				// Bloquea las horas mas una, de las reservaciones hechas. Esto para colocar en este cuadro las horas a las 
				// que puede terminar el evento, no comenzar, a diferencia del cuadro anterior
				$('.xdsoft_time_variant .xdsoft_time').each(function(index){
					for(i=0; i<hoursToTakeAway.length; i++) {
						if(hoursToTakeAway[i].indexOf(parseInt($(this).text())-1) !== -1) {
							$(this).addClass('disabled');
			                $(this).fadeTo(1,.3);
			                $(this).prop('disabled',true);
						} 
					
					}
					
					// Bloquea las horas anteriores a la hora de inicio de reservación
					if(parseInt($(this).text()) <= startTime) {
		                $(this).fadeTo(1,.3);
		                $(this).prop('disabled',true);
					}
					
					// Identifica la hora en la que ya existe una reservación, a partir de la hora de inicio seleccionada
					// en el cuadro anterior
					else if(parseInt($(this).text()) >= startTime && turn==false) {
						if($(this).prop('disabled')) {
							turn = true;
							console.log("Aqui inicia los deshabilitado con " + turn + ", " + $(this).text());
							maxTime = $(this).text().slice(0,2);
							console.log("maxTime " + maxTime);
						}
					}
					
					// Deshabilita las horas disponibles a partir de la siguiente hora de reservacion agendada previamente.
					// Esto para evitar que se seleccionen un periodo de tiempo que contenga a otro ya reservado.
					// Ej: Esto evita que se seleccione de 4 a 10, cuando ya existe una reservacion de 6 a 8.
					for(i=maxTime; i<24; i++) {
						if(parseInt($(this).text()) == i) {
							console.log("Se debe deshabilitar " + $(this).text());
							$(this).fadeTo(1,.3);
							$(this).prop('disabled',true);
						}
					}
	          });
			}
		}
	}
	);
</script>