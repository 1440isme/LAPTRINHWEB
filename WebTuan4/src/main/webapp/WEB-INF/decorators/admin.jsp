<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
</head>
<body>



	<sitemesh:write property="body" />


	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
