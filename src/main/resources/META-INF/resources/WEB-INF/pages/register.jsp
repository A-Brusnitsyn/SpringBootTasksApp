<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .error { color: red; }
    </style>
</head>
<body>
<h2>Регистрация</h2>

<% if (request.getAttribute("error") != null) { %>
<p class="error"><%= request.getAttribute("error") %></p>
<% } %>

<form method="post" action="/register">
    <div>
        <label>Логин:</label><br>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>Email:</label><br>
        <input type="email" name="email" required>
    </div>
    <div>
        <label>Пароль:</label><br>
        <input type="password" name="password" required>
    </div>
    <div>
        <label>Подтвердите пароль:</label><br>
        <input type="password" name="confirmPassword" required>
    </div>
    <br>
    <button type="submit">Зарегистрироваться</button>
</form>

<p>Уже есть аккаунт? <a href="/login">Войти</a></p>
</body>
</html>