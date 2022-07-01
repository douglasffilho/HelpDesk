<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="br.com.daniel.security.domain.UserPrincipal" %>
<%@ page import="br.com.daniel.security.permissions.ViewRoles" %>

<%
    Object loggedUserAttr = session.getAttribute("principal");
    if (loggedUserAttr == null) {
        response.sendRedirect("/login");
        return;
    }

    UserPrincipal loggedUser = ((UserPrincipal) loggedUserAttr);
    Set<String> loggedUserRoles = loggedUser.listRoles();

    boolean CAN_MANAGE_USERS = loggedUserRoles.containsAll(ViewRoles.USERS_ROOT_ROLES);
    boolean CAN_MANAGE_REQUESTS = loggedUserRoles.containsAll(ViewRoles.SERVICE_DESK_ROOT_ROLES);
    boolean IS_CLIENT = loggedUserRoles.containsAll(ViewRoles.CLIENT_ROOT_ROLES);
    boolean IS_ADMIN = loggedUserRoles.containsAll(ViewRoles.ADMIN_ROOT_ROLES);
%>
