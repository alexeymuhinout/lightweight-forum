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
        <label>Edit message:<br>
            <textarea name="message_text">${userMessage.text}</textarea>
        </label>
        <input type="submit" name="update" value="Update">
        <input type="hidden" name="message_id" value="${userMessage.id}">
        <input type="hidden" name="subcategory_id" value="${userMessage.subcategoryId}">
        <input type="hidden" name="command" value="message_update">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>