package com.koreait.board;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.db.Dbconn;

public class ReplyDAO {
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   ResultSet rs = null;
	   
	   String sql = "";
	   
	
	public int repl(ReplyDTO reply) {
		try {
			
			conn = Dbconn.getConnection();
			if(conn!= null) {
				 sql = "insert into tb_reply (re_userid, re_content, re_boardidx) values (?,?,?)";
				 pstmt = conn.prepareStatement(sql);
	             pstmt.setString(1, reply.getUserid());
	             pstmt.setString(2, reply.getContent());
	             pstmt.setInt(3, reply.getBoardidx());
	             if(pstmt.executeUpdate() > 0) {
	  			   return 1;
	  				}
				}
	  		}catch(Exception e) {
	  			e.printStackTrace();
	  		}
	  		return 0;
	  	}

}
