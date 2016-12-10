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
    <div class="profile_div">
        <section class="info">
            <p>Registered: ${user.registrationDate}</p>
            <p>Name: ${user.name}</p>
            <p>Surname: ${user.surname}</p>
            <p>Login: ${user.login}</p>
            <p>Mail: ${user.mail}</p>
            <p>Birthday: ${user.birthday}</p>
            <p>City: ${user.city}</p>
            <c:if test="${not empty user}">
            <div style="width: 100%;">
                <div class="forum_div_button_one">
                    <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                        <input type="hidden" name="user_id" value="${user.id}"/>
                        <input type="hidden" name="command" value="user_delete"/>
                        <input class="button_login" type="submit" value="Delete"/>
                    </form>
                </div>
                <div class="forum_div_button_two">
                    <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                        <input type="hidden" name="user_id" value="${user.id}"/>
                        <input type="hidden" name="command" value="user_update_show"/>
                        <input class="button_green" type="submit" value="Edit"/>
                    </form>
                </div>
                </c:if>
        </section>
    </div>
    <c:if test="${not empty user && user.admin != false}">
        <section class="admin-panel">
            <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                <input type="hidden" name="command" value="swear_words_show"/>
                <input class="button_forum_create" type="submit" value="Configure swearwords"/>
            </form>
        </section>
    </c:if>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>