<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idSearchOutPut</title>
<link href='https://fonts.googleapis.com/css?family=Pacifico'
	rel='stylesheet'>
<link rel="stylesheet" href="../resources/css/login_signup/idSearch.css?ver123">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
	function page() {
		window.location.href = "login";
	}
</script>
</head>
<body>
	<div class="SEARCH_FORM">
		<h1><a href="${contextPath}/mainpage/main" style="text-decoration: none;">People in Trip</a></h1>
		<h5>아이디찾기</h5>
	<form action="${contextPath}/login_signup/find_id" name="find_id"
			method="post">
		<input id="SID" type="text" value="${id}" disabled><br>
		<button type="button" class="SBTN" onclick="page()">
			<strong>로그인 페이지로 이동</strong>
	</form>
	</div>
</body>
</html>