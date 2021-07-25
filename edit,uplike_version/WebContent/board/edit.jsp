<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
<%@ page import="com.koreait.db.Dbconn"%>

<%	

		
		String b_title = "";
		String b_content = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String b_idx = request.getParameter("b_idx");
		String sql ="";
		

	
		 try {
		      conn = Dbconn.getConnection();
		      if (conn != null) {
		    	  sql = "select b_idx, b_userid, b_title, b_content from tb_board where b_idx=?";
		    	  pstmt = conn.prepareStatement(sql);
		    	  pstmt.setString(1, b_idx);
		    	  rs = pstmt.executeQuery();
		    	  if(rs.next()){
		    	
		    		  b_title=rs.getString("b_title");
		    		  b_content=rs.getString("b_content");
		    		    	  
		    	  }
		    	  
		      }
		}catch(Exception e){
			e.printStackTrace();
		}
		 	 
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>게시글 수정</h2>
	<form method="post" action="edit_ok.jsp?b_idx=<%=b_idx%>">
		<p>작성자 :  <%=session.getAttribute("userid")%></p>
		
		<p><label>제목 : <input type="text" name="b_title" value="<%=b_title%>"></label></p> 
		<p>내용</p>
			<p><textarea rows="5" cols="40" name="b_content"><%=b_content%></textarea></p>
		<p><input type="submit" value="수정 완료" > <input type="reset" value="다시작성"> <input type="button" value="리스트" onclick="location.href='list.jsp'">
		</p>
	</form>

</body>
</html>

