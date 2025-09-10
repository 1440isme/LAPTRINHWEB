<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center">
                        <h3>Đặt lại mật khẩu</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty alert}">
                            <div class="alert alert-danger">${alert}</div>
                        </c:if>

                        <c:if test="${not empty success}">
                            <div class="alert alert-success">${success}</div>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                            <div class="mb-3">
                                <label for="email" class="form-label">Email:</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                                    <input type="email" class="form-control" id="email" name="email" 
                                           placeholder="Nhập email của bạn" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="newPassword" class="form-label">Mật khẩu mới:</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fa fa-lock"></i></span>
                                    <input type="password" class="form-control" id="newPassword" name="newPassword" 
                                           placeholder="Nhập mật khẩu mới" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="confirmPassword" class="form-label">Xác nhận mật khẩu:</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="fa fa-lock"></i></span>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" 
                                           placeholder="Nhập lại mật khẩu mới" required>
                                </div>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary">Đặt lại mật khẩu</button>
                            </div>
                        </form>

                        <div class="text-center mt-3">
                            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">Quay lại đăng nhập</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>