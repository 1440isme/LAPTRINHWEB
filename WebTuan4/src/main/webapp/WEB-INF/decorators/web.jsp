<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
     <%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%@include file="/common/web/header.jsp"%>
 
 <hr>
 <sitemesh:write property = "body" />
 
</body>
</html>