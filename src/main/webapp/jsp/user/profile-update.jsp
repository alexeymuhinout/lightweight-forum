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
                <label>Name:<br><input class="input_text_reg" type="text" name="user_name" value="${user.name}"
                                       title="Your name"></label>
                <label>Surname:<br><input class="input_text_reg" type="text" name="user_surname" value="${user.surname}"
                                          title="Your surname"></label>
                <label>Login:<br><input class="input_text_reg" type="text" name="user_login" value="${user.login}"
                                        title="Your login"></label>
                <label>Mail:<br><input class="input_text_reg" type="email" name="user_mail" value="${user.mail}"
                                       title="Your mail"></label>
                <label>Birthday:<br><input class="input_text_reg" type="date" name="user_birthday"
                                           value="${user.birthday}"
                                           title="Your birthday date"></label>
                <label>City:<br>
                    <select class="select_reg" name="city_id">
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
                <label>If was not found, write city here:<br>
                    <input class="input_text_reg" type="text" name="city_name" title="City name">
                </label>
                <input type="hidden" name="user_id" value="${user.id}">
                <input type="hidden" name="command" value="user_update">
                <input class="button_reg" type="submit" name="update" value="Update">
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
                        Please do not post any personal information (or photos) of yourself that you wouldn’t want to
                        disclose
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
                        Do not question moderators in the open forum. Moderation can be stressful, use private messages
                        to chat
                        with moderators.
                    </li>
                </ol>
            </form>
        </div>
    </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/jsp/footer.jsp"/>
</body>
</html>