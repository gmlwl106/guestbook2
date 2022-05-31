<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.GuestBookVo" %>

<%
   int delNo = Integer.parseInt(request.getParameter("del_no"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook2/gbc?action=delete" method="post">
		<input type="hidden" name="del_no" value="<%=delNo%>">
		비밀번호  <input type="password" name="del_pw" value="">
		<button type="submit">확인</button>
		<br>
		<a href="/guestbook2/gbc?action=list">메인으로 돌아가기</a>
	</form>

</body>
</html>