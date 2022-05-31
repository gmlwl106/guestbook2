<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestBookVo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
    
<%
   	List<GuestBookVo> gbList = (List<GuestBookVo>) request.getAttribute("gbList");
   	
   	Date date = new Date();
   	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   	String regDate = simpleDate.format(date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 글쓰기 테이블 -->
	<form action="./gbc" method="get">
		<table border="1">
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" value="">
				</td>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password" value="">
				</td>
			</tr>
			<tr>
				<td colspan="4" >
					<textarea rows="10" cols="80" name="content" value=""></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="reg_date" value="<%=regDate%>">
					<input type="hidden" name="action" value="write">
					<button type="submit">확인</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	
	
	<!-- 글 출력 -->
	<%
	for(GuestBookVo g : gbList) {
	%>
		<table border="1">
			<tr>
				<td width="50px"><%=g.getNo() %></td>
				<td width="150px"><%=g.getName() %></td>
				<td width="300px"><%=g.getDate() %></td>
				<td>
					<a href="/guestbook2/gbc?action=deleteForm&del_no=<%=g.getNo()%>">삭제</a>
				</td>
			</tr>
			<tr>
				<td colspan="4" width="590px">
					<p>
						<%=g.getContent() %>
					</p>
				</td>
			</tr>
		</table>
		<br>
	<% } %>
</body>
</html>