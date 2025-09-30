<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Trang home của user</title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">

                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Font Awesome -->
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

                <style>
                    .book-item {
                        border: 1px solid #ddd;
                        border-radius: 8px;
                        margin-bottom: 20px;
                        padding: 15px;
                    }

                    .book-cover {
                        max-width: 150px;
                    }
                </style>
            </head>

            <body>

                <%@ include file="/common/web/header.jsp" %>

                    <div class="container my-5">
                        <h2 class="text-center mb-4">Danh sách sản phẩm</h2>
                        <h3 class="text-muted small mb-3">Mã cửa hàng: #${p.sellerId}</h3>

                        <c:choose>
                            <c:when test="${not empty productList}">
                                <div class="row g-4">
                                    <c:forEach var="p" items="${productList}">
                                        <div class="col-12 col-md-6 col-lg-4">
                                            <div class="card h-100 position-relative">
                                                <c:if test="${not empty p.sellerId}">
                                                    <span
                                                        class="badge bg-secondary position-absolute top-0 end-0 m-2">#${p.sellerId}</span>
                                                </c:if>
                                                <div class="text-center p-3">
                                                    <c:choose>
                                                        <c:when test="${not empty p.images}">
                                                            <img src="${pageContext.request.contextPath}/image?fname=${p.images}"
                                                                alt="${p.productName}" class="img-fluid book-cover">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="book-cover bg-light d-flex align-items-center justify-content-center"
                                                                style="height: 200px;">
                                                                <i class="fas fa-image fa-3x text-muted"></i>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <div class="card-body">



                                                    <h6 class="card-title mb-2">
                                                        <a
                                                            href="${pageContext.request.contextPath}/product?id=${p.productId}">${p.productName}</a>
                                                    </h6>
                                                    <div class="small text-muted mb-2">Mã sản phẩm: ${p.productCode}
                                                    </div>
                                                    <div class="mb-1">
                                                        <strong>Danh mục:</strong>
                                                        <c:out
                                                            value="${p.category != null ? p.category.categoryName : '---'}" />
                                                    </div>
                                                    <div class="mb-1"><strong>Giá:</strong> ${p.price} VND</div>
                                                    <div class="mb-1"><strong>Số lượng:</strong> ${p.amount}</div>
                                                </div>
                                                <div class="card-footer bg-transparent border-0 pt-0 pb-3 px-3">
                                                    <a class="btn btn-sm btn-primary w-100"
                                                        href="${pageContext.request.contextPath}/product?id=${p.productId}">
                                                        Xem chi tiết
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="text-center">
                                    <div class="alert alert-info">
                                        <i class="fas fa-info-circle me-2"></i>
                                        Hiện tại chưa có sản phẩm nào trong hệ thống.
                                        <br>
                                        <small class="text-muted">Vui lòng thêm dữ liệu mẫu để kiểm thử.</small>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <%@ include file="/common/web/footer.jsp" %>

                        <!-- Bootstrap JS -->
                        <script
                            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
            </body>

            </html>