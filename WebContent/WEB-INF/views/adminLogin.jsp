<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<link href='http://fonts.googleapis.com/css?family=Nunito:400,300'
	rel='stylesheet' type='text/css'>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />

<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />




<!-- Main -->
<div id="main-wrapper">
	<div class="5grid-layout">
		<div class="row">
			<div class="12u mobileUI-main-content">

				<!-- Content -->
				<div id="content">

					<!-- Article - Formulario -->
					<article class="featured">
						<form action="index.html" method="post">

							<h1>Administrador</h1>
							<h2>Inicio de Sesión</h2>

							<fieldset>
								<label for="name">Correo Electrónico:</label> <input type="text"
									id="email" name="user_name"> <label for="password">Contraseña:</label>
								<input type="password" id="password" name="user_password">
							</fieldset>

							<button type="submit">Iniciar Sesión</button>
						</form>
					</article>

					<article class="featured">
						<c:if test="${param.error != null}">
							<span style="color: red;">¡Error de credenciales!</span>
						</c:if>
						
						<form name='f' action="j_spring_security_check" method='POST'>

							<h1>Inicio de Sesión</h1>

							<fieldset>
								<label for="name">Correo Electrónico:</label> 
									<input type='text' name='usuario'> 
								
								<label for="password">Contraseña:</label>
									<input type='password' name='clave'>
									
								<input type='checkbox' name='_spring_security_remember_me' checked="checked"><label class="light"
										for="business">Recordar</label>
							</fieldset>

							<button name="submit" type="submit" value="Aceptar">Iniciar Sesión</button>
						</form>




						



					</article>



				</div>
			</div>
		</div>
	</div>
</div>



<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />