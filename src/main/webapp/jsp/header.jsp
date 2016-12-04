<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<header>
    <nav class="main-nav">
        <section class="user-block">
            <div class="logo-block">Lightweight-forum</div>
            <div class="login-block">
                <c:choose>
                    <c:when test="${empty user}">
                        <a href="${pageContext.request.contextPath}/controller?command=user_login_show">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/controller?command=user_show">Profile</a>
                        <a href="${pageContext.request.contextPath}/controller?command=user_logout">Logout[${user.name}]</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/controller?command=registration_show">Registration</a>
            </div>
        </section>
        <ul class="main-ul">
            <li><a class="href" href="${pageContext.request.contextPath}/controller?command=index_show">Main</a></li>
            <li><a class="href" href="${pageContext.request.contextPath}/controller?command=categories_show">Forum</a></li>
            <li><a class="href" href="${pageContext.request.contextPath}/controller?command=blog_show">Blog</a></li>
            <li><a class="href" href="${pageContext.request.contextPath}/controller?command=community_show">Community</a></li>
            <li><a class="href" href="${pageContext.request.contextPath}/controller?command=find_show">Find</a></li>
        </ul>
    </nav>
</header>