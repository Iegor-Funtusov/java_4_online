<%--
  Created by IntelliJ IDEA.
  User: egorfuntusov
  Date: 06.04.2023
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users all</title>
</head>
<body>

    <table>
        <thead>
        <tr>
            <th>first name</th>
            <th>last name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div style="margin-top: 10px">
        <a href="${pageContext.request.contextPath}/users/new" role="button">User new</a>
    </div>

</body>
</html>
