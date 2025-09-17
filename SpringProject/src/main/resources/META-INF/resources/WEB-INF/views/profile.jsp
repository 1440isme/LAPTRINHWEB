<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<title>Thông tin cá nhân</title>
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

/* Avatar styling and hover effects */
.avatar-container {
  position: relative;
  display: inline-block;
}

.avatar-image {
  transition: all 0.3s ease;
}

.avatar-edit-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  opacity: 0;
  transition: opacity 0.3s ease;
  cursor: pointer;
}

.avatar-container:hover .avatar-edit-overlay {
  opacity: 1;
}

.avatar-edit-overlay label {
  margin: 0;
  cursor: pointer;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
</head>
<body>

<%@ include file="/common/web/header.jsp"%>
<!-- Profile Page Content -->
<div class="container my-5">
  <div class="row">
    <!-- Left Column - User Avatar -->
    <div class="col-md-4 mb-4">
      <div class="card shadow">
        <div class="card-body text-center">
          <div class="mb-4 position-relative avatar-container">
            <!-- Avatar Image -->
            <img 
              src="${pageContext.request.contextPath}/${empty user.images ? 'assets/images/default-avatar.png' : user.images}" 
              alt="${user.fullname}" 
              class="rounded-circle img-fluid shadow avatar-image" 
              style="width: 180px; height: 180px; object-fit: cover;" 
              id="previewAvatar"
            />
            
            <!-- Overlay Edit Button -->
            <div class="avatar-edit-overlay">
              <label for="avatarInput" class="btn btn-sm btn-light rounded-circle">
                <i class="fas fa-camera"></i>
              </label>
            </div>
          </div>
          
          <h4>${user.fullname}</h4>
          <p class="text-muted">${user.email}</p>
          <p class="text-muted">
            <i class="fas fa-phone me-2"></i>${empty user.phone ? "Chưa cập nhật" : user.phone}
          </p>
        </div>
      </div>
    </div>
    
    <!-- Right Column - Update Form -->
    <div class="col-md-8">
      <div class="card shadow">
        <div class="card-header bg-primary bg-gradient text-white">
          <h5 class="mb-0">
            <i class="fas fa-user-edit me-2"></i>Cập nhật thông tin cá nhân
          </h5>
        </div>
        <div class="card-body">
          <!-- Success Message -->
          <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
              <i class="fas fa-check-circle me-2"></i>${successMessage}
              <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
          </c:if>
          
          <!-- Error Message -->
          <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
              <i class="fas fa-exclamation-triangle me-2"></i>${errorMessage}
              <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
          </c:if>
        
          <form action="profile" method="post" enctype="multipart/form-data">
            <!-- Hidden file input for avatar -->
            <input type="file" id="avatarInput" name="avatar" class="d-none" accept="image/*">
            
            <!-- Username (readonly) -->
            <div class="mb-3">
              <label for="username" class="form-label">
                <i class="fas fa-user me-2"></i>Tên đăng nhập
              </label>
              <input 
                type="text" 
                class="form-control" 
                id="username" 
                value="${user.name}" 
                readonly 
              />
              <small class="text-muted">Tên đăng nhập không thể thay đổi</small>
            </div>
            
            <!-- Email (readonly) -->
            <div class="mb-3">
              <label for="email" class="form-label">
                <i class="fas fa-envelope me-2"></i>Email
              </label>
              <input 
                type="email" 
                class="form-control" 
                id="email" 
                value="${user.email}" 
                readonly 
              />
            </div>
            
            <!-- Full Name -->
            <div class="mb-3">
              <label for="fullname" class="form-label">
                <i class="fas fa-id-card me-2"></i>Họ và tên
              </label>
              <input 
                type="text" 
                class="form-control" 
                id="fullname" 
                name="fullname" 
                value="${user.fullname}" 
                required 
              />
            </div>
            
            <!-- Phone Number -->
            <div class="mb-3">
              <label for="phone" class="form-label">
                <i class="fas fa-phone-alt me-2"></i>Số điện thoại
              </label>
              <input 
                type="tel" 
                class="form-control" 
                id="phone" 
                name="phone" 
                value="${user.phone}" 
                pattern="[0-9]{10,11}" 
                title="Vui lòng nhập số điện thoại từ 10-11 chữ số"
              />
            </div>
            
            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-save me-2"></i>Lưu thay đổi
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="/common/web/footer.jsp"%>

<script>
// Preview avatar when file is selected
document.getElementById('avatarInput').addEventListener('change', function(e) {
    const file = e.target.files[0];
    if (file) {
        // Validate file type
        const validTypes = ['image/jpeg', 'image/jpg', 'image/png', 'image/gif'];
        if (!validTypes.includes(file.type)) {
            alert('Vui lòng chọn file ảnh hợp lệ (JPG, PNG, GIF)');
            this.value = '';
            return;
        }
        
        // Validate file size (max 5MB)
        const maxSize = 5 * 1024 * 1024; // 5MB
        if (file.size > maxSize) {
            alert('Kích thước file không được vượt quá 5MB');
            this.value = '';
            return;
        }
        
        // Create preview
        const reader = new FileReader();
        reader.onload = function(e) {
            const previewImg = document.getElementById('previewAvatar');
            previewImg.src = e.target.result;
            
            // Add a subtle animation effect
            previewImg.style.opacity = '0.7';
            setTimeout(() => {
                previewImg.style.opacity = '1';
            }, 100);
        };
        reader.readAsDataURL(file);
    }
});

// Show file name when selected
document.getElementById('avatarInput').addEventListener('change', function(e) {
    const file = e.target.files[0];
    if (file) {
        // Create or update file name display
        let fileNameDisplay = document.getElementById('fileNameDisplay');
        if (!fileNameDisplay) {
            fileNameDisplay = document.createElement('small');
            fileNameDisplay.id = 'fileNameDisplay';
            fileNameDisplay.className = 'text-muted mt-2 d-block';
            document.querySelector('.avatar-container').parentNode.appendChild(fileNameDisplay);
        }
        fileNameDisplay.innerHTML = `<i class="fas fa-file-image me-1"></i>Đã chọn: ${file.name}`;
    }
});
</script>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</html>