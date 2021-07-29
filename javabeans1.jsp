<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.koreait.member.MemberDTO" %>    
<%
	MemberDTO memberDTO = new MemberDTO();
	memberDTO.setUserid("apple");
	memberDTO.setUserpw("1234");
	memberDTO.setUsername("김사과");
	

%>    
               <!--객체명--->
<jsp:useBean id="mem" class="com.koreait.member.MemberDTO" scope="page"/>
<jsp:setProperty property="userid" name="mem" value="banana"/> <!-- 위의 id를 name값으로 -->
<jsp:setProperty property="userpw" name="mem" value="1111"/>
<jsp:setProperty property="username" name="mem" value="반하나"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바빈스 테스트 - 1</title>
</head>
<body>
	<h2>자바빈즈 테스트 - 1</h2>
	<hr/>
	<p>memberDTO의 아이디 : <%=memberDTO.getUserid()%></p>
	<p>memberDTO의 비밀번호 : <%=memberDTO.getUserpw()%></p>
	<p>memberDTO의  이름: <%=memberDTO.getUsername()%></p>
	<hr/>
	<p><jsp:getProperty property="userid" name="mem"/></p>
	<p><jsp:getProperty property="userpw" name="mem"/></p>
	<p><jsp:getProperty property="username" name="mem"/></p>
	
	
</body>
</html>