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
    <div style="width: 100%;">
        <div style="width: 25%;">
    <form action="${pageContext.request.contextPath}/controller" method="post" class="profile_page">
        <label>Name:<br>
            <input class="input_text_reg" type="text" name="user_name" title="User name">
        </label>
        <br>
        <label>Surname:<br>
            <input class="input_text_reg" type="text" name="user_surname" title="User surname">
        </label>
        <br>
        <label>Login:<br>
            <input class="input_text_reg" type="text" name="user_login" title="User login">
        </label>
        <br>
        <label>Password:<br>
            <input class="input_text_reg" type="password" name="user_password" title="User password">
        </label>
        <br>
        <label>Mail:<br>
            <input class="input_text_reg" type="email" name="user_mail" title="User mail">
        </label>
        <br>
        <label>Birthday:<br>
            <input class="input_text_reg" type="date" name="user_birthday" title="User birthday">
        </label>
        <br>
        <label>City:<br>
            <select name="city_id">
                <c:forEach items="${cities}" var="city">
                    <option value="${city.id}">${city.name}</option>
                </c:forEach>
            </select>
        </label>
        <br>
        <label>If was not found, write city name here:<br>
            <input class="input_text_reg" type="text" name="city_name" title="City name">
        </label>
        <br>
        <input class="button_reg" type="submit" name="create" value="Register">
        <input type="hidden" name="command" value="register">
    </form>
            </div>

        <div style="width: 65%;">
    <form class="profile_page">
        <h4>Here are some rules that we ask you to follow:</h4>
        <ol>
            <li>
                Keep your posts relevant to the forum category.
            </li>
            <li>
                Please be respectful of others and don’t sweat the small stuff. We’re not all English majors.
            </li>
            <li>
                Please do not post any personal information (or photos) of yourself that you wouldn’t want to disclose
                to the public at large.
            </li>
            <li>
                Do not post hateful racist, or illegal content. Do not post copyrighted material without proper
                attribution.
            </li>
            <li>
                Please do not use the forum for self-promotion, solicitation, or advertising. SPAM will not be
                tolerated.
            </li>
            <li>
                Please refrain from discussing illegal activities, sharing lewd photos or using curse words.
            </li>
            <li>
                Rudeness, personal attacks, bullying, threats or inflammatory posts will not be tolerated.
            </li>
            <li>
                Do not question moderators in the open forum. Moderation can be stressful, use private messages to chat
                with moderators.
            </li>
        </ol>
    </form>
            </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>