<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta id="viewport" name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, viewport-fit=cover">
<title>HelpDesk - Login</title>
<script type="text/javascript" src="resources/scripts.js" ></script>
<script>
    var submit = function() {
        var senhaElement = document.getElementById("senha");
        senhaElement.value = btoa(senhaElement.value);

        document.getElementById("form").submit();
    }
</script>
</head>
<body>
    <%@include file="includes/message.jsp"%>
	<div>
	    <form id="form" method="POST" action="/login">
	        <input type="email" placeholder="E-mail" id="email" name="email" />
	        <input type="password" placeholder="Senha" id="senha" name="senha" />
	    </form>
	    <button type="button" onclick="submit()">Entrar</button>
	</div>
</body>
</html>