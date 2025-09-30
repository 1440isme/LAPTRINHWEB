<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Danh sách sách</title>
<!-- Bootstrap 5 & Font Awesome -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
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

.book-image {
	width: 60px;
	height: 80px;
	object-fit: cover;
	border-radius: 8px;
}

.book-title {
	font-weight: 500;
	color: #2d3748;
}

.book-publisher {
	font-size: 0.875rem;
	color: #718096;
}

.book-price {
	font-weight: 600;
	color: #e53e3e;
}
</style>
</head>
<body>
	<%@ include file="/common/admin/header.jsp"%>
	<div class="container py-5">
		<div class="card p-4">
			<div class="d-flex justify-content-between align-items-center mb-4">
				<h4>
					<i class="fa fa-book me-2"></i>Danh sách sách
				</h4>

				<div>
					<a href="${pageContext.request.contextPath}/admin/book/search"
						class="btn btn-primary me-2"> <i class="fas fa-search me-1"></i>Tìm
						kiếm
					</a> <a href="${pageContext.request.contextPath}/admin/book/add"
						class="btn btn-primary"> <i class="fa fa-plus me-1"></i>Thêm
						mới
					</a>
				</div>
			</div>

			<!-- Hiển thị thông báo thành công/lỗi -->
			<c:if test="${not empty message}">
				<div class="alert alert-success alert-dismissible fade show"
					role="alert">
					<i class="fa fa-check-circle me-2"></i>${message}
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<i class="fa fa-exclamation-triangle me-2"></i>${error}
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				</div>
			</c:if>

			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Hình bìa</th>
							<th>Thông tin sách</th>
							<th>ISBN</th>
							<th>Giá</th>
							<th>Số lượng</th>
							<th>Ngày xuất bản</th>
							<th>Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bookList}" var="book">
							<tr>
								<td class="align-middle">${book.bookId}</td>
								<td class="align-middle">
									<c:if test="${not empty book.coverImage}">
										<img
											src="${pageContext.request.contextPath}/image?fname=${book.coverImage}"
											alt="Book cover" class="book-image">
									</c:if>
									<c:if test="${empty book.coverImage}">
										<div
											class="book-image d-flex align-items-center justify-content-center bg-light text-muted">
											<i class="fa fa-book"></i>
										</div>
									</c:if>
								</td>
								<td class="align-middle">
									<div class="book-title">${book.title}</div>
									<c:if test="${not empty book.publisher}">
										<div class="book-publisher">
											<i class="fa fa-building me-1"></i>${book.publisher}
										</div>
									</c:if>
								</td>
								<td class="align-middle">${book.isbn}</td>
								<td class="align-middle">
									<c:if test="${not empty book.price}">
										<span class="book-price">${book.price}đ</span>
									</c:if>
								</td>
								<td class="align-middle">
									<span class="badge ${book.quantity > 0 ? 'bg-success' : 'bg-danger'}">
										${book.quantity}
									</span>
								</td>
								<td class="align-middle">${book.publishDate}</td>
								<td class="align-middle">
									<a
										href="${pageContext.request.contextPath}/admin/book/edit?bookId=${book.bookId}"
										class="btn btn-sm btn-outline-warning me-2">
										<i class="fa fa-edit"></i> Sửa
									</a>
									<a
										href="${pageContext.request.contextPath}/admin/book/delete?bookId=${book.bookId}"
										class="btn btn-sm btn-outline-danger"
										onclick="return confirm('Bạn có chắc chắn muốn xóa sách này?')">
										<i class="fa fa-trash"></i> Xóa
									</a>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty bookList}">
							<tr>
								<td colspan="8" class="text-center text-muted py-4">
									<i class="fa fa-inbox fa-3x mb-3 d-block"></i>
									Chưa có sách nào
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<!-- Phân trang -->
			<c:if test="${not empty totalPages && totalPages > 1}">
				<nav aria-label="Phân trang sách">
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
	<%@ include file="/common/admin/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
