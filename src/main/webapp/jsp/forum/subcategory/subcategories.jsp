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
<header>
    <nav class="main-nav">
        <section class="user-block">
            <div class="logo-block">Lightweight-forum</div>
            <div class="login-block">
                <c:choose>
                    <c:when test="${empty user}">
                        <a href="${pageContext.request.contextPath}/jsp/login.jsp">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/jsp/home.jsp">Profile</a>
                        <a href="${pageContext.request.contextPath}/controller?command=logout">Logout[${user.name}]</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/controller?command=registration_show">Registration</a>
            </div>
        </section>
        <ul class="main-ul">
            <li><a href="${pageContext.request.contextPath}/controller?command=index_show">Main</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=categories_show">Forum</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=blog_sho">Blog</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=community_show">Community</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=find_show">Find</a></li>
        </ul>
    </nav>
</header>
<main>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>
    <c:if test="${not empty user}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="category_id" value="${param.category_id}"/>
            <input type="hidden" name="command" value="subcategory_create_show"/>
            <input type="submit" value="Create new subcategory"/>
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
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="submit" value="Edit"/>
                            <input type="hidden" name="command" value="subcategory_update_show"/>
                            <input type="hidden" name="category_id" value="${userSubcategory.subcategory.categoryId}">
                            <input type="hidden" name="subcategory_id" value="${userSubcategory.subcategory.id}"/>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="submit" value="Delete"/>
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
<footer>
    <div>Contact info: <br>
        +38(095)3132563
    </div>
    <div>
        <button class="social-button">VK</button>
        <button class="social-button">TWITTER</button>
        <button class="social-button">FB</button>
    </div>
    <div>
        Programmed by <br>
        Muhin Alexey Nikolaevich
    </div>
</footer>
</body>
</html>