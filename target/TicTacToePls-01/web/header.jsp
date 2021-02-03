<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginUserService" class="cz.osu.kip.TTT.services.LoginUserService" scope="session" />

<c:choose>
    <c:when test="${loginUserService.user.email == null || loginUserService.user.nickname == null}">
        <div class="headerContainer">
            <div class="headerDivForm">
                <form method="post" action="loginUser">
                    <label for="nick-mail">Nickname/E-mail:</label>
                    <input type="text" id="nick-mail" name="nick-mail">
                    <label for="pw">Password:</label>
                    <input type="password" id="pw" name="pw">
                    <button type="submit" class="btn btn-primary">Log in</button>
                </form>
            </div>
            <div class="headerDivForm">
                <form action="register.jsp" method="post">
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h2>Welcome, ${loginUserService.user.nickname}</h2>
    </c:otherwise>
</c:choose>
