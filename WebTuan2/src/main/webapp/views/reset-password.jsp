<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt lại mật khẩu</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="container mt-5">

    <form action="${pageContext.request.contextPath}/reset-password" method="post" class="w-50 mx-auto">
        <h2 class="mb-4 text-center">Đặt lại mật khẩu</h2>

        <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <input type="hidden" name="email" value="${email}">

        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-lock"></i></span>
            <input type="password" placeholder="Mật khẩu mới" name="newPassword" class="form-control" required minlength="6">
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-lock"></i></span>
            <input type="password" placeholder="Xác nhận mật khẩu mới" name="confirmPassword" class="form-control" required minlength="6">
        </div>

        <button type="submit" class="btn btn-primary w-100">Đặt lại mật khẩu</button>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">Quay lại đăng nhập</a>
        </div>
    </form>

</body>
</html>