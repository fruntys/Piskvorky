<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register a new user</title>
</head>
<body>
<div class="totalContainer">

    <h1>Register a new player account</h1>

    <div class="formContainer">
        <form method="post" action="registerUser">
            <label for="nickname">Nickname:</label>
            <input type="text" id="nickname" name="nickname">
            <br>
            <label for="mail">E-mail:</label>
            <input type="text" id="mail" name="mail">
            <br>
            <label for="pw1">Password:</label>
            <input type="password" id="pw1" name="pw1">
            <br>
            <label for="pw2">Repeat password:</label>
            <input type="password" id="pw2" name="pw2">
            <br>
            <button type="submit">Register</button>
        </form>
    </div>
</div>
</body>
</html>
