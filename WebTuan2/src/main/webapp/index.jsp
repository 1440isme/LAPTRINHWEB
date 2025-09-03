<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trương Công Bình</title>
<style>
    .logout-container {
        position: absolute;
        top: 10px;
        right: 10px;
    }
    .logout-btn {
        background-color: #dc3545;
        color: white;
        padding: 8px 16px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-decoration: none;
    }
    .logout-btn:hover {
        background-color: #c82333;
    }
</style>
</head>
<body>
<div class="logout-container">
    <c:if test="${not empty sessionScope.account}">
        <span>Welcome!</span>
        <a href="logout" class="logout-btn">Logout</a>
    </c:if>
</div>
<h1>HI HELLO</h1>
</body>
</html>
