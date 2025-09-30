<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Danh sách sản phẩm</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        </head>

        <body>
            <%@ include file="/common/admin/header.jsp" %>
                <div class="container py-5">
                    <div class="card p-4">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <h4><i class="fa fa-box me-2"></i>Danh sách sản phẩm</h4>
                            <div>
                                <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary">
                                    <i class="fa fa-plus me-1"></i>Thêm mới
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
                                        <th>Tên</th>
                                        <th>Mã</th>
                                        <th>Danh mục</th>
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Kho</th>
                                        <th>Seller</th>
                                        <th>Status</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${productList}" var="p">
                                        <tr>
                                            <td class="align-middle">${p.productId}</td>
                                            <td class="align-middle">${p.productName}</td>
                                            <td class="align-middle">${p.productCode}</td>
                                            <td class="align-middle">
                                                <c:out
                                                    value="${p.category != null ? p.category.categoryName : p.categoryId}" />
                                            </td>
                                            <td class="align-middle">${p.price}</td>
                                            <td class="align-middle">${p.amount}</td>
                                            <td class="align-middle">${p.stock}</td>
                                            <td class="align-middle">${p.sellerId}</td>
                                            <td class="align-middle">
                                                <span
                                                    class="badge ${p.status == 1 ? 'bg-success' : 'bg-secondary'}">${p.status
                                                    == 1 ? 'Active' : 'Inactive'}</span>
                                            </td>
                                            <td class="align-middle">
                                                <a href="${pageContext.request.contextPath}/admin/product/edit?productId=${p.productId}"
                                                    class="btn btn-sm btn-outline-warning me-2">
                                                    <i class="fa fa-edit"></i> Sửa
                                                </a>
                                                <form action="${pageContext.request.contextPath}/admin/product/delete"
                                                    method="post" class="d-inline">
                                                    <input type="hidden" name="productId" value="${p.productId}" />
                                                    <button type="submit" class="btn btn-sm btn-outline-danger"
                                                        onclick="return confirm('Xoá sản phẩm này?')">
                                                        <i class="fa fa-trash"></i> Xóa
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty productList}">
                                        <tr>
                                            <td colspan="9" class="text-center text-muted py-4">Chưa có sản phẩm nào
                                            </td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>

                        <c:if test="${not empty totalPages && totalPages > 1}">
                            <nav aria-label="Phân trang sản phẩm">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${currentPage > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${currentPage - 1}"><i
                                                    class="fa fa-chevron-left"></i></a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${totalPages}" var="pageNum">
                                        <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                            <a class="page-link" href="?page=${pageNum}">${pageNum}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${currentPage < totalPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="?page=${currentPage + 1}"><i
                                                    class="fa fa-chevron-right"></i></a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                </div>
                <%@ include file="/common/admin/footer.jsp" %>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>