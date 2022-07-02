<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelpDesk - Home</title>
</head>
<body>
    <%@include file="includes/nav.jsp"%>
    <%@include file="includes/message.jsp"%>
    <div class="main-menu">
        <%
            if (CAN_MANAGE_REQUESTS) {
                %>
                    <div class="item">
                        <a href="/service-desk">Chamados</a>
                    </div>
                <%
            }
            if (IS_CLIENT) {
                %>
                    <div class="item">
                        <a href="/service-desk/requests/my">Meus Chamados</a>
                    </div>
                <%
            }
        %>
    </div>
</body>
</html>