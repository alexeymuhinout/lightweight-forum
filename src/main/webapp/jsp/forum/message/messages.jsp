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
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>