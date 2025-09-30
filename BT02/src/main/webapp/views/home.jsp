<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
    <title>Trang home của user</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

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

<%@ include file="/common/web/header.jsp"%>

<div class="container my-5">
    <h2 class="text-center mb-4">Danh sách sách</h2>

    <c:choose>
        <c:when test="${not empty bookList}">
            <c:forEach var="book" items="${bookList}">
                <div class="row book-item">
                    <!-- Ảnh bìa -->
                    <div class="col-md-3 text-center">
                        <c:choose>
                            <c:when test="${not empty book.coverImage}">
                                <img src="${pageContext.request.contextPath}/image?fname=${book.coverImage}" alt="${book.title}" class="img-fluid book-cover">
                            </c:when>
                            <c:otherwise>
                                <div class="book-cover bg-light d-flex align-items-center justify-content-center" style="height: 200px;">
                                    <i class="fas fa-book fa-3x text-muted"></i>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <!-- Thông tin sách -->
                    <div class="col-md-9">
                        <p><strong>Tiêu đề:</strong> <a href="book?bookId=${book.bookId}" class="text-decoration-none text-primary fs-5 fw-bold">${book.title}</a></p>
                        <p><strong>Mã isbn:</strong> ${book.isbn}</p>
                        <p><strong>Tác giả:</strong>
                            <c:choose>
                                <c:when test="${not empty book.authors}">
                                    <c:forEach var="author" items="${book.authors}" varStatus="status">
                                        ${author.authorName}<c:if test="${!status.last}">, </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <em>Chưa có thông tin tác giả</em>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p><strong>Nhà xuất bản:</strong> ${book.publisher}</p>
                        <p><strong>Ngày xuất bản:</strong> ${book.publishDate}</p>
                        <p><strong>Số lượng:</strong> ${book.quantity}</p>
                        <p><strong>Giá:</strong> ${book.price} VND</p>
                        <p><strong>Đánh giá:</strong> <span class="text-primary">(${book.ratingCount} đánh giá)</span></p>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="text-center">
                <div class="alert alert-info">
                    <i class="fas fa-info-circle me-2"></i>
                    Hiện tại chưa có sách nào trong hệ thống hoặc không thể kết nối đến cơ sở dữ liệu.
                    <br>
                    <small class="text-muted">Vui lòng kiểm tra kết nối cơ sở dữ liệu hoặc thêm dữ liệu mẫu.</small>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="/common/web/footer.jsp"%>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
