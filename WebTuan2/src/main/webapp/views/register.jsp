<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="container mt-5">

    <form action="register" method="post" class="w-50 mx-auto">
        <h2 class="mb-4 text-center">Tạo tài khoản mới</h2>

        <!-- Hiển thị thông báo -->
        <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-user"></i></span>
            <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-user"></i></span>
            <input type="text" placeholder="Họ tên" name="fullname" class="form-control" required>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-envelope"></i></span>
            <input type="email" placeholder="Email" name="email" class="form-control" required>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-lock"></i></span>
            <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text"><i class="fa fa-lock"></i></span>
            <input type="password" placeholder="Nhập lại mật khẩu" name="confirmPassword" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-success w-100">Đăng ký</button>
    </form>

</body>
</html>
