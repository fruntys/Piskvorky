<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Two guys one pc</title>
    <link rel="stylesheet" type="text/css" href="css/board.css" />
</head>
<body>
<jsp:include page="header.jsp" />

<div class="totalContainer">
    <div class="cellContainer">
        <div>
            <c:forEach var="i" begin="0" end="14">
                <c:forEach var="j" begin="0" end="14">
                    <div class="cell" cell-row="${i}" cell-column="${j}"> </div>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
    <h2 class="game-status"></h2>
    <button id="restartGameBtn">Restart</button>
</div>

<script src="js/single-player-game.js"></script>
</body>
</html>