<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Chi tiết sản phẩm</title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">

                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Font Awesome -->
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

                <style>
                    .rating-stars {
                        color: #ffc107;
                    }

                    .book-cover {
                        max-width: 300px;
                        max-height: 400px;
                    }

                    .product-row {
                        border: 1px solid #eee;
                        border-radius: 6px;
                        padding: 16px;
                    }

                    .review-item {
                        border-bottom: 1px solid #eee;
                        padding: 15px 0;
                    }

                    .star-rating {
                        display: inline-flex;
                        cursor: pointer;
                    }

                    .star-rating input[type="radio"] {
                        display: none;
                    }

                    .star-rating label {
                        font-size: 1.5rem;
                        color: #ddd;
                        cursor: pointer;
                        transition: color 0.2s;
                    }

                    .star-rating input[type="radio"]:checked~label,
                    .star-rating label:hover,
                    .star-rating label:hover~label {
                        color: #ffc107;
                    }

                    .star-rating {
                        flex-direction: row-reverse;
                    }
                </style>
            </head>

            <body>

                <%@ include file="/common/web/header.jsp" %>

                    <div class="container my-5">
                        <c:choose>
                            <c:when test="${not empty product}">
                                <div class="row product-row">
                                    <!-- Hình ảnh sản phẩm -->
                                    <div class="col-md-4 text-center">
                                        <c:choose>
                                            <c:when test="${not empty product.images}">
                                                <img src="${pageContext.request.contextPath}/image?fname=${product.images}"
                                                    alt="${product.productName}" class="img-fluid book-cover mb-3">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="book-cover bg-light d-flex align-items-center justify-content-center mb-3"
                                                    style="height: 300px;">
                                                    <i class="fas fa-image fa-5x text-muted"></i>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <!-- Thông tin sản phẩm -->
                                    <div class="col-md-8">
                                        <p><strong>Tên sản phẩm:</strong> ${product.productName}</p>
                                        <p><strong>Mã sản phẩm:</strong> ${product.productCode}</p>
                                        <p><strong>Danh mục:</strong>
                                            <c:out
                                                value="${product.category != null ? product.category.categoryName : '---'}" />
                                        </p>
                                        <p><strong>Giá:</strong> <span class="text-success">${product.price} VND</span>
                                        </p>
                                        <p><strong>Số lượng:</strong> ${product.amount}</p>
                                        <p><strong>Mô tả:</strong>
                                            <c:out value="${product.description}" default="---" />
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="text-center">
                                    <div class="alert alert-warning">
                                        <i class="fas fa-exclamation-triangle me-2"></i>
                                        Không tìm thấy thông tin sản phẩm.
                                    </div>
                                    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">
                                        <i class="fas fa-arrow-left me-1"></i>Quay lại trang chủ
                                    </a>
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