<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" media="all" type="text/css" href='<c:url value="/res/css/validationEngine.jquery.css" />' />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<c:import url="/res/js/dateScript.txt" />
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine-es.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/jquery.validationEngine.js" />'></script>
<script type="text/javascript" src='<c:url value="/res/js/inputShowPwd.js" />'></script>
<link rel="stylesheet" href='<c:url value = "/res/css/mainform.css" />' />

<script>
$(function() {
    //Extends JQuery Methods to get current cursor postion in input text.
    //GET CURSOR POSITION
    jQuery.fn.getCursorPosition = function() {
        if (this.lengh == 0) return -1;
        return $(this).getSelectionStart();
    }

    jQuery.fn.getSelectionStart = function() {
        if (this.lengh == 0) return -1;
        input = this[0];

        var pos = input.value.length;

        if (input.createTextRange) {
            var r = document.selection.createRange().duplicate();
            r.moveEnd('character', input.value.length);
            if (r.text == '') pos = input.value.length;
            pos = input.value.lastIndexOf(r.text);
        } else if (typeof(input.selectionStart) != "undefined") pos = input.selectionStart;

        return pos;
    }

    //Bind Key Press event with password field    
    $("#txtpwd").keypress(function(e) {
        setTimeout(function() {
            maskPassword(e)
        }, 500);
    });

    $("#txtpwd").keydown(function(e) {
        if (e.keyCode == 8) {
            setTimeout(function() {
                maskPassword(e)
            }, 1);
        }
    });

});

function generateStars(n) {
    var stars = '';
    for (var i = 0; i < n; i++) {
        stars += '●';
    }
    return stars;
}

function maskPassword(e) {

    var text = $('#txthidden').val();
    var stars = $('#txthidden').val().length;
    var unicode = e.keyCode ? e.keyCode : e.charCode;
    $("#keycode").html(unicode);

    //Get Current Cursor Position on Password Textbox
    var curPos = $("#txtpwd").getCursorPosition();
    var PwdLength = $("#txtpwd").val().length;

    if (unicode != 9 && unicode != 13 && unicode != 37 && unicode != 40 && unicode != 37 && unicode != 39) {
        //If NOT <Back Space> OR <DEL> Then...
        if (unicode != 8 && unicode != 46) {
            text = text + String.fromCharCode(unicode);
            stars += 1;
        }
        //If Press <Back Space> Or <DEL> Then...
        else if ((unicode == 8 || unicode == 46) && stars != PwdLength) {
            stars -= 1;
            text = text.substr(0, curPos) + text.substr(curPos + 1);
        }
        //Set New String on both input fields
        $('#txthidden').val(text);
        $('#txtpwd').val(generateStars(stars));
    }

}
</script>

<c:import url="/WEB-INF/views/headfoot/headerm.jsp" />
<c:import url="/WEB-INF/views/headfoot/header_form.jsp" />

	<!-- Article - Formulario -->
	<article class="featured">
	
	
	
		<form method="post" id="valid"
			action="${pageContext.request.contextPath}/adminaccount/confirm" > 
			
		<h1>Reestablecer Contraseña</h1>
		<h2>${result}</h2>
			<fieldset>
			
			<label >Captura tu nueva contraseña</label>
			<input type="text" class="validate[required]" id="txtpwd" name="passwordShow"  />
			<input type="hidden" id="txthidden" name="password" />
			</fieldset>
			
			<button type="submit">Guardar</button>
		</form>
	</article>
				
	<article class="featured"> 
		<div class = "table1">
			<table>
				<tr>
					<td>Nombre(s)</td>
					<td>Apellido Paterno</td>
					<td>Apellido Materno</td>
					<td>Correo electrónico</td>
					<td>CURP</td>
					<td>Área</td>
					<td>Cargo</td>
					<td>Teléfono</td>
					<td>Permisos</td>
				</tr>
				<c:forEach var="admin" items="${adminList}">
					<tr>
						<td>${admin.persona.nombrePer}</td>
						<td>${admin.persona.apPatPer}</td>
						<td>${admin.persona.apMatPer}</td>
						<td>${admin.persona.emailPer}</td>
						<td>${admin.persona.curpPer}</td>
						<td>${admin.areaAd}</td>
						<td>${admin.cargoAd}</td>
						<td>${admin.telefonoAd}</td>
						<td>${admin.rolAd}</td>
						<td>
							<a href="<c:url value='/admin/update/${admin.idAd}' />">Modificar</a> &nbsp;  
							<a href="<c:url value='/admin/delete/${admin.idAd}' />">Eliminar</a>
						</td>
					</tr>
				</c:forEach>
				
			</table>
		
		</div>
	</article>
		
		
		

					
<c:import url="/WEB-INF/views/headfoot/footer_form.jsp" />	
<c:import url="/WEB-INF/views/headfoot/footerm.jsp" />