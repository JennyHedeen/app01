<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language!=null ? sessionScope.language : pageContext.request.locale}"/>
<fmt:setBundle basename="messages.app"/>
<html>
    <jsp:include page="fragments/headTag.jsp"/>
    <body>
    <jsp:include page="fragments/navMenu.jsp"/>
        <br>
        <div id="masterName">
            <div><fmt:message key="app.master"/>: ${activeMaster.name}</div>
        </div>
        <div id="shownDay">
            <div><a href="schedule?date=${date.minusDays(1)}">< < <</a></div>
            <div>${apps.get(0).getDate()}</div>
            <div><a href="schedule?date=${date.plusDays(1)}">> > ></a></div>
        </div>

        <table>
            <thead>
            <tr>
                <th><fmt:message key="sch.client"/></th>
                <th><fmt:message key="sch.time"/></th>
                <th></th>
                <th></th>
                <th><fmt:message key="sch.feedback"/></th>
            </tr>
            </thead>
            <c:forEach items="${apps}" var="app">
            <%--    <jsp:useBean id="app" type="com.hedeen.john.app01.model.Appointment"/>
                <tr>
                    <c:if test="${app.client.id != null}">
                        <c:if test="${app.client.id == sessionScope.authUser.id || app.masterId == sessionScope.authUser.id}">
                            <td>${app.client.name}</td>
                            <td>${app.seance.toString()}</td>
                            <td><fmt:message key="sch.accept"/></td>
                            <td><a href="apply?action=cancel&id=${app.id}"><fmt:message key="sch.cancel"/></a></td>
                            <c:if test="${app.client.id == sessionScope.authUser.id && app.feedback.status.toString() == 'REQUESTED'}">
                                <td><a href="apply?action=write&id=${app.id}"><fmt:message key="sch.write"/></a></td>
                            </c:if>
                            <c:if test="${app.client.id != sessionScope.authUser.id || app.feedback.status.toString() != 'REQUESTED'}">
                                <c:if test="${sessionScope.authUser.admin}">
                                    <c:if test="${app.feedback == null}">
                                        <td><a href="apply?action=request&id=${app.id}"><fmt:message key="sch.request"/></a></td>
                                    </c:if>
                                    <c:if test="${app.feedback != null}">
                                        <c:if test="${app.feedback.status.toString() == 'REQUESTED'}">
                                            <td class="grey"><fmt:message key="sch.requested"/></td>
                                        </c:if>
                                        <c:if test="${app.feedback.status.toString() == 'RECEIVED'}">
                                            <td class="green">${app.feedback.description}</td>
                                        </c:if>
                                    </c:if>
                                </c:if>
                                <c:if test="${!sessionScope.authUser.admin}">
                                    <td> </td>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${app.client.id != sessionScope.authUser.id && app.masterId != sessionScope.authUser.id}">
                            <td><fmt:message key="sch.reserved"/></td>
                            <td>${app.seance.toString()}</td>
                            <td><fmt:message key="sch.accept"/></td>
                            <td><fmt:message key="sch.cancel"/></td>
                            <c:if test="${sessionScope.authUser.admin}">
                                <c:if test="${app.feedback == null}">
                                    <td><a href="apply?action=request&id=${app.id}"><fmt:message key="sch.request"/></a></td>
                                </c:if>
                                <c:if test="${app.feedback != null}">
                                    <c:if test="${app.feedback.status.toString() == 'REQUESTED'}">
                                        <td class="grey"><fmt:message key="sch.requested"/></td>
                                    </c:if>
                                    <c:if test="${app.feedback.status.toString() == 'RECEIVED'}">
                                        <td class="green">${app.feedback.description}</td>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <c:if test="${!sessionScope.authUser.admin}">
                                <td> </td>
                            </c:if>
                        </c:if>
                    </c:if>
                    <c:if test="${app.client.id == null}">
                        <td> </td>
                        <td>${app.seance.toString()}</td>
                        <c:if test="${sessionScope.authUser != null}">
                            <td><a href="apply?action=accept&date=${app.date}&seance=${app.seance.id}"><fmt:message key="sch.accept"/></a></td>
                        </c:if>
                        <c:if test="${sessionScope.authUser == null}">
                            <td><fmt:message key="sch.accept"/></td>
                        </c:if>
                        <td><fmt:message key="sch.cancel"/></td>
                        <td> </td>
                    </c:if>  --%>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
