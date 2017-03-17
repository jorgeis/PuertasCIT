<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />

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

								<h1>Registro</h1>

								<fieldset>
									<legend>
										<span class="number">1</span>Información Básica
									</legend>
									 <input type="text" id="name" placeholder="User name"
										name="user_name">  <input placeholder="Email"
										type="email" id="mail" name="user_email"> <label
										for="password">Contraseña:</label> <input type="password"
										id="password" name="user_password"> <label>Sexo:</label>
									<input type="radio" id="under_13" value="under_13"
										name="user_age"><label for="under_13" class="light">Masculino
										</label><br> <input type="radio" id="over_13" value="over_13"
										name="user_age"><label for="over_13" class="light">Femenino</label>
								</fieldset>

								<fieldset>
									<legend>
										<span class="number">2</span>Datos de Perfil
									</legend>
									<label for="bio">Motivo de la Suscripción</label>
									<textarea id="bio" name="user_bio"></textarea>
								</fieldset>
								<fieldset>
									<label for="job">Ocupación</label> <select id="job"
										name="user_job">
										<optgroup label="Categorías">
											<option value="frontend_developer">Freelancer</option>
											<option value="php_developor">Empresario</option>
											<option value="python_developer">Investigador</option>
											<option value="rails_developer">Emprendedor</option>
											<option value="web_designer">Estudiante</option>
											<option value="WordPress_developer">Servicios Profesionales</option>
										</optgroup>
									</select> <label>Intereses:</label> <input type="checkbox"
										id="development" value="interest_development"
										name="user_interest"><label class="light"
										for="development">Informática - Programación</label><br> <input
										type="checkbox" id="design" value="interest_design"
										name="user_interest"><label class="light" for="design">Metal - Mecánico</label><br>
									<input type="checkbox" id="business" value="interest_business"
										name="user_interest"><label class="light"
										for="business">Eléctrico - Electrónico</label>

								</fieldset>
								<button type="submit">Registro</button>
							</form>
					</article>
				</div>
			</div>
		</div>
	</div>
</div>


<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />