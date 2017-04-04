<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.text.*"%>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<link rel="stylesheet" media="all" type="text/css"
	href='<c:url value="/res/css/validationEngine.jquery.css" />' />

<script type="text/javascript"
	src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/res/js/inputShowPwd.js" />'></script>

<!-- Article - Formulario -->
<article class="featured">

	<form method="post" id="valid" action="${pageContext.request.contextPath}${customMapping}">

		<h1>${pageTitle}</h1>
		<h2>${result}</h2>
		<button type="submit">${confirmButton}</button>
	</form>
</article>






<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />