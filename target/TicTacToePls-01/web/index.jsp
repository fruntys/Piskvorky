<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginUserService" class="cz.osu.kip.TTT.services.LoginUserService" scope="session" />
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <form method="post" action="createGame">
        <input type="hidden" id="idChallenger" name="idChallenger" value="${loginUserService.user.id}">
        <button type="submit" class="btn btn-primary">Challenge a friend</button>
    </form>

    <br>

    <form method="post" action="single-player-game.jsp">
        <button type="submit" class="btn btn-primary">Two players one PC</button>
    </form>

    <br>

    <form method="post" action="">
        <button type="submit" class="btn btn-primary">Training against PC</button>
    </form>
</div>
</body>
</html>
