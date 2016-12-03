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
    <c:if test="${not empty user && user.admin != false}">
        <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
            <input type="hidden" name="command" value="category_create_show"/>
            <input class="button_forum_create" type="submit" value="Create new category"/>
        </form>
    </c:if>
    <table>
        <tr>
            <th>
                Тема
            </th>
            <th>
                Модератор
            </th>
            <th>
                Дата создания
            </th>
            <c:if test="${not empty user && user.admin != false}">
                <th>
                    Изменить категорию
                </th>
            </c:if>
        </tr>
        <c:forEach items="${categories}" var="category">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=subcategories_show&amp;category_id=${category.category.id}">${category.category.name}</a><br>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=subcategories_show&amp;category_id=${category.category.id}">${category.moderator}</a><br>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=subcategories_show&amp;category_id=${category.category.id}">${category.category.creationDate}</a><br>
                </td>
                <c:if test="${not empty user && user.admin != false}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post" >
                            <input class="tdbutton" type="submit" value="Edit"/>
                            <input type="hidden" name="command" value="category_update_show"/>
                            <input type="hidden" name="category_id" value="${category.category.id}"/>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input class="tdbutton" type="submit" value="Delete"/>
                            <input type="hidden" name="command" value="category_delete"/>
                            <input type="hidden" name="category_id" value="${category.category.id}"/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>