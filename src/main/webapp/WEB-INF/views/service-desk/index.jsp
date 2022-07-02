<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.com.daniel.model.decorator.ServiceRequestWithUsersData" %>
<%@ page import="java.util.List" %>

<%
    List<ServiceRequestWithUsersData> requests = (List<ServiceRequestWithUsersData>) request.getAttribute("requests");
    int thisPage = (int) request.getAttribute("thisPage");
    int thisSize = (int) request.getAttribute("thisSize");
    int nextPage = (int) request.getAttribute("nextPage");
    int previousPage = (int) request.getAttribute("previousPage");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta id="viewport" name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, viewport-fit=cover">
<title>HelpDesk - Chamados</title>
</head>
<body>
    <%@include file="../includes/common_styles.jsp"%>
    <%@include file="../includes/nav.jsp"%>
    <%@include file="../includes/message.jsp"%>
	<%String serviceDeskHeaderTitle="Meus Chamados";%>
    <%@include file="header.jsp"%>

    <div>
        <table cellspacing="0">
            <tr>
                <th>Aberto Para</th>
                <th>Descrição</th>
                <th>Status</th>
                <th>Analista</th>
            </tr>
            <%
                for (int i = 0; i < requests.size(); i++) {
                    ServiceRequestWithUsersData thisRequest = requests.get(i);
                    %>
                        <tr>
                            <td><%=thisRequest.getCreatedByAsString()%></td>
                            <td><%=thisRequest.getShortDescription()%></td>
                            <td><%=thisRequest.getStatus().getTranslation()%></td>
                            <%
                                if (thisRequest.gotToAnalysis()) {
                                    %>
                                        <td><%=thisRequest.getAnalyzedByAsString()%></td>
                                    <%
                                } else {
                                    %>
                                        <td></td>
                                    <%
                                }
                            %>
                        </tr>
                    <%
                }
            %>
        </table>

        <div class="page-controls">
            <a href="/requests?page=<%=previousPage%>&size=<%=thisSize%>">&lt;</a>
            <a href="/requests?page=<%=thisPage%>&size=<%=thisSize%>"><%=thisPage%></a>
            <a href="/requests?page=<%=nextPage%>&size=<%=thisSize%>">&gt;</a>
        </div>
    </div>
</body>
</html>