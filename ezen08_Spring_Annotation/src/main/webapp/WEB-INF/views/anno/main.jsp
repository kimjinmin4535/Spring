<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과</title>
</head>
<body>
	<h1>2022년 소서 절기입니다!</h1>
	<h1>${msg}입니다!</h1>			<!-- 컨트롤러에서 넘긴 메시지를 출력 -->
</body>
</html>