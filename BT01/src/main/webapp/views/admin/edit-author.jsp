<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/admin/header.jsp"%>
    <meta charset="UTF-8">
    <title>Chỉnh sửa tác giả</title>
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
        .form-control, .form-select {
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card p-4">
                    <h4 class="mb-4 text-center"><i class="fa fa-user-edit me-2"></i>Chỉnh sửa tác giả</h4>

                    <!-- Hiển thị thông báo lỗi nếu có -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            <i class="fa fa-exclamation-triangle me-2"></i>${error}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/author/update" method="post">
                        <input type="hidden" name="authorId" value="${author.authorId}">

                        <div class="mb-3">
                            <label for="authorName" class="form-label">Tên tác giả <span class="text-danger">*</span></label>
                            <input type="text" id="authorName" name="authorName" class="form-control" value="${author.authorName}" required>
                        </div>

                        <div class="mb-3">
                            <label for="dateOfBirth" class="form-label">Ngày sinh</label>
                            <input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control" value="${author.dateOfBirth}">
                        </div>

                        <c:if test="${author.books != null && author.books.size() > 0}">
                            <div class="mb-3">
                                <label class="form-label">Sách đã viết</label>
                                <div class="bg-light p-3 rounded">
                                    <c:forEach items="${author.books}" var="book" varStatus="status">
                                        <span class="badge bg-info me-1 mb-1">${book.title}</span>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>

                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-warning px-4">
                                <i class="fa fa-save me-1"></i>Cập nhật
                            </button>
                            <a href="${pageContext.request.contextPath}/admin/authors" class="btn btn-outline-secondary px-4">
                                <i class="fa fa-arrow-left me-1"></i>Quay lại
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/common/admin/footer.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

