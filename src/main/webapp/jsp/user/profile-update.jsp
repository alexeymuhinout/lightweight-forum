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
        <label>Name:<br><input type="text" name="user_name" value="${user.name}" title="Your name"></label>
        <label>Surname:<br><input type="text" name="user_surname" value="${user.surname}" title="Your surname"></label>
        <label>Login:<br><input type="text" name="user_login" value="${user.login}" title="Your login"></label>
        <label>Mail:<br><input type="email" name="user_mail" value="${user.mail}" title="Your mail"></label>
        <label>Birthday:<br><input type="date" name="user_birthday" value="${user.birthday}"
                                   title="Your birthday date"></label>
        <label>City:<br>
            <select name="city_id">
                <c:forEach items="${cities}" var="city">
                    <option
                            <c:if test="${user.city eq city.name}">
                                selected
                            </c:if> value="${city.id}">${city.name}
                    </option>
                </c:forEach>
            </select>
        </label>
        <br>
        <label>If was not found, write city name here:<br>
            <input type="text" name="city_name" title="City name">
        </label>
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="hidden" name="command" value="user_update">
        <input type="submit" name="update" value="Update">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>