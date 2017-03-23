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
	 JSON ubicado en controlador, donde url tiene el RequestMappng donde se ubica el servidor -->
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
// 	// Mostrar valor de una opción en <select> al hacer click sobre ella
// 	$('#estadoSel').on('change', function() {
// 		alert(this.value);
// 	});
	
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
		//alert(perMun);
		$('#municipioSel').find('option[value="' + perMun + '"]').prop('selected',true).trigger('change');
	}, 200); 
});
</script>



<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>

	<!-- Article - Formulario -->
	<article class="featured">

		<input type="hidden" id="path" value=${pageContext.request.contextPath} />
		
		<!-- Formularios de prueba para JSON y AJAX -->
<%-- 		<form action="${pageContext.request.contextPath}/findMunicipio"> --%>
<!-- 			<input type="text" name="term"/> -->
<!-- 			<input type="submit" value="test json"/> -->
<!-- 		</form> -->
		
<!-- 		<br /><br /> -->
		
<!-- 		Buscar municipios con AJAX -->
<!-- 		<input type="hidden" id="path" value=${pageContext.request.contextPath} /> -->
<!-- 		<input type="text" id="ajaxText"/> -->
<!-- 		<input type="submit" id="ajaxSearch" value="Buscar asincronamente"/> -->
<!-- 		<div id="ajaxResult"> -->
		
<!-- 		</div> -->
<!-- 		<br /><br /> -->
		
		<sf:form method="post" id="valid"
			action="${pageContext.request.contextPath}/clientesave"
	 		commandName="personaClienteDireccionWrapper"> 
			
		<h1>Registrar Nuevo Cliente</h1>
		<h2>${result}</h2>
			<fieldset>
				<legend>
					<span class="number">1</span>Información de Cuenta
				</legend>
				<label class="light">Correo electrónico</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="emailPer" />
				<label class="light">Contraseña</label>
					<sf:input type="password" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="passCli" />
			</fieldset>
			
			<fieldset>
				<legend>
					<span class="number">2</span>Información Básica
				</legend>
				<label class="light">Nombre(s)</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombrePer" />
				<label class="light">Apellido Paterno</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apPatPer" />
				<label class="light">Apellido Materno</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="apMatPer" />
				<label class="light">CURP</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="curpPer" />
				<label class="light">Correo electrónico alterno</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="emailAltCli" />
				<label class="light">Sexo</label> <br />		
					<sf:radiobutton path="sexoCli" value="Masculino" class="validate[required]" data-prompt-position="bottomLeft:20,26" /><label for="Masculino" class="light">Masculino</label><br> 
					<sf:radiobutton path="sexoCli" value="Femenino" class="validate[required]" /><label for="Femenino" class="light">Femenino</label><br /><br />			
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
					<input type="text" value="<c:out value="${otroOcup}"/>" class="validate[required]" data-prompt-position="bottomLeft:20,5" name="otroOcupacion" />	
				</div>
				<label>Objetivo</label> 
					<sf:textarea class="validate[required]" data-prompt-position="bottomLeft:20,5" path="objetivoCli"></sf:textarea>
				<label>Avatar</label> 
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="avatarCli" />
			</fieldset>
			
			<fieldset>
				<legend>
					<span class="number">3</span>Dirección
				</legend>
				<label class="light">Calle</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="calleDir" />
				<label class="light">Número Exterior</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numExtDir" />
				<label class="light">Número Interior</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numIntDir" />
				<label class="light">Colonia</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="coloniaDir" />
				<label>Estado</label> 
				<div class="select-estado" >
					<sf:select id="estadoSel" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="idEstado">
						<c:forEach var="estado" items="${estadoList}"> 
							<sf:option value="${estado.idEst}"> ${estado.nombreEst} </sf:option> 
						</c:forEach> 
					</sf:select>
				</div>
					
				
				<label>Municipio</label>
				<div class="select-municipio" >
					<sf:select name="nombreMun" id="municipioSel" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="idMun">
					</sf:select>
				</div>
				
				<label class="light">Código Postal</label>
					<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="cpDir" />
					
				<sf:input type="text" path="idMun" class="persistMun" />
				<sf:input type="text" path="idEstado" class="persistEst" />
				<sf:input type="text" path="idCli" />
					
			</fieldset>
			
			
			
			<button type="submit">Guardar</button>
		</sf:form>
				
		<br/><br/>
			
		
		
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />