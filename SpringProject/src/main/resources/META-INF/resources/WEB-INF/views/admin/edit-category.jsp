<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa danh mục</title>
    <!-- Bootstrap 5 & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
        body {
            background: #f8fafc;
        }
        .card {
            border-radius: 16px;
            box-shadow: 0 2px 16px rgba(0,0,0,0.07);
        }
        .form-label {
            font-weight: 500;
        }
        .btn {
            border-radius: 8px;
        }
        .current-image {
            max-width: 150px;
            border-radius: 8px;
            border: 2px solid #e9ecef;
        }
    </style>
</head>
<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card p-4">
                    <h4 class="mb-4 text-center"><i class="fa fa-edit me-2"></i>Chỉnh sửa danh mục</h4>
                    
                    <!-- Hiển thị thông báo lỗi nếu có -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <i class="fa fa-exclamation-triangle me-2"></i>${error}
                        </div>
                    </c:if>
                    
                    <form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${category.id}">
                        
                        <div class="mb-3">
                            <label for="name" class="form-label">Tên danh mục</label>
                            <input type="text" id="name" name="name" class="form-control" value="${category.name}" required>
                        </div>
                        
                        <div class="mb-3">
                            <label class="form-label">Hình ảnh hiện tại</label>
                            <div class="mb-2">
                                <c:if test="${not empty category.images}">
                                    <img src="${pageContext.request.contextPath}/image?fname=${category.images}" 
                                         alt="Current image" class="current-image">
                                </c:if>
                                <c:if test="${empty category.images}">
                                    <p class="text-muted">Chưa có hình ảnh</p>
                                </c:if>
                            </div>
                        </div>
                        
                        <div class="mb-3">
                            <label for="image" class="form-label">Chọn hình ảnh mới (để trống nếu không thay đổi)</label>
                            <input type="file" id="image" name="image" class="form-control" accept="image/*">
                        </div>
                        
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-warning"><i class="fa fa-save me-1"></i>Cập nhật</button>
                            <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-outline-secondary"><i class="fa fa-arrow-left me-1"></i>Quay lại</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>