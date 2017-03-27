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

<!-- Script para ocultar y mostrar campo dependiendo de opción seleccionada en <select> -->
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
    if($('#ocupacionCli').val() == 'Otro') {
        $('#otroOcupacion').show(); 
    } else {
        $('#otroOcupacion').hide(); 
    } 
});
</script>



<!-- Script para hacer consultas asíncronas a través de JSON. Necesita servidor 
	 JSON ubicado en controlador -->
 <script type="text/javascript">
// 	jQuery(document).ready(function(){
//  		$('#ajaxSearch').click(function(event) {
//  		   var path = $("#path").val();
//             var url = path + "/json/search/mun";
//             $.ajax({ 
//                  url: url, 
//                  data: { term: $("#ajaxText").val()}, 
//                  success: function (data) { 
//                  	$('#ajaxResult').empty();
//                      $.each(data, function(index, element) {
//                      	$('#ajaxResult').append(index, ': <b>' + element + '</b><br/>');
//                      });
//                  }
//              });
//          });
//  	});
</script>

<!-- Script para llenar un select dependiendo de opción seleccionada en <select>, a través de una consulta a un servidor JSON 
	 En este caso es llenar los municipios dependiendo del estado seleccionado -->
<script type="text/javascript">
$(document).ready(function() {
    $(".select-estado select").change(function() {
	$(".select-municipio select").empty();
	var path = $("#path").val();
    var url = path + "/json/search/mun";
	$.ajax({ 
        url: url, 
        data: { term: $("#estadoSel").val()}, 
        success: function (data) {
            $.each(data, function(index, element) {
            	$(".select-municipio select").append('<option value="'+ index +'">'+ element +'</option>');
            });
        }
    });
    });
});
</script>

<!-- Script para llenar los select de estado y municipio automáticamente cuando se redirija al mismo formulario y persistir los datos -->
<script>
$(function() {	
	var perEst = $('.persistEst').val();
	var perMun = $('.persistMun').val();
	
	if (perEst == 0) {
	    perEst = 1;
	}
	if (perMun == 0) {
	    perMun = 1;
	}
	
	$('#estadoSel').find('option:nth-child(' + perEst + ')').prop('selected',true).trigger('change');
		
	setTimeout(function(){
		$('#municipioSel').find('option[value="' + perMun + '"]').prop('selected',true).trigger('change');
	}, 200); 
});
</script>

<script>
	jQuery(document).ready(function(){
		var path = $("#path").val();
		jQuery("#siglas").autocomplete({
			source: path + "/json/search/siglasorg",
		});
	});
</script>



<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>

<!-- Article - Formulario -->
<article class="featured">

	<input type="hidden" id="path" value=${pageContext.request.contextPath} />
	
	<sf:form method="post" id="valid"
		action="${pageContext.request.contextPath}/gratuitosave"
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
				<sf:select class="validate[required]" data-prompt-position="bottomLeft:20,5" path="area">
					<c:forEach var="area" items="${areaGratuitaList}"> 
						<sf:option value="${area.idArea}"> ${area.nombreArea} </sf:option>
					</c:forEach> 
				</sf:select>
			<label class="light">Número de Asistentes</label>	
				<sf:input type="number" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numAsistEveGra" />
			<label class="light">Fecha y hora de inicio del Evento</label>
				<sf:input type="text" id="datepicker" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="fhInicioEveGra" />
			<label class="light">Fecha y hora de fin del Evento</label>
				<sf:input type="text" id="datepicker" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="fhFinEveGra" />
			<label class="light">Población objetivo</label>	
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="poblacionObjEveGra" />
			</fieldset>
		
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos de la organización
			</legend>
			<label class="light">Siglas</label>
				<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" id="siglasOrg" />
			<label class="light">Nombre</label>
				<input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" id="nombreOrg" />
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