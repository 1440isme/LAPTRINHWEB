<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Tìm kiếm người dùng</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <%@ include file="/common/admin/header.jsp" %>
                <div class="container py-5">
                    <div class="card p-4">
                        <h4 class="mb-3"><i class="fa fa-search me-2"></i>Tìm kiếm người dùng</h4>

                        <form method="get" class="row g-2"
                            action="${pageContext.request.contextPath}/admin/user/search">
                            <div class="col-auto">
                                <input type="text" name="keyword" class="form-control" placeholder="Nhập họ tên..."
                                    value="${keyword}" />
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-primary" type="submit">Tìm</button>
                            </div>
                            <div class="col-auto">
                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/users">Quay
                                    lại</a>
                            </div>
                        </form>

                        <div class="table-responsive mt-4">
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
                                                Không có kết quả phù hợp
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%@ include file="/common/admin/footer.jsp" %>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>
