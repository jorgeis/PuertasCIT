<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page session="true" %>

<!DOCTYPE>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="" />
<meta name="keywords" content="" />
<link href='http://fonts.googleapis.com/css?family=Nunito:400,300'	rel='stylesheet' type='text/css'>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic" rel="stylesheet" type="text/css" />	
<script src='<c:url value = "/res/js/jquery-1.8.1.min.js" />'></script>
<script src='<c:url value = "/res/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=40&amp;mobileUI.openerWidth=60" />'></script>
<script src='<c:url value = "/res/js/jquery.dropotron-1.1.js" />'></script>
<script src='<c:url value = "/res/js/jquery.slidertron-1.2.js" />'></script>
<script src='<c:url value = "/res/js/init.js" />'></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
<link rel="stylesheet" href='<c:url value = "/res/css/own.css" />' />

<script>
	jQuery(document).ready(function(){
		jQuery(".confirmActivate").on("click", function() {
	        return confirm("Este cambiará su estado a Activo. ¿Continuar?");
	    });
		jQuery(".confirmDelete").on("click", function() {
	        return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
	    });
		var path = $("#path").val();
		jQuery("#busqueda").autocomplete({
			source: path + "/json/search/admin",
			minLength: 2
		});
		jQuery("#valid").validationEngine();
		jQuery("#valid2").validationEngine();
	});
</script>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />

	<!-- Article - Formulario -->
	<article class="featured"> 
<%-- 		<form method="post" id="valid2" action="${pageContext.request.contextPath}/admin/search" > --%>
<!-- 			<h1>Buscar administradores</h1> -->
<%-- 			<input type="hidden" id="path" value="${pageContext.request.contextPath}"/> --%>
<!-- 			<input type="text" name="busqueda" id="busqueda" class="validate[required]" /> -->
<!-- 			<button type="submit">Buscar</button> -->
<!-- 		</form>	 -->

<!-- 		<form> -->
<%-- 			<h1>${pageTitle}</h1> --%>
<%-- 			<c:if test="${result != null}"> --%>
<%-- 				<h2>${result}</h2> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${message1 != null}"> --%>
<%-- 				<h2>${message1}</h2> --%>
<%-- 			</c:if> --%>
<!-- 		</form> -->
		
		<c:set var="backslash" value="\\"/>
		
		<form>
			<h1>Membresías Coworking</h1>
		</form>
		<div class = "table1">
			<table>
				<tr >
					<td colspan=5>Membresía ${coworkingFreelancerTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${coworkingFreelancer}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<table>
				<tr >
					<td colspan=5>Membresía ${coworkingEmpresarialTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${coworkingEmpresarial}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<table>
				<tr >
					<td colspan=5>Membresía ${coworkingUniversitarioTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${coworkingUniversitario}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
		<form>
			<h1>Membresías Maker</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${makerFreelancerTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${makerFreelancer}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<table>
				<tr >
					<td colspan=5>Membresía ${makerEmpresarialTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${makerEmpresarial}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<table>
				<tr >
					<td colspan=5>Membresía ${makerOtroTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${makerOtro}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
		<form>
			<h1>Membresías Decision Theater</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${decisionTheaterTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${decisionTheater}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Espacio Dedicado</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${espacioDedicadoTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${espacioDedicado}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Training Room</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${trainingRoomTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${trainingRoom}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			
			<form>
			<h1>Membresías Show Room</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${showRoomTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${showRoom}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Sala de Juntas</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${salaDeJuntasTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${salaDeJuntas}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Black</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${membresiaBlackTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${membresiaBlack}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Platinum</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${membresiaPlatinumTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${membresiaPlatinum}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Gold</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${membresiaGoldTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${membresiaGold}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
			<form>
			<h1>Membresías Premium</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${membresiaPremiumTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${membresiaPremium}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
			
				<form>
			<h1>Servicios Adicionales</h1>
		</form>
			<table>
				<tr >
					<td colspan=5>Membresía ${printerTit}</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Membresía</td>
					<td>Servicios</td>
					<td>Costo Mes 1</td>
					<td>Costo Mes 3</td>
					<td>Costo Mes 6</td>
				</tr>
				<c:forEach var="paq" items="${printer}">
					<tr>
						<td>${paq.nombrePaq} </td>
						<c:set var="descripcion" value="${fn:replace(paq.descripcionPaq, backslash, '<br />')}" />
						<td>${descripcion}</td>
						<c:if test="${paq.costo2Paq == 0 && paq.costo3Paq == 0}">
							<td colspan="3">${paq.costo1Paq}0 MXN por hora</td>
						</c:if>
						<c:if test="${paq.costo2Paq != 0}">
							<td>${paq.costo1Paq}0 MXN</td>
							<td>${paq.costo2Paq}0 MXN</td>
							<td>${paq.costo3Paq}0 MXN</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			
			<br /><br />
		
		</div>
	</article>
					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />