<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglib.jsp" %>

    <style>
      /* Glassmorphism và hiệu ứng hiện đại cho header */
      .navbar.glassmorphism {
        background: rgba(25, 33, 80, 0.8) !important;
        backdrop-filter: blur(10px);
        box-shadow: 0 8px 32px rgba(31, 38, 135, 0.2);
        border-bottom: 1px solid rgba(255, 255, 255, 0.18);
        transition: all 0.3s ease;
        position: relative !important;
        z-index: 1030 !important;
      }
      
      .navbar-brand {
        font-size: 1.5rem;
        position: relative;
        background: linear-gradient(45deg, #ffffff, #00e5ff);
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
        background: linear-gradient(90deg, #ffffff, #00e5ff);
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
        background: rgba(255, 255, 255, 0.1);
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
  <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
        <div class="container">
            <a class="navbar-brand" href="#">Bình Shop - Admin Panel</a>
            <div class="navbar-nav ms-auto">
                <span class="navbar-text me-3">Xin chào, ${sessionScope.account.fullname} (Admin)</span>
                <a class="btn btn-outline-light" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
            </div>
        </div>
    </nav>
    