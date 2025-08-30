<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topbar</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="col-sm-6">
        <ul class="list-inline right-topbar pull-right">
            <c:choose>
                <c:when test="${sessionScope.account == null}">
                    <li class="list-inline-item">
                        <a href="${pageContext.request.contextPath }/login">Đăng nhập</a>
                    </li>
                    <li class="list-inline-item">
                        <a href="${pageContext.request.contextPath }/register">Đăng ký</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="list-inline-item">
                        <a href="${pageContext.request.contextPath }/member/myaccount">
                            ${sessionScope.account.fullName}
                        </a>
                    </li>
                    <li class="list-inline-item">
                        <a href="${pageContext.request.contextPath }/logout">Đăng xuất</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="list-inline-item">
                <i class="search fa fa-search search-button"></i>
            </li>
        </ul>
    </div>
</body>
</html>
