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
    <c:if test="${not empty user}">
        <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
            <input type="hidden" name="category_id" value="${param.category_id}"/>
            <input type="hidden" name="command" value="subcategory_create_show"/>
            <input class="button_forum_create" type="submit" value="Create new subcategory"/>
        </form>
    </c:if>
    <table>
        <tr>
            <th>
                Категория
            </th>
            <th>
                Автор
            </th>
            <th>
                Дата создания
            </th>
            <c:if test="${not empty user}">
                <th>
                    Изменить категорию
                </th>
            </c:if>
        </tr>
        <c:forEach items="${subcategories}" var="userSubcategory">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=messages_show&amp;subcategory_id=${userSubcategory.subcategory.id}">${userSubcategory.subcategory.name}</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=messages_show&amp;subcategory_id=${userSubcategory.subcategory.id}">${userSubcategory.userName}</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=messages_show&amp;subcategory_id=${userSubcategory.subcategory.id}">${userSubcategory.subcategory.creationDate}</a>
                </td>
                <c:if test="${not empty user}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post" >
                            <input class="tdbutton" type="submit" value="Edit"/>
                            <input type="hidden" name="command" value="subcategory_update_show"/>
                            <input type="hidden" name="category_id" value="${userSubcategory.subcategory.categoryId}">
                            <input type="hidden" name="subcategory_id" value="${userSubcategory.subcategory.id}"/>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="post" >
                            <input class="tdbutton" type="submit" value="Delete"/>
                            <input type="hidden" name="command" value="subcategory_delete"/>
                            <input type="hidden" name="category_id" value="${userSubcategory.subcategory.categoryId}">
                            <input type="hidden" name="subcategory_id" value="${userSubcategory.subcategory.id}"/>
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