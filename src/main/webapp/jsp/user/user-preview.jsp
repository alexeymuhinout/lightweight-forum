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
            <p>Registered: ${user_preview.registrationDate}</p>
            <p>Name: ${user_preview.name}</p>
            <p>Surname: ${user_preview.surname}</p>
            <p>Login: ${user_preview.login}</p>
            <p>Mail: ${user_preview.mail}</p>
            <p>Birthday: ${user_preview.birthday}</p>
            <p>City: ${user_preview.city}</p>
        </section>
        <c:if test="${not empty user && user.admin != false}">
            <section class="admin-panel">
                <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                    <label><br>Write message:<br>
                        <textarea class="text_for_msg" name="user_block_reason"></textarea>
                    </label>
                    <label><br>Block until:<br>
                        <input type="date" name="user_block_until_date">
                    </label>
                    <input type="hidden" name="user_login" value="${user_preview.login}"/>
                    <input type="hidden" name="command" value="user_block"/>
                    <input class="button_forum_create" type="submit" value="Block user"/>
                </form>
                <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
                    <input type="hidden" name="user_login" value="${user_preview.login}"/>
                    <input type="hidden" name="command" value="user_unblock"/>
                    <input class="button_forum_create" type="submit" value="Unblock user"/>
                </form>
            </section>
        </c:if>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>