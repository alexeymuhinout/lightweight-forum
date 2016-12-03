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
    <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
        <label>Category name:
            <input class="input_text" type="text" name="category_name" title="Category name">
        </label>
        <input type="hidden" name="command" value="category_create">
        <input  class="button_forum_create" type="submit" value="Create">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>