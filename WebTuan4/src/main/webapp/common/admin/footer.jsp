<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<style>
  /* Glassmorphism cho footer */
  .footer-glassmorphism {
    background: rgba(25, 33, 80, 0.8) !important;
    backdrop-filter: blur(10px);
    box-shadow: 0 -8px 32px rgba(31, 38, 135, 0.2);
    border-top: 1px solid rgba(255, 255, 255, 0.18);
    margin-top: 3rem !important;
    position: relative;
    z-index: 1;
  }
  
  /* Hiệu ứng gradient overlay */
  .footer-glassmorphism::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(135deg, rgba(0, 229, 255, 0.1) 0%, rgba(106, 17, 203, 0.1) 100%);
    z-index: -1;
  }
  
  /* Hiệu ứng sáng cho thông tin liên hệ */
  .contact-info {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 1.5rem;
    margin-bottom: 1rem;
  }
  
  .contact-item {
    display: flex;
    align-items: center;
    transition: transform 0.3s ease;
  }
  
  .contact-item:hover {
    transform: translateY(-3px);
  }
  
  .contact-icon {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 36px;
    height: 36px;
    background: rgba(255, 255, 255, 0.1);
    border-radius: 50%;
    margin-right: 10px;
    transition: all 0.3s ease;
    color: #00e5ff;
    text-shadow: 0 0 10px rgba(0, 229, 255, 0.5);
  }
  
  .contact-item:hover .contact-icon {
    background: rgba(255, 255, 255, 0.2);
    transform: scale(1.1);
    box-shadow: 0 0 15px rgba(0, 229, 255, 0.5);
  }
  
  /* Hiệu ứng cho social media icons */
  .social-links {
    display: flex;
    justify-content: center;
    gap: 1rem;
  }
  
  .social-icon {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 40px;
    height: 40px;
    background: rgba(255, 255, 255, 0.1);
    border-radius: 50%;
    transition: all 0.3s ease;
    color: white;
    position: relative;
    overflow: hidden;
  }
  
  .social-icon:before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(45deg, rgba(0, 229, 255, 0.5), rgba(106, 17, 203, 0.5));
    opacity: 0;
    transition: opacity 0.3s ease;
    z-index: -1;
  }
  
  .social-icon:hover {
    transform: translateY(-3px) rotate(8deg);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
  }
  
  .social-icon:hover:before {
    opacity: 1;
  }
  
  .social-icon i {
    transition: all 0.3s ease;
  }
  
  .social-icon:hover i {
    transform: scale(1.2);
    text-shadow: 0 0 10px rgba(255, 255, 255, 0.8);
  }
  
  /* Facebook */
  .social-icon.facebook:hover {
    background: #3b5998;
  }
  
  /* Instagram */
  .social-icon.instagram:hover {
    background: linear-gradient(45deg, #f09433, #e6683c, #dc2743, #cc2366, #bc1888);
  }
  
  /* Twitter */
  .social-icon.twitter:hover {
    background: #1da1f2;
  }
  
  /* YouTube */
  .social-icon.youtube:hover {
    background: #ff0000;
  }
  
  /* Hiệu ứng cho copyright text */
  .copyright {
    font-size: 0.9rem;
    position: relative;
    color: rgba(255, 255, 255, 0.7);
    transition: color 0.3s ease;
    padding-top: 1rem;
    border-top: 1px solid rgba(255, 255, 255, 0.1);
  }
  
  .copyright:hover {
    color: #fff;
  }
  
  .copyright .shop-name {
    background: linear-gradient(45deg, #ffffff, #00e5ff);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    font-weight: 600;
  }
</style>

<!-- Footer -->
<footer class="footer-glassmorphism text-light py-4">
  <div class="container text-center">
    <div class="contact-info">
      <div class="contact-item">
        <span class="contact-icon"><i class="fas fa-phone-alt"></i></span>
        <span>Hotline: 0123 456 789</span>
      </div>
      <div class="contact-item">
        <span class="contact-icon"><i class="fas fa-envelope"></i></span>
        <span>Email: support@myshop.com</span>
      </div>
      <div class="contact-item">
        <span class="contact-icon"><i class="fas fa-map-marker-alt"></i></span>
        <span>Địa chỉ: TP. Hồ Chí Minh</span>
      </div>
    </div>
    
    <div class="social-links my-3">
      <a href="#" class="social-icon facebook" title="Facebook"><i class="fab fa-facebook-f"></i></a>
      <a href="#" class="social-icon instagram" title="Instagram"><i class="fab fa-instagram"></i></a>
      <a href="#" class="social-icon twitter" title="Twitter"><i class="fab fa-twitter"></i></a>
      <a href="#" class="social-icon youtube" title="YouTube"><i class="fab fa-youtube"></i></a>
    </div>
    
    <p class="copyright mb-0">&copy; 2025 <span class="shop-name">Bình Shop</span>. All rights reserved.</p>
  </div>
</footer>