<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/inputShowPwd.js" />'></script>
<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />

<!-- Article - Formulario -->
<article class="featured">

	<form method="post" id="valid" action="${pageContext.request.contextPath}${customMapping}">

		<h1>${pageTitle}</h1>
		<h2>${result}</h2>
		<h2>${message1}</h2>
		<br />
		<input type="hidden" name="param1" value="${param1}" />
		<input type="hidden" name="param2" value="${param2}" />
		<button type="submit">${confirmButton}</button>
	</form>
</article>

<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />