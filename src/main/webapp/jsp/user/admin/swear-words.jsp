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
    <ul>
        <c:forEach items="${swear_words}" var="swear_word">
            <li>
                <c:if test="${not empty user && user.admin != false}">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="text" name="swear_word_text" value="${swear_word.text}" title="Swear word">
                        <input type="hidden" name="swear_word_id" value="${swear_word.id}"/>
                        <button name="command" type="submit" value="swear_word_update">Update</button>
                        <button name="command" type="submit" value="swear_word_delete">Delete</button>
                    </form>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${not empty user && user.admin != false}">
        <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
            <label>Word:
                <input type="text" name="swear_word_text" value="">
            </label>
            <input type="hidden" name="command" value="swear_word_create"/>
            <input class="button_forum_create" type="submit" value="Add"/>
        </form>
    </c:if>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>