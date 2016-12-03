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
    <form action="${pageContext.request.contextPath}/controller" method="post" class="login_page">
        <label>Login:<br>
            <input  class="input_text" type="text" name="user_login" title="User login or mail address">
        </label>
        <br>
        <label>Password:<br>
            <input class="input_text" type="password" name="user_password" title="User password">
        </label>
        <br>
        <input class="button_login" type="submit" name="login" value="Login">
        <input type="hidden" name="command" value="user_login">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>