<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Thêm người dùng</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <%@ include file="/common/admin/header.jsp" %>
                <div class="container py-5">
                    <div class="row justify-content-center">
                        <div class="col-md-8">
                            <div class="card p-4">
                                <h4 class="mb-3"><i class="fa fa-user-plus me-2"></i>Thêm người dùng</h4>

                                <form action="${pageContext.request.contextPath}/admin/user/insert" method="post">
                                    <div class="mb-3">
                                        <label class="form-label">Username</label>
                                        <input type="text" name="username" class="form-control" required />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Email</label>
                                        <input type="email" name="email" class="form-control" required />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Password</label>
                                        <input type="password" name="password" class="form-control" required />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Họ tên</label>
                                        <input type="text" name="fullname" class="form-control" />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Phone</label>
                                        <input type="number" name="phone" class="form-control" />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Role</label>
                                        <select name="roleId" class="form-select">
                                            <option value="2" selected>User</option>
                                            <option value="1">Admin</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">SellerId (tuỳ chọn)</label>
                                        <input type="number" name="sellerId" class="form-control" />
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Status</label>
                                        <select name="status" class="form-select">
                                            <option value="1" selected>Active</option>
                                            <option value="0">Inactive</option>
                                        </select>
                                    </div>
                                    <div class="d-flex gap-2">
                                        <button class="btn btn-primary" type="submit">Lưu</button>
                                        <a class="btn btn-secondary"
                                            href="${pageContext.request.contextPath}/admin/users">Huỷ</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%@ include file="/common/admin/footer.jsp" %>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>
