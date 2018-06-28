<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a href="meals" class="navbar-brand"><img src="resources/images/icon-meal.png"> <spring:message code="app.title"/></a>
        <form:form class="form-inline my-2" action="logout" method="post">
            <sec:authorize access="isAuthenticated()">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <a class="btn btn-info mr-1" href="users"><spring:message code="user.title"/></a>
                </sec:authorize>
                <a class="btn btn-info mr-1" href="profile">${userTo.name} <spring:message code="app.profile"/></a>
                <button class="btn btn-primary" type="submit">
                    <span class="fa fa-sign-out"></span>
                </button>
            </sec:authorize>
        </form:form>
        <%--<li class="nav-item dropdown">--%>
            <%--<a class="dropdown-toggle nav-link my-1 ml-2" data-toggle="dropdown" aria-expanded="false">en</a>--%>
            <%--<div class="dropdown-menu">--%>
                <%--<a class="dropdown-item" href="/login?lang=en">English</a>--%>
                <%--<a class="dropdown-item" href="/login?lang=ru">Русский</a>--%>
            <%--</div>--%>
        <%--</li>--%>
    </div>
</nav>