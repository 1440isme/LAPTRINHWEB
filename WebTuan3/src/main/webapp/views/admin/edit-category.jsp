<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vn.binh.model.Category" %>
<%
    Category category = (Category) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa danh mục</title>
</head>
<body>
    <h2>Sửa danh mục</h2>
    <form action="${pageContext.request.contextPath}/admin/category/edit" 
          method="post" enctype="multipart/form-data">
        
        <input type="hidden" name="id" value="<%= category.getCateid() %>">

        <div>
            <label for="name">Tên danh mục:</label>
            <input type="text" id="name" name="name" value="<%= category.getCatename() %>" required>
        </div>

        <div>
            <label>Icon hiện tại:</label><br>
            <%
                if (category.getIcon() != null) {
            %>
                <img src="${pageContext.request.contextPath}/uploads/<%= category.getIcon() %>" 
                     alt="icon" style="width:100px;height:100px;">
            <%
                } else {
            %>
                <span>Chưa có icon</span>
            <%
                }
            %>
        </div>

        <div>
            <label for="icon">Chọn icon mới (nếu muốn thay):</label>
            <input type="file" id="icon" name="icon" accept="image/*">
        </div>

        <div>
            <button type="submit">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/admin/category/list">Quay lại</a>
        </div>
    </form>
</body>
</html>