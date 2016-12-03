<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<c:if test="${not empty message}">
    <p class="message">${message}</p>
</c:if>