<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="vn.binh.model.Category" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
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
        .table {
            border-radius: 12px;
            overflow: hidden;
            background: #fff;
        }
        .btn {
            border-radius: 8px;
        }
        .fa {
            margin-right: 4px;
        }
    </style>
</head>
<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="card p-4 mb-4">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="mb-0"><i class="fa fa-list me-2"></i>Danh sách danh mục</h4>
                        <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-success">
                            <i class="fa fa-plus"></i> Thêm mới
                        </a>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>ID</th>
                                    <th>Tên danh mục</th>
                                    <th>Icon</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (categories != null) {
                                        for (Category c : categories) {
                                %>
                                    <tr>
                                        <td><%= c.getCateid() %></td>
                                        <td><%= c.getCatename() %></td>
                                        <td>
                                            <%
                                                if (c.getIcon() != null) {
                                            %>
                                                <img src="${pageContext.request.contextPath}/uploads/<%= c.getIcon() %>" alt="icon" style="width:48px;height:48px;">
                                            <%
                                                } else {
                                            %>
                                                <span class="text-muted"><i class="fa fa-image"></i> Không có</span>
                                            <%
                                                }
                                            %>
                                        </td>
                                        <td>
                                            <div class="d-flex gap-2">
                                                <a href="${pageContext.request.contextPath}/admin/category/edit?id=<%= c.getCateid() %>" class="btn btn-primary btn-sm">
                                                    <i class="fa fa-edit"></i> Sửa
                                                </a>
                                                <a href="${pageContext.request.contextPath}/admin/category/delete?id=<%= c.getCateid() %>" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?');">
                                                    <i class="fa fa-trash"></i> Xóa
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>