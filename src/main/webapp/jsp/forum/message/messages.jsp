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
            <div class="logo-block"><h1>Lightweight-forum</h1></div>
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
    <ul>
        <c:forEach items="${messages}" var="userMessage">
            <li class="message">
                <div class="message-sender">
                    <p class="message-sender-name">${userMessage.senderName}</p><br>
                    Registered:<p>${userMessage.senderRegistrationDate}</p>
                </div>
                <div class="message-content">
                    <div class="message-content-subcategory"><h4>${userMessage.subcategoryName}</h4></div>
                    <div class="message-content-message">
                        <p>
                            <c:if test="${not empty userMessage.receiverName}">
                                ${userMessage.receiverName},
                            </c:if>
                                ${userMessage.message.text}
                        </p>
                    </div>
                    <div class="message-actions">
                        <c:if test="${not empty user}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="message_create_show"/>
                                <input type="hidden" name="subcategory_id"
                                       value="${userMessage.message.subcategoryId}"/>
                                <input type="hidden" name="reply_to_user_id"
                                       value="${userMessage.message.userId}"/>
                                <input type="submit" value="Reply"/>
                            </form>
                        </c:if>
                        <c:if test="${not empty user && (userMessage.message.userId eq user.id || user.admin != false)}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="message_delete"/>
                                <input type="hidden" name="subcategory_id"
                                       value="${userMessage.message.subcategoryId}"/>
                                <input type="hidden" name="message_id" value="${userMessage.message.id}"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </c:if>
                        <c:if test="${not empty user && (userMessage.message.userId eq user.id || user.admin != false)}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="message_update_show"/>
                                <input type="hidden" name="subcategory_id"
                                       value="${userMessage.message.subcategoryId}"/>
                                <input type="hidden" name="message_id" value="${userMessage.message.id}"/>
                                <input type="submit" value="Update"/>
                            </form>
                        </c:if>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${not empty user}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="message_create_show"/>
            <input type="hidden" name="subcategory_id" value="${param.subcategory_id}"/>
            <input type="submit" value="Create new message"/>
        </form>
    </c:if>
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