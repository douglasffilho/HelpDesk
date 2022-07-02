<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.daniel.model.decorator.ServiceRequestWithUsersDataAndComments" %>
<%@ page import="br.com.daniel.domain.ServiceRequestComment" %>
<%@ page import="java.util.List" %>

<%
    ServiceRequestWithUsersDataAndComments thisRequest = (ServiceRequestWithUsersDataAndComments) request.getAttribute("request");
    List<ServiceRequestComment> thisComments = thisRequest.getComments();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta id="viewport" name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, viewport-fit=cover">
<title>HelpDesk - Chamado <%=thisRequest.getReadableId()%></title>
</head>
<body>
    <%@include file="../includes/nav.jsp"%>
    <%@include file="../includes/message.jsp"%>
	<%String serviceDeskHeaderTitle="Chamado " + thisRequest.getReadableId();%>
    <%@include file="header.jsp"%>

    <div class="request">
        <div class="data">
            <p><b>Descrição:</b> <%=thisRequest.getDescription()%></p>
            <p><b>Status:</b> <%=thisRequest.getStatus().getTranslation()%></p>
            <p><b>Aberto Por:</b> <%=thisRequest.getCreatedByAsString()%></p>
            <p><b>Aberto Em:</b> <%=thisRequest.getCreatedAtAsString()%></p>
            <%
                if (thisRequest.gotToAnalysis()) {
                    %>
                        <p><b>Em análise por:</b> <%=thisRequest.getAnalyzedByAsString()%></p>
                    <%
                }
            %>
        </div>
    </div>
    <style>
        .requests-create fieldset {
            border: none;
            display: flex;
            flex-direction: column;
        }
        .requests-create textarea {
            min-width: 100%;
            width: 100%;
            max-width: 50rem;
            max-height: 20.5rem;
            min-height: 20.5rem;
        }
    </style>
</body>
</html>