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
        <label>Subcategory name:
            <input class="input_text" type="text" name="subcategory_name" title="Subcategory name">
        </label>
        <input class="button_forum_create" type="submit" name="create" value="Create">
        <input type="hidden" name="category_id" value="${param.category_id}">
        <input type="hidden" name="command" value="subcategory_create">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>