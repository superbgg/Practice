<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setCharacterEncoding("utf-8");
    %>
    
 <jsp:useBean id="student" class="com.koreait.member.Student"/>
 <!-- 
 <jsp:setProperty property="id" name="student"/>
 <jsp:setProperty property="name" name="student"/>
 <jsp:setProperty property="gender" name="student"/>
 <jsp:setProperty property="age" name="student"/>
     -->
      <jsp:setProperty property="*" name="student"/> <!-- 한꺼번에 데이터 받아옴 -->
      
      
      <!-- <jsp.setProperty property="age" param="mem_age" name="student"/>
      name값이 틀릴 경우 param을 이용해 전달 가능,  ------현재 name값    ----데이터 전달할 name값
      -->
      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바빈스 테스트 - 2</title>
	<p>번호 : <jsp:getProperty property="id" name="student"/></p> 
	<!-- set에서의 property 값이 get의 property에 자동으로 전달됨, 이때 name값이 같아야함-->
	<p>이름 : <jsp:getProperty property="name" name="student"/></p>
	<p>성별 : <jsp:getProperty property="gender" name="student"/></p>
	<p>나이 : <jsp:getProperty property="age" name="student"/></p>
</head>
<body>

</body>
</html>