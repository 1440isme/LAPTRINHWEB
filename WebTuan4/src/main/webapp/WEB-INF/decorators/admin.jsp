<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
<head>
    <title><decorator:title default="Admin Page"/></title>
</head>
<body>
    <jsp:include page="/common/admin/header.jsp"/>
    <jsp:include page="/common/admin/left.jsp"/>

    <div class="content">
        <decorator:body/>
    </div>

    <jsp:include page="/common/admin/footer.jsp"/>
</body>
</html>
