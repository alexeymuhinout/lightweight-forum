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
                        <a href="${pageContext.request.contextPath}/controller?command=profile_show">Profile</a>
                        <a href="${pageContext.request.contextPath}/controller?command=logout">Logout[${user.name}]</a>
                    </c:otherwise>
                </c:choose>
                <a href="${pageContext.request.contextPath}/controller?command=registration_show">Registration</a>
            </div>
        </section>
        <ul class="main-ul">
            <li><a href="${pageContext.request.contextPath}/controller?command=index_show">Main</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=categories_show">Forum</a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=blog_show">Blog</a></li>
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
    <section>
        <p>This forum has been created so that you can exchange ideas, converse and meet with others who share a common
            interest in a safe environment.

            Every community has its own culture and boundaries. To share how you feel about content, we encourage you to
            use
            the Reactions (the little icons under every comment) to share your positive feedback with users. This will
            help
            reinforce the proper behavior which we expect by all community members.</p>
        <h5>Here are some rules that we ask you to follow:</h5>
        <ul>
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
        </ul>
        <p>In summary, use your common sense, treat others are you expect to be treated and help us build a great
            community
            experience for everyone. Getting the most out of this forum.</p>
    </section>
    <section>
        <h5>Here are some tips and tricks on using this forum:</h5>

        <ul>
            <li>
                Use the reactions to share how you feel about other’s comments. The most liked content shows up on the
                Best of page.
            </li>
            <li>
                Mention others. To let someone know you are talking about or too them put the @ symbol before the
                username. They’ll get notified when you do.
            </li>
            <li>
                Update your notification settings. You’ll receive a notification if someone mentions you or posts to a
                discussion that you’ve bookmarked. You can set notifications from your edit profile page.
            </li>
            <li>
                Post a comment to someone’s Activity Feed on their profile page. Remember, these are comments can be
                seen by everyone. You can private message members too, or check your messages by going to your “inbox”
                in your personal menu.
            </li>
            <li>
                Bookmark a discussion (click the star) to get notifications for new comments. You can edit notification
                settings from your profile.
            </li>
            <li>
                Check out the forum from your mobile phone. Just go to the forum URL in your browser - no need to
                download an app.
            </li>
            <li>
                Want to embed content? Our forum supports automatic embed for Twitter, Pinterest, Vine, YouTube, and
                Vimeo. Just paste in the url of the content, and we will do the rest.
            </li>
            <li>
                Markup your posts with simple HTML tags and preview your comment before posting.
            </li>
            <li>
                Choose titles for your posts that are clear and concise.
            </li>
            <li>
                Don’t forget to add a picture to your profile.
            </li>
            <li>
                Use the search before you ask a question, you might be surprised to find the answer.
            </li>
            <li>
                Most important of all, have fun and be kind to one another!
            </li>
        </ul>
    </section>
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