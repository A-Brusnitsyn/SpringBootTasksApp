<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
<h2>Вход в систему</h2>

<% if (request.getAttribute("error") != null) { %>
<p class="error"><%= request.getAttribute("error") %></p>
<% } %>

<% if (request.getAttribute("success") != null) { %>
<p class="success"><%= request.getAttribute("success") %></p>
<% } %>

<form method="post" action="/login">
    <div>
        <label>Логин:</label><br>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>Пароль:</label><br>
        <input type="password" name="password" required>
    </div>
    <br>
    <button type="submit">Войти</button>
</form>

<p>Нет аккаунта? <a href="/register">Зарегистрироваться</a></p>
</body>
</html>