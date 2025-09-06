<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vn.binh.model.Category" %>
<%
    Category category = (Category) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa danh mục</title>
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
        .img-preview {
            border-radius: 8px;
            border: 1px solid #ddd;
            width: 100px;
            height: 100px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card p-4">
                    <h4 class="mb-4 text-center"><i class="fa fa-edit me-2"></i>Sửa danh mục</h4>
                    <form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<%= category.getCateid() %>">
                        <div class="mb-3">
                            <label for="name" class="form-label">Tên danh mục</label>
                            <input type="text" id="name" name="name" class="form-control" value="<%= category.getCatename() %>" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Icon hiện tại</label><br>
                            <%
                                if (category.getIcon() != null) {
                            %>
                                <img src="${pageContext.request.contextPath}/uploads/<%= category.getIcon() %>" alt="icon" class="img-preview mb-2">
                            <%
                                } else {
                            %>
                                <span class="text-muted"><i class="fa fa-image"></i> Chưa có icon</span>
                            <%
                                }
                            %>
                        </div>
                        <div class="mb-3">
                            <label for="icon" class="form-label">Chọn icon mới (nếu muốn thay)</label>
                            <input type="file" id="icon" name="icon" class="form-control" accept="image/*">
                        </div>
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary"><i class="fa fa-save me-1"></i>Cập nhật</button>
                            <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-outline-secondary"><i class="fa fa-arrow-left me-1"></i>Quay lại</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>