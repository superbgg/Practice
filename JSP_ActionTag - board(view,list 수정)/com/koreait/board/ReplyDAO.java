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
	   ArrayList<ReplyDTO> list = new ArrayList();
	   
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
	
	public ArrayList<ReplyDTO> view_reply(int idx) {
		try {
			conn = Dbconn.getConnection();
			if(conn!= null) {
				sql = "select re_idx, re_userid, re_content, re_regdate from tb_reply where re_boardidx="+idx+" order by re_idx desc";
				pstmt = conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					ReplyDTO reply = new ReplyDTO();
		//dto클래스 타입의 객체를 만들어서 객체에 데이터 저장후 해당 객체를 리스트에 추가
					reply.setReidx(rs.getInt("re_idx"));
					reply.setUserid(rs.getString("re_userid"));
					reply.setContent(rs.getString("re_content"));
					reply.setRegdate(rs.getString("re_regdate"));
					list.add(reply);
				}
				return list;
			}
			
		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return null;
	}
	
	public int reply_del(ReplyDTO reply) {
		try {
			conn = Dbconn.getConnection();
			sql = "delete from tb_reply where re_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getReidx());
			if(pstmt.executeUpdate() > 0) {
			   return 1;
			}
		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return 0;
	}
	
	public int replycnt(int idx) {
		try {
			conn = Dbconn.getConnection();
			if(conn!= null) {
				sql = "select count(re_idx) as replycnt from tb_reply where re_boardidx="+idx;
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				int replycnt=0;
				if(rs.next()) {
					replycnt= rs.getInt("replycnt");
				}
				return replycnt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
