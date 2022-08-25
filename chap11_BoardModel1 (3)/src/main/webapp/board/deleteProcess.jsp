<%@page import="org.apache.tomcat.JarScanFilter"%>
<%@page import="kr.co.ezenac.model1.board.BoardDAO"%>
<%@page import="kr.co.ezenac.model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="isLoggedIn.jsp" %>				<%-- 로그인 확인 --%>

<%
	String num = request.getParameter("num");				//삭제할 게시물의 num값 획득(일련번호)
	
	BoardDTO dto = new BoardDTO();
	BoardDAO dao = new BoardDAO(application);
	dto = dao.selectView(num);								//삭제할 게시물 일련번호에 해당하는 게시물 얻기
	
	//로그인된 사용자 id 얻기
	String sessionId = session.getAttribute("UserId").toString();
	
	int delResult = 0;
	
	//작성자가 본인인지 확인
	if (sessionId.equals(dto.getId())) {
		// 작성자 본인이면
		dto.setNum(num);
		delResult = dao.deletePost(dto);			//삭제!
		dao.close();
		
		//성공 여부 처리
		if (delResult == 1) {
			//성공시 목록 페이지로 이동
			JsFunction.alertLocation("삭제되었습니다.", "list.jsp", out);
		}
		else {
			//실패시 이전 페이지로 이동
			JsFunction.alertBack("삭제에 실패했습니다.", out);
		}
	}
	else {
		// 작성자 본인이 아니라면 이전페이지로 이동
		JsFunction.alertBack("본인만 삭제할 수 있습니다.", out);
		return;
	}
	
%>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    