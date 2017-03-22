<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<link href='http://fonts.googleapis.com/css?family=Nunito:400,300'	rel='stylesheet' type='text/css'>
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,300italic,400,400italic" rel="stylesheet" type="text/css" />	
		<script src='<c:url value = "/res/js/jquery-1.8.1.min.js" />'></script>
		<script src='<c:url value = "/res/css/5grid/init.js?use=mobile,desktop,1000px&amp;mobileUI=1&amp;mobileUI.theme=none&amp;mobileUI.titleBarHeight=40&amp;mobileUI.openerWidth=60" />'></script>
		<script src='<c:url value = "/res/js/jquery.dropotron-1.1.js" />'></script>
		<script src='<c:url value = "/res/js/jquery.slidertron-1.2.js" />'></script>
		<script src='<c:url value = "/res/js/init.js" />'></script>
		<noscript>
			<link rel="stylesheet" href='<c:url value = "/res/css/5grid/core.css" />' />
			<link rel="stylesheet" href='<c:url value = "/res/css/5grid/core-desktop.css" />' />
			<link rel="stylesheet" href='<c:url value = "/res/css/5grid/core-1200px.css" />' />
			<link rel="stylesheet" href='<c:url value = "/res/css/5grid/core-noscript.css" />' />
			<link rel="stylesheet" href='<c:url value = "/res/css/style.css" />' />
			<link rel="stylesheet" href='<c:url value = "/res/css/style-desktop.css" />' />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie8.css" /><![endif]-->
		<!--[if lte IE 7]><link rel="stylesheet" href="css/ie7.css" /><![endif]-->
		
			
	</head>
	<body class="homepage">

		<!-- Header Wrapper -->
			<div id="header-wrapper">
			
				<!-- Header -->
					<div class="5grid-layout">
						<div class="row">
							<div class="12u">
								<header id="page-header">
									<h1><a href='<c:url value="/"/>' class="mobileUI-site-name"><span></span></a></h1>
									<nav class="mobileUI-site-nav">
										<ul>
											<li class="current_page_item"><a href='<c:url value="/"/>'>Inicio</a></li>
									<!-- 	<li>
												<a href="" class="arrow">Planes y Servicios</a>
												<ul>
													<li><a href="#">Individual</a></li>
													<li><a href="#">Empresarial</a></li>
													<li><a href="#">Etiam dolore nisl</a></li>
													<li>
														<span class="arrow">Phasellus consequat</span>
														<ul>
															<li><a href="#">Lorem ipsum dolor</a></li>
															<li><a href="#">Phasellus consequat</a></li>
															<li><a href="#">Magna phasellus</a></li>
															<li><a href="#">Etiam dolore nisl</a></li>
															<li><a href="#">Veroeros feugiat</a></li>
														</ul>
													</li>
													<li><a href="#">Veroeros feugiat</a></li>
												</ul>
											</li>  -->
									  		<li>
												<a href="" class="arrow">Planes y Servicios</a>
												<ul>
													<li><a href="#">Gratuito</a></li>
													<li><a href="#">Individual</a></li>
													<li><a href="#">Empresarial</a></li>
												</ul>
											</li>
											<li><a href='<c:url value="/twocolumn1"/>'>Instalaciones</a></li>
											<li><a href='<c:url value="/admin/persona/save"/>'>Regístrate</a></li>
											
											<li><a href='<c:url value="/admin/notificacion/queryall/1"/>'>Notificaciones</a></li>
											
											<!-- Mostrar nombre de usuario si inició sesión automáticamente -->
											<sec:authorize access="isRememberMe()">
												<sec:authentication var="principal" property="principal" />
												<c:set var="username" value="${principal.username}" />
												<li> ${principal.username} </li>
											</sec:authorize>
											
											<!-- Mostrar nombre de usuario si acaba inició sesión -->
											<sec:authorize access="isFullyAuthenticated()">
												<sec:authentication var="principal" property="principal" />
												<c:set var="username" value="${principal}" />
												<li> ${principal} </li>
											</sec:authorize>
											
											<!-- Mostrar link de login -->
											<sec:authorize access="!isAuthenticated()">
												<li><a href='<c:url value="/login" />'>Iniciar Sesión</a> </li>
											</sec:authorize>
											
											<!-- Mostrar link de logout -->
											<sec:authorize access="isAuthenticated()">
												<li><a href='<c:url value="/logout" />'>Cerrar Sesión</a> </li>
											</sec:authorize>
											
										</ul>
									</nav>
									<br />
								</header>
							</div>
						</div>
					</div>
		
			</div>