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

<script>
$(function() {
	
	var perIdCli = $('.persistIdCli').val();
	
	if(perIdCli == 0) {
        $('#accountCli').show(); 
    } else {
        $('#accountCli').hide(); 
    } 
	
});
persistIdCli
</script>



<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>

<!-- Article - Formulario -->
<article class="featured">

	<input type="hidden" id="path" value=${pageContext.request.contextPath} />
	
	<sf:form method="post" id="valid"
		action="${pageContext.request.contextPath}/orgsave"
 		commandName="organizacionDireccionWrapper"> 
		
	<h1>Alta de nueva organización</h1>
	<h2>${result}</h2>
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Datos de la organización
			</legend>
			<label class="light">Nombre</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="nombreOrg" />
			<label class="light">Siglas</label>
				<sf:input type="text"  class="validate[required]" data-prompt-position="bottomLeft:20,5" path="siglasOrg" />
			<label class="light">RFC</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="rfcOrg" />
			<label class="light">Número de Trabajadores</label>
				<sf:input type="number" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="numTrabajadoresOrg" />
			<label class="light">Teléfono</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="telefonoOrg" />
			<label class="light">Sitio Web</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="webOrg" />
			<label class="light">Giro de la Organización</label>
				<sf:input type="text" class="validate[required]" data-prompt-position="bottomLeft:20,5" path="giroOrg" />
		
			<label>Sector Empresarial</label>
				<sf:select class="validate[required]" data-prompt-position="bottomLeft:20,5" path="sectorEmp">
					<c:forEach var="sector" items="${sectorEmpList}"> 
						<sf:option value="${sector.idSE}"> ${sector.nombreSE} </sf:option>
					</c:forEach> 
				</sf:select>
		</fieldset>
				
		<fieldset>
			<legend>
				<span class="number">&nbsp;</span>&nbsp;Dirección de la Organización
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
				
			<sf:input type="hidden" path="idMun" class="persistMun" />
			<sf:input type="hidden" path="idEstado" class="persistEst" />
			<input type="hidden" name="idCli" value="5" />
<%-- 			<input type="hidden" name="idCli" value="${idCli}" /> --%>
			<%-- 			<sf:input type="hidden" path="idCli" class="persistIdCli"/> --%>
				
		</fieldset>
	
		
		
		
		<button type="submit">Guardar</button>
	</sf:form>
			
	<br/><br/>
		
	
	
</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />