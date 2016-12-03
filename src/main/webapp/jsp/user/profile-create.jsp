<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>lightweight-forum</title>
    <meta charset="utf-8">
    <link rel='StyleSheet' type='text/css' href='${pageContext.request.contextPath}/styles/main.css'/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/jsp/header.jsp"/>
<main>
    <jsp:include page="${pageContext.request.contextPath}/jsp/message.jsp"/>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <label>Name:<br>
            <input type="text" name="user_name" title="User name">
        </label>
        <br>
        <label>Surname:<br>
            <input type="text" name="user_surname" title="User surname">
        </label>
        <br>
        <label>Login:<br>
            <input type="text" name="user_login" title="User login">
        </label>
        <br>
        <label>Password:<br>
            <input type="password" name="user_password" title="User password">
        </label>
        <br>
        <label>Mail:<br>
            <input type="email" name="user_mail" title="User mail">
        </label>
        <br>
        <label>Birthday:<br>
            <input type="date" name="user_birthday" title="User birthday">
        </label>
        <br>
        <label>City:<br>
            <select name="city_id">
                <c:forEach items="${cities}" var="city">
                    <option value="${city.id}">${city.name}</option>
                </c:forEach>
            </select>
        </label>
        <br>
        <label>If was not found, write city name here:<br>
            <input type="text" name="city_name" title="City name">
        </label>
        <br>
        <input type="submit" name="create" value="Register">
        <input type="hidden" name="command" value="register">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>