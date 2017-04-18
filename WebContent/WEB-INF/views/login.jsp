<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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
<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
 
					<article class="featured">
<%-- 						<c:if test="${param.error != null}"> --%>
<!-- 							<span style="color: red;">¡Error de credenciales!</span> -->
<%-- 						</c:if> --%>
						
						<form name='f' action="j_spring_security_check" method='POST'>

							<h1>Inicio de Sesión</h1>
							<c:if test="${param.error != null}">
								<h2>¡Correo electrónico o contrseña incorrectos!</h2>
							</c:if>
							<h2>${result}</h2>

							<fieldset>
								<label for="name">Correo Electrónico:</label> 
									<input type='text' name='usuario'> 
								
								<label for="password">Contraseña:</label>
									<input type='password' name='clave'>
									
								<input type='checkbox' name='_spring_security_remember_me' checked="checked"><label class="light"
										for="business">Recordar</label>
								<br/>	
								<a href="<c:url value='/account/forgot' />">He olvidado mi contraseña</a>
							</fieldset>

							<button name="submit" type="submit" value="Aceptar">Iniciar Sesión</button>
						</form>
					</article>
	
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />