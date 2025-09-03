<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục mới</title>
</head>
<body>
    <h2>Thêm danh mục mới</h2>
    <form action="${pageContext.request.contextPath}/admin/category/add" 
          method="post" enctype="multipart/form-data">
        
        <div>
            <label for="name">Tên danh mục:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div>
            <label for="icon">Chọn icon:</label>
            <input type="file" id="icon" name="icon" accept="image/*">
        </div>

        <div>
            <button type="submit">Thêm mới</button>
            <a href="${pageContext.request.contextPath}/admin/category/list">Quay lại</a>
        </div>
    </form>
</body>
</html>