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
                        <a href="${pageContext.request.contextPath}/jsp/user/login.jsp">Login</a>
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
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <label>Name:<br><input type="text" name="user_name" value="${user.name}" title="Your name"></label>
        <label>Surname:<br><input type="text" name="user_surname" value="${user.surname}" title="Your surname"></label>
        <label>Login:<br><input type="text" name="user_login" value="${user.login}" title="Your login"></label>
        <label>Mail:<br><input type="email" name="user_mail" value="${user.mail}" title="Your mail"></label>
        <label>Birthday:<br><input type="date" name="user_birthday" value="${user.birthday}"
                                   title="Your birthday date"></label>
        <label>City:<br>
            <select name="city_id">
                <c:forEach items="${cities}" var="city">
                    <option
                            <c:if test="${user.city eq city.name}">
                                selected
                            </c:if> value="${city.id}">${city.name}
                    </option>
                </c:forEach>
            </select>
        </label>
        <br>
        <label>If was not found, write city name here:<br>
            <input type="text" name="city_name" title="City name">
        </label>
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="hidden" name="command" value="user_update">
        <input type="submit" name="update" value="Update">
    </form>
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