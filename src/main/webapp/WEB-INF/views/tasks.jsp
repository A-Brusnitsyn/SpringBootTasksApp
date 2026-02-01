<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Мои задачи</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .tasks { margin: 20px 0; }
        .task { padding: 5px; border: 1px solid #ddd; margin: 5px 0; }
    </style>
</head>
<body>
<h2>Добро пожаловать, ${username}!</h2>

<div class="tasks">
    <h3>Ваши задачи:</h3>
    <c:choose>
        <c:when test="${empty tasks}">
            <p>Задач пока нет</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="task" items="${tasks}">
                <div class="task">${task}</div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>

<br>
<a href="/logout">Выйти</a>
</body>
</html>