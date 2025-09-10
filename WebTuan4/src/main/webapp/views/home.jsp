<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">WebTuan4 - User</a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">Xin chào, ${user.fullname}</span>
                <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
            </div>
        </div>
    </nav>
    
    <div class="container mt-5">
        <div class="row">
            <div class="col-12">
                <div class="jumbotron bg-light p-5 rounded">
                    <h1 class="display-4">Chào mừng đến với hệ thống!</h1>
                    <p class="lead">Bạn đang đăng nhập với tài khoản user.</p>
                    <hr class="my-4">
                    <div class="row">
                        <div class="col-md-6">
                            <h5>Thông tin tài khoản:</h5>
                            <ul class="list-group">
                                <li class="list-group-item"><strong>ID:</strong> ${user.id}</li>
                                <li class="list-group-item"><strong>Username:</strong> ${user.name}</li>
                                <li class="list-group-item"><strong>Họ tên:</strong> ${user.fullname}</li>
                                <li class="list-group-item"><strong>Phone:</strong> ${user.phone}</li>
                                <li class="list-group-item"><strong>Role:</strong> User (${user.role})</li>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <h5>Chức năng:</h5>
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="button">Xem thông tin cá nhân</button>
                                <button class="btn btn-secondary" type="button">Cập nhật thông tin</button>
                                <button class="btn btn-info" type="button">Đổi mật khẩu</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>