<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ include file="/common/taglib.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>

                <meta charset="UTF-8">
                <title>Danh sách người dùng</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
                <style>
                    body {
                        background: #f8fafc;
                    }

                    .card {
                        border-radius: 16px;
                        box-shadow: 0 2px 16px rgba(0, 0, 0, 0.07);
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
                </style>
            </head>

            <body>
                <%@ include file="/common/admin/header.jsp" %>
                    <div class="container py-5">
                        <div class="card p-4">
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h4>
                                    <i class="fa fa-users me-2"></i>Danh sách người dùng
                                </h4>

                                <div>
                                    <a href="${pageContext.request.contextPath}/admin/user/search"
                                        class="btn btn-primary me-2"> <i class="fas fa-search me-1"></i>Tìm
                                        kiếm
                                    </a> <a href="${pageContext.request.contextPath}/admin/user/add"
                                        class="btn btn-primary"> <i class="fa fa-plus me-1"></i>Thêm
                                        mới
                                    </a>
                                </div>
                            </div>

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
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>Họ tên</th>
                                            <th>Phone</th>
                                            <th>Role</th>
                                            <th>SellerId</th>
                                            <th>Status</th>
                                            <th>Thao tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${users}" var="u">
                                            <tr>
                                                <td class="align-middle">${u.userId}</td>
                                                <td class="align-middle">${u.username}</td>
                                                <td class="align-middle">${u.email}</td>
                                                <td class="align-middle">${u.fullname}</td>
                                                <td class="align-middle">${u.phone}</td>
                                                <td class="align-middle">
                                                    <c:choose>
                                                        <c:when test="${u.roleId == 1}">Admin</c:when>
                                                        <c:when test="${u.roleId == 2}">User</c:when>
                                                        <c:otherwise>${u.roleId}</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="align-middle">${u.sellerId}</td>
                                                <td class="align-middle">
                                                    <span
                                                        class="badge ${u.status == 1 ? 'bg-success' : 'bg-secondary'}">${u.status
                                                        == 1 ? 'Active' : 'Inactive'}</span>
                                                </td>
                                                <td class="align-middle">
                                                    <a href="${pageContext.request.contextPath}/admin/user/edit?id=${u.userId}"
                                                        class="btn btn-sm btn-outline-warning me-2">
                                                        <i class="fa fa-edit"></i> Sửa
                                                    </a>
                                                    <form action="${pageContext.request.contextPath}/admin/user/delete"
                                                        method="post" class="d-inline">
                                                        <input type="hidden" name="id" value="${u.userId}" />
                                                        <button type="submit" class="btn btn-sm btn-outline-danger"
                                                            onclick="return confirm('Xoá người dùng này?')">
                                                            <i class="fa fa-trash"></i> Xóa
                                                        </button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty users}">
                                            <tr>
                                                <td colspan="9" class="text-center text-muted py-4">
                                                    <i class="fa fa-inbox fa-3x mb-3 d-block"></i>
                                                    Chưa có người dùng nào
                                                </td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>

                            <c:if test="${not empty totalPages && totalPages > 1}">
                                <nav aria-label="Phân trang người dùng">
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${currentPage > 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="?page=${currentPage - 1}">
                                                    <i class="fa fa-chevron-left"></i>
                                                </a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${totalPages}" var="pageNum">
                                            <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                                <a class="page-link" href="?page=${pageNum}">${pageNum}</a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${currentPage < totalPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="?page=${currentPage + 1}">
                                                    <i class="fa fa-chevron-right"></i>
                                                </a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>
                    </div>
                    <%@ include file="/common/admin/footer.jsp" %>
                        <script
                            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>