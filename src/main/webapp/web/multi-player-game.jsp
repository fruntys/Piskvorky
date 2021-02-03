<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginUserService" class="cz.osu.kip.TTT.services.LoginUserService" scope="session" />
<jsp:useBean id="boardService" class="cz.osu.kip.TTT.services.BoardService" scope="session" />
<html>
<head>
    <title>MultiPlayer game</title>
    <link rel="stylesheet" href="css/board.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">

    <c:choose>
        <c:when test="${loginUserService.user.id != 0}">

            <c:if test="${not empty param.key && not empty param.game && not empty param.challenger }" >
            
            <c:url value = "http://localhost:8080/TicTacToePls-01/web/createGame" var="url">
                <c:param name="key" value="${param.key}" />
                <c:param name="game" value="${param.game}" />
                <c:param name="challenger" value="${param.challenger}" />

                <c:if test="${loginUserService.user.id > 0 && loginUserService.user.id != param.challenger}" >
                    <c:param name="opponent" value="${loginUserService.user.id}" />
                </c:if>
            </c:url>
            </c:if>

            <c:if test="${loginUserService.user.id == param.challenger}" >
                <h3>Send this link to your opponent</h3>
                <p>${url}</p>
            </c:if>

            <c:if test="${loginUserService.user.id != param.challenger && empty param.opponent}" >
                <c:redirect url="${url}" />
            </c:if>

        </c:when>
        <c:otherwise>
            <p>You need to login first. Then come back to this page, please.</p>
        </c:otherwise>
    </c:choose>

    <table>
        <c:forEach begin="0" end="14" var="rowIndex">
            <tr>
            <c:forEach begin="0" end="14" var="columnIndex">
                <th id="cell" column="${columnIndex}" row="${rowIndex}">${boardService.board.getCell(rowIndex, columnIndex)}</th>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>

    <h2 id="playCheck"></h2>

    <form method="post" action="moveHandler">
        <input type="hidden" id="key" name="key" value="${param.key}">
        <input type="hidden" id="game" name="game" value="${param.game}">
        <input type="hidden" id="challenger" name="challenger" value="${param.challenger}">
        <input type="hidden" id="opponent" name="opponent" value="${param.opponent}">

        <input type="hidden" id="input_cellRow" name="input_cellRow" value="">
        <input type="hidden" id="input_cellCol" name="input_cellCol" value="">
        <button id="placer">Place character</button>
    </form>
</div>

<script src="js/multiplayer-move-handler.js"></script>

</body>
</html>
