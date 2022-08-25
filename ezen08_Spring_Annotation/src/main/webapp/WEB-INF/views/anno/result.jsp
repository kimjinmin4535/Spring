<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>

	<h1>아이디: ${userId }</h1>
	<h1>이름 : ${userName }</h1>
	
<%-- 	
	<h1>아이디: ${info1.userId }</h1>		<!-- Map에 key로 접근하여 값을 출력함 -->
	<h1>이름 : ${info1.userName }</h1>	<!-- @ModelAttribute("info1")에서 지정한 이름으로 속성에 접근함 -->	
 --%>
 </body>
</html>