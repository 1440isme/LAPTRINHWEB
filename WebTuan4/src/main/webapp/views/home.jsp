<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang home của user</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

<style>
.hero-section {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: white;
	padding: 80px 0;
	text-align: center;
	margin-bottom: 50px;
}

.feature-card {
	transition: transform 0.3s ease;
}

.feature-card:hover {
	transform: translateY(-5px);
}
</style>
</head>
<body>
  
    	<%@ include file="/common/web/header.jsp"%> 
	
	<!-- Body -->
	<div class="container my-5">
	
		<h2 class="text-center mb-4">Sản phẩm nổi bật</h2>
		<div class="row g-4">
			<!-- Product 1 -->
			<div class="col-md-4">
				<div class="card h-100 shadow-sm">
					<img src="https://via.placeholder.com/300x200" class="card-img-top"
						alt="Sản phẩm 1">
					<div class="card-body">
						<h5 class="card-title">Sản phẩm 1</h5>
						<p class="card-text text-muted">Mô tả ngắn gọn về sản phẩm.</p>
						<p class="fw-bold text-danger">250.000đ</p>
						<a href="#" class="btn btn-primary"><i
							class="fas fa-cart-plus"></i> Thêm vào giỏ</a>
					</div>
				</div>
			</div>
			<!-- Product 2 -->
			<div class="col-md-4">
				<div class="card h-100 shadow-sm">
					<img src="https://via.placeholder.com/300x200" class="card-img-top"
						alt="Sản phẩm 2">
					<div class="card-body">
						<h5 class="card-title">Sản phẩm 2</h5>
						<p class="card-text text-muted">Mô tả ngắn gọn về sản phẩm.</p>
						<p class="fw-bold text-danger">350.000đ</p>
						<a href="#" class="btn btn-primary"><i
							class="fas fa-cart-plus"></i> Thêm vào giỏ</a>
					</div>
				</div>
			</div>
			<!-- Product 3 -->
			<div class="col-md-4">
				<div class="card h-100 shadow-sm">
					<img src="https://via.placeholder.com/300x200" class="card-img-top"
						alt="Sản phẩm 3">
					<div class="card-body">
						<h5 class="card-title">Sản phẩm 3</h5>
						<p class="card-text text-muted">Mô tả ngắn gọn về sản phẩm.</p>
						<p class="fw-bold text-danger">450.000đ</p>
						<a href="#" class="btn btn-primary"><i
							class="fas fa-cart-plus"></i> Thêm vào giỏ</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<%@ include file="/common/web/footer.jsp"%>
</body>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</html>