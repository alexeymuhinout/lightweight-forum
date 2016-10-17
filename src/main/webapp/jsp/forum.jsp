<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Hotel-info</title>
    <meta charset="utf-8">
    <link rel='StyleSheet' type='text/css' href='${pageContext.request.contextPath}/styles/main.css'/>
</head>
<body>
<header>
    <nav class="main-nav">
        <section class="user-block">
            <div>Lightweight-forum</div>
            <div>USER BLOCK</div>
        </section>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Main</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/forum.jsp">Forum</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/blog.jsp">Blog</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/community.jsp">Community</a></li>
            <li><a href="${pageContext.request.contextPath}/jsp/find.jsp">Find</a></li>
        </ul>
    </nav>
</header>
<main>
    MAIN
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