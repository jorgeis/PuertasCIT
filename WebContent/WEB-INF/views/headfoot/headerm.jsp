<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>


		

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
													<li><a href='<c:url value="/gratuitoform"/>'>Gratuito</a></li>
													<li><a href="#">Individual</a></li>
													<li><a href="#">Empresarial</a></li>
													<li><a href='<c:url value="/twocolumn1"/>'>Instalaciones</a></li>
												</ul>
											</li>
											
											<!-- Menú disponible si estas autenticado como Cliente -->
											<sec:authorize access="hasRole('ROLE_CLIENT')">
												<li>
												<a href="" class="arrow">Cliente</a>
												<ul>
													<li>
														<span class="arrow">Administrar</span>
														<ul>
															<li><a href='<c:url value="/cliente/update/"/>'>Cambiar datos personales</a></li>
															<li><a href='<c:url value="/cliente/deletesc/"/>'>Borrar cuenta</a></li>
														</ul>
													</li>
													<li>
														<span class="arrow">Organización</span>
														<ul>
															<li><a href='<c:url value="/org/queryown/1"/>'>Consultar</a></li>
															<li><a href='<c:url value="/orgform"/>'>Agregar</a></li>
														</ul>
													</li>
												</ul>
											</li>
											</sec:authorize>
											
											
											<!-- Menú disponible si estas autenticado como Cliente -->
<%-- 											<sec:authorize access="hasRole('ROLE_ADMIN')"> --%>
											<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ROOT')">
												<li>
												<a href="" class="arrow">Administrador</a>
												<ul>
													<li>
														<span class="arrow">Notificaciones</span>
														<ul>
															<li><a href='<c:url value="/admin/notificacion/form"/>'>Crear notificación</a></li>
															<li><a href='<c:url value="/admin/notificacion/queryall/1"/>'>Consultar notificaciones</a></li>
														</ul>
													</li>
													<li>
														<span class="arrow">Administradores</span>
														<ul>
															<li><a href='<c:url value="/admin/form"/>'>Crear nuevo</a></li>
															<li><a href='<c:url value="/admin/searchform"/>'>Buscar activos</a></li>
															<li><a href='<c:url value="/admin/queryall/1"/>'>Consultar activos</a></li>
															<li><a href='<c:url value="/admin/querypending/1"/>'>Consultar pendientes</a></li>
															<li><a href='<c:url value="/admin/querydeleted/1"/>'>Consultar borrados</a></li>
														</ul>
													</li>
													<li>
														<span class="arrow">Clientes</span>
														<ul>
															<li><a href='<c:url value="/cliente/searchform"/>'>Buscar activos</a></li>
															<li><a href='<c:url value="/cliente/queryall/1"/>'>Consultar activos</a></li>
															<li><a href='<c:url value="/cliente/querypending/1"/>'>Consultar pendientes</a></li>
															<li><a href='<c:url value="/cliente/querydeleted/1"/>'>Consultar borrados</a></li>
														</ul>
													</li>
													<li>
														<span class="arrow">Organizaciones</span>
														<ul>
															<li><a href='<c:url value="/org/searchform"/>'>Búsqueda por nombre</a></li>
															<li><a href='<c:url value="/org/queryall/1"/>'>Consultar todas</a></li>
															<li><a href='<c:url value="/orgform"/>'>Agregar nueva</a></li>
														</ul>
													</li>
													<li>
														<span class="arrow">Reservaciones gratuitas</span>
														<ul>
															<li><a href='<c:url value="/gra/querypending/1"/>'>Consultar pendientes</a></li>
															<li><a href='<c:url value="/gra/queryall/1"/>'>Consultar todas</a></li>
															<li><a href='<c:url value="/gra/queryaccepted/1"/>'>Consultar aceptadas</a></li>
															<li><a href='<c:url value="/gra/queryrejected/1"/>'>Consultar rechazadas</a></li>
															<li><a href='<c:url value="/gra/queryweek/1"/>'>Consultar semana</a></li>
														</ul>
													</li>
												</ul>
											</li>
											</sec:authorize>
											
											<!-- Mostrar nombre de usuario si inició sesión automáticamente -->
											<sec:authorize access="isRememberMe()">
												<sec:authentication var="principal" property="principal" />
												<c:set var="username" value="${principal.username}" />
												<li>
													<sec:authorize access="hasRole('ROLE_CLIENT')">
														CLIENTE:
													</sec:authorize>
													<sec:authorize access="hasAnyRole('ROLE_ROOT','ROLE_ADMIN')">
														ADMIN:
													</sec:authorize>
													${principal.username}
												</li>
											</sec:authorize>
											
											<!-- Mostrar nombre de usuario y privilegios si acaba inició sesión -->
											<sec:authorize access="isFullyAuthenticated()">
												<sec:authentication var="principal" property="principal" />
												<c:set var="username" value="${principal}" />
												<li>
													<sec:authorize access="hasRole('ROLE_CLIENT')">
														CLIENTE:
													</sec:authorize>
													<sec:authorize access="hasAnyRole('ROLE_ROOT','ROLE_ADMIN')">
														ADMIN:
													</sec:authorize>
													${principal}
												</li>
											</sec:authorize>
											
											<!-- Mostrar link de registro -->
											<sec:authorize access="!isAuthenticated()">
												<li><a href='<c:url value="/clienteform" />'>Regístrate</a> </li>
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