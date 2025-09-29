<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết sách</title>
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
        .star-rating input[type="radio"]:checked ~ label,
        .star-rating label:hover,
        .star-rating label:hover ~ label {
            color: #ffc107;
        }
        .star-rating {
            flex-direction: row-reverse;
        }
    </style>
</head>
<body>

<%@ include file="/common/web/header.jsp"%>

<div class="container my-5">
    <c:choose>
        <c:when test="${not empty book}">
            <div class="row">
                <!-- Book Information -->
                <div class="col-md-4 text-center">
                    <c:choose>
                        <c:when test="${not empty book.coverImage}">
                            <img src="${book.coverImage}" alt="${book.title}" class="img-fluid book-cover mb-3">
                        </c:when>
                        <c:otherwise>
                            <div class="book-cover bg-light d-flex align-items-center justify-content-center mb-3" style="height: 300px;">
                                <i class="fas fa-book fa-5x text-muted"></i>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-8">
                    <h2>${book.title}</h2>
                    <p><strong>ISBN:</strong> ${book.isbn}</p>
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
                    <p><strong>Giá:</strong> <span class="text-success">${book.price} VND</span></p>
                    <p><strong>Số lượng còn lại:</strong> ${book.quantity}</p>

                    <c:if test="${not empty book.description}">
                        <div class="mt-3">
                            <strong>Mô tả:</strong>
                            <p class="text-muted">${book.description}</p>
                        </div>
                    </c:if>
                </div>
            </div>

            <!-- Rating Form (only show if user is logged in) -->
            <c:if test="${not empty sessionScope.account}">
                <div class="row mt-5">
                    <div class="col-md-8 offset-md-2">
                        <div class="card">
                            <div class="card-header">
                                <h5><i class="fas fa-star me-2"></i>Đánh giá sách này</h5>
                            </div>
                            <div class="card-body">
                                <form method="post" action="${pageContext.request.contextPath}/book">
                                    <input type="hidden" name="bookId" value="${book.bookId}">

                                    <div class="mb-3">
                                        <label class="form-label">Đánh giá của bạn:</label>
                                        <div class="star-rating">
                                            <input type="radio" id="star5" name="rating" value="5" required>
                                            <label for="star5">★</label>
                                            <input type="radio" id="star4" name="rating" value="4">
                                            <label for="star4">★</label>
                                            <input type="radio" id="star3" name="rating" value="3">
                                            <label for="star3">★</label>
                                            <input type="radio" id="star2" name="rating" value="2">
                                            <label for="star2">★</label>
                                            <input type="radio" id="star1" name="rating" value="1">
                                            <label for="star1">★</label>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="review" class="form-label">Nhận xét:</label>
                                        <textarea class="form-control" id="review" name="review" rows="4"
                                                  placeholder="Chia sẻ cảm nghĩ của bạn về cuốn sách này..."></textarea>
                                    </div>

                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-paper-plane me-1"></i>Gửi đánh giá
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- Reviews Section -->
            <div class="row mt-5">
                <div class="col-12">
                    <h4><i class="fas fa-comments me-2"></i>Đánh giá từ độc giả (${fn:length(reviews)})</h4>

                    <c:choose>
                        <c:when test="${not empty reviews}">
                            <c:forEach var="review" items="${reviews}">
                                <div class="review-item">
                                    <div class="d-flex justify-content-between align-items-start">
                                        <div>
                                            <h6 class="mb-1">
                                                <c:choose>
                                                    <c:when test="${not empty review.user.fullname}">
                                                        ${review.user.fullname}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${review.user.email}
                                                    </c:otherwise>
                                                </c:choose>
                                            </h6>
                                            <div class="rating-stars mb-2">
                                                <c:forEach begin="1" end="5" var="star">
                                                    <i class="fas fa-star ${star <= review.rating ? '' : 'text-muted'}"></i>
                                                </c:forEach>
                                                <small class="text-muted ms-2">(${review.rating}/5)</small>
                                            </div>
                                        </div>
                                    </div>

                                    <c:if test="${not empty review.reviewText}">
                                        <p class="text-muted mb-0">${review.reviewText}</p>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center text-muted py-4">
                                <i class="fas fa-comments fa-3x mb-3"></i>
                                <p>Chưa có đánh giá nào cho cuốn sách này.</p>
                                <c:if test="${not empty sessionScope.account}">
                                    <p>Hãy là người đầu tiên đánh giá!</p>
                                </c:if>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="text-center">
                <div class="alert alert-warning">
                    <i class="fas fa-exclamation-triangle me-2"></i>
                    Không tìm thấy thông tin sách.
                </div>
                <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">
                    <i class="fas fa-arrow-left me-1"></i>Quay lại trang chủ
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="/common/web/footer.jsp"%>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
