<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	request.setCharacterEncoding("UTF-8");
%>        
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.no-underline { text-decoration: none; }
	</style>
</head>
<body>
	<h1>
		<a href="${contextPath}/member/listMembers.do" class="no-underline">멤버 리스트</a><br/><br/><br/>
		<a href="#" class="no-underline">게시글 리스트</a><br/><br/><br/>
		<a href="#" class="no-underline">상품 리스트</a><br/><br/><br/>
	</h1>
</body>
</html>