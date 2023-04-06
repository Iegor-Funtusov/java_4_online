<%--
  Created by IntelliJ IDEA.
  User: egorfuntusov
  Date: 06.04.2023
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User new</title>
</head>
<body>


    <form:form
            action="${pageContext.request.contextPath}/users/new"
            method="post"
            modelAttribute="user">
        First name: <input type="text" name="firstName" id="firstName" >
        Last name: <input type="text" name="lastName" id="lastName" >
        <input type="submit" value="Create">
    </form:form>

</body>
</html>
