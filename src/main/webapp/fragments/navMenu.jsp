<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language!=null ? sessionScope.language : pageContext.request.locale}" />
<fmt:setBundle basename="messages.app"/>

<header>
    <div class="navbar">
        <div><a href="login"><fmt:message key="app.login"/></a></div>
        <div><a href="registration"><fmt:message key="app.register"/></a></div>
        <div class="dropdown">
            <div><a href="#"><fmt:message key="app.schedule"/></a></div>
            <div class="dropdown-content">
                <c:forEach items="${sessionScope.masters}" var="master">
                    <jsp:useBean id="master" type="com.hedeen.john.app01.model.User"/>
                    <a href="schedule?mid=${master.id}">${master.name}</a>
                </c:forEach>
            </div>
        </div>
        <c:if test="${sessionScope.authUser != null}">
        <div><a href="schedule?action=my"><fmt:message key="app.my"/></a></div>
            <c:if test="${sessionScope.authUser.admin}">
                <div><a href="users"><fmt:message key="app.manage"/></a></div>
            </c:if>
        </c:if>
        <div class="dropdown">
            <div><a href="#"><fmt:message key="app.lang"/></a></div>
            <div class="dropdown-content lang">
                <a href="schedule?action=lang&language=en">EN</a>
                <a href="schedule?action=lang&language=ru">RU</a>
            </div>
        </div>
        <div id="authorized">
            <c:if test="${sessionScope.authUser != null}">
                <span class="fourteen"><fmt:message key="app.logged"/></span><br>${sessionScope.authUser.name}
            </c:if>
            <c:if test="${sessionScope.authUser == null}">
            <span class="fourteen"><fmt:message key="app.notlogged"/></span>
            </c:if>
        </div>
    </div>
</header>