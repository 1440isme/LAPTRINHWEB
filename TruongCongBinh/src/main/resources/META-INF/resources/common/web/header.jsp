<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>


    <style>
      /* Glassmorphism và hiệu ứng hiện đại cho header */
      .navbar.glassmorphism {
        background: rgba(34, 197, 94, 0.85) !important;
        /* green-500 */
        backdrop-filter: blur(10px);
        box-shadow: 0 8px 32px rgba(34, 197, 94, 0.35);
        border-bottom: 1px solid rgba(255, 255, 255, 0.18);
        transition: all 0.3s ease;
        position: relative !important;
        z-index: 1030 !important;
      }

      .navbar-brand {
        font-size: 1.5rem;
        position: relative;
        background: linear-gradient(45deg, #ffffff, #34d399);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        font-weight: 800;
        letter-spacing: 1px;
        text-shadow: 0 0 10px rgba(0, 229, 255, 0.5);
      }

      .navbar-brand:after {
        content: '';
        position: absolute;
        bottom: -5px;
        left: 0;
        width: 100%;
        height: 3px;
        background: linear-gradient(90deg, #ffffff, #34d399);
        transform: scaleX(0);
        transition: transform 0.3s ease;
        transform-origin: right;
      }

      .navbar-brand:hover:after {
        transform: scaleX(1);
        transform-origin: left;
      }



      .nav-item {
        margin: 0 5px;
      }

      .nav-link {
        font-weight: 500;
        padding: 8px 16px !important;
        border-radius: 8px;
        transition: all 0.3s ease;
        position: relative;
        z-index: 1;
      }

      .nav-link:before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(255, 255, 255, 0.15);
        border-radius: 8px;
        transform: scale(0);
        transition: transform 0.3s ease;
        z-index: -1;
      }

      .nav-link:hover:before,
      .nav-link.active:before {
        transform: scale(1);
      }

      .nav-link:hover {
        color: #fff !important;
        transform: translateY(-2px);
      }

      .nav-link.active {
        background: rgba(255, 255, 255, 0.2);
        color: #fff !important;
      }

      /* Dropdown styling */
      .dropdown {
        position: relative;
        z-index: 1050 !important;
      }

      .dropdown-toggle {
        border: 1px solid rgba(255, 255, 255, 0.2);
        border-radius: 12px;
        padding: 8px 16px;
        transition: all 0.3s ease;
        background: rgba(255, 255, 255, 0.1);
      }

      .dropdown-toggle:hover {
        background: rgba(255, 255, 255, 0.2);
        transform: translateY(-2px);
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
      }

      .dropdown-menu {
        background: rgba(255, 255, 255, 0.95) !important;
        backdrop-filter: blur(15px);
        border: 1px solid rgba(255, 255, 255, 0.3);
        border-radius: 12px;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.25);
        overflow: hidden;
        transform-origin: top center;
        animation: dropdownAnimation 0.3s ease;
        z-index: 9999 !important;
        position: absolute !important;
        min-width: 200px;
      }

      .dropdown-menu.show {
        z-index: 9999 !important;
      }

      .dropdown-item {
        padding: 10px 20px;
        border-radius: 8px;
        margin: 5px;
        transition: all 0.3s ease;
      }

      .dropdown-item:hover {
        background: linear-gradient(45deg, rgba(106, 17, 203, 0.1), rgba(37, 117, 252, 0.1));
        transform: translateX(5px);
      }

      .dropdown-item i {
        transition: transform 0.3s ease;
      }

      .dropdown-item:hover i {
        transform: scale(1.2);
      }

      .dropdown-item.text-danger:hover {
        background: linear-gradient(45deg, rgba(255, 0, 0, 0.1), rgba(255, 99, 71, 0.1));
      }

      /* Login/Register buttons */
      .btn-outline-light {
        border-radius: 12px;
        border: 1px solid rgba(255, 255, 255, 0.2);
        background: rgba(255, 255, 255, 0.1);
        transition: all 0.3s ease;
      }

      .btn-outline-light:hover {
        background: rgba(255, 255, 255, 0.2);
        transform: translateY(-3px);
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
      }

      .btn-primary {
        border-radius: 12px;
        background: linear-gradient(45deg, #6a11cb, #2575fc);
        border: none;
        transition: all 0.3s ease;
      }

      .btn-primary:hover {
        background: linear-gradient(45deg, #5a0cb5, #1565eb);
        transform: translateY(-3px);
        box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
      }
    </style>

    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark glassmorphism">
      <div class="container">

        <!-- Mobile toggle button -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNav">
          <ul class="navbar-nav me-auto align-items-lg-center">
            <li class="nav-item">
              <a class="nav-link ${pageContext.request.servletPath.contains('/home') ? 'active' : ''}"
                href="${pageContext.request.contextPath}/home">
                <i class="fas fa-home me-1"></i> Trang Chủ
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link ${pageContext.request.servletPath.contains('/products') ? 'active' : ''}"
                href="${pageContext.request.contextPath}/products">
                <i class="fas fa-box me-1"></i> Sản Phẩm
              </a>
            </li>
            <!-- Search -->
            <li class="nav-item ms-lg-3 flex-grow-1" style="min-width: 260px;">
              <form class="d-flex" action="${pageContext.request.contextPath}/products" method="get">
                <div class="input-group">
                  <input class="form-control form-control-sm" type="text" name="keyword"
                    placeholder="Tìm kiếm sản phẩm..." />
                  <button class="btn btn-primary btn-sm" type="submit"><i class="fas fa-search"></i></button>
                </div>
              </form>
            </li>
          </ul>

          <!-- Kiểm tra user đăng nhập -->
          <c:choose>
            <c:when test="${not empty sessionScope.account}">
              <ul class="navbar-nav ms-auto align-items-lg-center">
                <!-- Dark mode toggle -->
                <li class="nav-item me-2">
                  <button id="themeToggle" class="btn btn-outline-light btn-sm" type="button" title="Chuyển giao diện">
                    <i class="fas fa-moon"></i>
                  </button>
                </li>
                <!-- Cart placeholder -->
                <li class="nav-item me-2 d-none d-lg-block">
                  <a class="nav-link" href="#"><i class="fas fa-shopping-cart"></i></a>
                </li>
                <!-- User dropdown -->
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
                    <i class="fas fa-user-circle me-1"></i>
                    <c:choose>
                      <c:when test="${not empty sessionScope.account.fullname}">${sessionScope.account.fullname}
                      </c:when>
                      <c:otherwise>${sessionScope.account.email}</c:otherwise>
                    </c:choose>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                    <c:if test="${sessionScope.account.roleId == 1}">
                      <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/home"><i
                            class="fas fa-cogs me-2"></i>Trang quản trị</a></li>
                      <li>
                        <hr class="dropdown-divider" />
                      </li>
                    </c:if>
                    <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout"><i
                          class="fas fa-sign-out-alt me-2"></i>Đăng xuất</a></li>
                  </ul>
                </li>
              </ul>
            </c:when>
            <c:otherwise>
              <ul class="navbar-nav ms-auto align-items-lg-center">
                <li class="nav-item me-2">
                  <button id="themeToggle" class="btn btn-outline-light btn-sm" type="button" title="Chuyển giao diện">
                    <i class="fas fa-moon"></i>
                  </button>
                </li>
                <li class="nav-item">
                  <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-light me-2 nav-link">
                    <i class="fas fa-sign-in-alt me-1"></i> Đăng nhập
                  </a>
                </li>
                <li class="nav-item">
                  <a href="${pageContext.request.contextPath}/register" class="btn btn-primary nav-link text-white">
                    <i class="fas fa-user-plus me-1"></i> Đăng ký
                  </a>
                </li>
              </ul>
            </c:otherwise>
          </c:choose>
          </ul>
        </div>
      </div>
    </nav>

    <script>
      (function () {
        const toggle = document.getElementById('themeToggle');
        if (!toggle) return;
        const root = document.documentElement;
        let dark = false;
        toggle.addEventListener('click', function () {
          dark = !dark;
          if (dark) {
            root.style.setProperty('--bs-body-bg', '#0b1220');
            root.style.setProperty('--bs-body-color', '#e2e8f0');
            document.querySelector('.navbar')?.classList.add('navbar-dark');
          } else {
            root.style.removeProperty('--bs-body-bg');
            root.style.removeProperty('--bs-body-color');
          }
        });
      })();
    </script>