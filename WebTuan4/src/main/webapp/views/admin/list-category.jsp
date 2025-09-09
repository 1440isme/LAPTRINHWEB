<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách danh mục</title>
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
        .table th {
            border: none;
            font-weight: 600;
            color: #495057;
            background: #f8f9fa;
        }
        .btn {
            border-radius: 8px;
        }
        .category-image {
            width: 60px;
            height: 60px;
            object-fit: cover;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="container py-5">
        <div class="card p-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h4><i class="fa fa-list me-2"></i>Danh sách danh mục</h4>
                <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary">
                    <i class="fa fa-plus me-1"></i>Thêm mới
                </a>
            </div>
            
            <!-- Hiển thị thông báo thành công/lỗi -->
            <c:if test="${not empty message}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="fa fa-check-circle me-2"></i>${message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="fa fa-exclamation-triangle me-2"></i>${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Hình ảnh</th>
                            <th>Tên danh mục</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td class="align-middle">${category.id}</td>
                                <td class="align-middle">
                                    <c:if test="${not empty category.images}">
                                        <img src="${pageContext.request.contextPath}/image?fname=${category.images}" 
                                             alt="Category image" class="category-image">
                                    </c:if>
                                    <c:if test="${empty category.images}">
                                        <div class="category-image d-flex align-items-center justify-content-center bg-light text-muted">
                                            <i class="fa fa-image"></i>
                                        </div>
                                    </c:if>
                                </td>
                                <td class="align-middle fw-medium">${category.name}</td>
                                <td class="align-middle">
                                    <a href="${pageContext.request.contextPath}/admin/category/edit?id=${category.id}" 
                                       class="btn btn-sm btn-outline-warning me-2">
                                        <i class="fa fa-edit"></i> Sửa
                                    </a>
                                    <a href="${pageContext.request.contextPath}/admin/category/delete?id=${category.id}" 
                                       class="btn btn-sm btn-outline-danger"
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?')">
                                        <i class="fa fa-trash"></i> Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty categories}">
                            <tr>
                                <td colspan="4" class="text-center text-muted py-4">
                                    <i class="fa fa-inbox fa-3x mb-3 d-block"></i>
                                    Chưa có danh mục nào
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>