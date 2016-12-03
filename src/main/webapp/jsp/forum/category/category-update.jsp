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
        <label>Category name:
            <input type="text" name="category_name" title="Category name" value="${category.name}">
        </label>
        <label>Moderator:<br>
            <select name="user_id">
                <c:forEach items="${admins}" var="admin">
                    <option value="${admin.id}">${admin.name}</option>
                </c:forEach>
            </select>
        </label>
        <input type="hidden" name="category_id" value="${category.id}">
        <input type="hidden" name="command" value="category_update">
        <input type="submit" value="Update">
    </form>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>