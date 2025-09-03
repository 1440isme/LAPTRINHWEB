<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="vn.binh.model.Category" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách danh mục</title>
</head>
<body>
    <h2>Danh sách danh mục</h2>
    <a href="${pageContext.request.contextPath}/admin/category/add">+ Thêm mới</a>
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên danh mục</th>
                <th>Icon</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (categories != null) {
                    for (Category c : categories) {
            %>
                <tr>
                    <td><%= c.getCateid() %></td>
                    <td><%= c.getCatename() %></td>
                    <td>
                        <%
                            if (c.getIcon() != null) {
                        %>
                            <img src="${pageContext.request.contextPath}/uploads/<%= c.getIcon() %>" 
                                 alt="icon" style="width:60px;height:60px;">
                        <%
                            } else {
                        %>
                            <span>Không có</span>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/category/edit?id=<%= c.getCateid() %>">Sửa</a>
                        |
                        <a href="${pageContext.request.contextPath}/admin/category/delete?id=<%= c.getCateid() %>" 
                           onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>