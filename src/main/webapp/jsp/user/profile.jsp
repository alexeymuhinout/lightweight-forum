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
    <section class="info">
        <p>Registered: ${user.registrationDate}</p>
        <p>Name: ${user.name}</p>
        <p>Surname: ${user.surname}</p>
        <p>Login: ${user.login}</p>
        <p>Mail: ${user.mail}</p>
        <p>Birthday: ${user.birthday}</p>
        <p>City: ${user.city}</p>
        <c:if test="${not empty user}">
            <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="hidden" name="command" value="user_delete"/>
                <input  class="button_del" type="submit" value="Delete"/>
            </form>
            <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                <input type="hidden" name="user_id" value="${user.id}"/>
                <input type="hidden" name="command" value="user_update_show"/>
                <input class="button_reg" type="submit" value="Edit"/>
            </form>
        </c:if>
    </section>
    <c:if test="${not empty user && user.admin != false}">
        <section class="admin-panel">
            <form action="${pageContext.request.contextPath}/controller" method="post" class = "profile_page">
                <input type="hidden" name="command" value="swearwords_show"/>
                <input class="button_forum_create" type="submit" value="Configure swearwords"/>
            </form>
        </section>
    </c:if>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>