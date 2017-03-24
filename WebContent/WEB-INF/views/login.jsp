<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />

 
					<article class="featured">
						<c:if test="${param.error != null}">
							<span style="color: red;">¡Error de credenciales!</span>
						</c:if>
						
						<form name='f' action="j_spring_security_check" method='POST'>

							<h1>Inicio de Sesión</h1>
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