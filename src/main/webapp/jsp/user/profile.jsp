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
    <section>
        <p>Registered: ${user.registrationDate}</p>
        <p>Name: ${user.name}</p>
        <p>Surname: ${user.surname}</p>
        <p>Login: ${user.login}</p>
        <p>Mail: ${user.mail}</p>
        <p>Birthday: ${user.birthday}</p>
        <p>City: ${user.city}</p>
        <c:if test="${not empty user}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="hidden" name="command" value="user_delete"/>
                <input type="submit" value="Delete"/>
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="hidden" name="command" value="user_update_show"/>
                <input type="submit" value="Edit"/>
            </form>
        </c:if>
    </section>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>