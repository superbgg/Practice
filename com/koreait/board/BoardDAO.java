package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.db.Dbconn;


	
public class BoardDAO {
		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   List<BoardDTO> boardList = new ArrayList();
		   String sql = "";
		   

	public List<BoardDTO> selectBoard(){
		try {
			conn=Dbconn.getConnection();
			sql="select b_idx,b_userid,b_title,b_regdate,b_hit,b_like,b_file from tb_board order by b_idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setIdx(rs.getInt("b_idx"));
				board.setUserid(rs.getString("b_userid"));
				board.setTitle(rs.getString("b_title"));
				board.setHit(rs.getInt("b_hit"));
				board.setLike(rs.getInt("b_like"));
				board.setRegdate(rs.getString("b_regdate"));
				board.setFile(rs.getString("b_file"));
				boardList.add(board);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		 return boardList;
	}
	
	public int del(BoardDTO board) {	
		try {
			conn = Dbconn.getConnection();
			sql = "delete from tb_board where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getIdx());
			if(pstmt.executeUpdate() > 0) {
			   return 1;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int write(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			  if (conn != null) {
			
			sql = "insert into tb_board(b_userid, b_title, b_content, b_file) values (?, ?, ?, ?)";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getUserid());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFile());
			if(pstmt.executeUpdate() > 0) {
				return 1;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public BoardDTO edit(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			  if (conn != null) {
					sql = "select b_title, b_content, b_file from tb_board where b_idx=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, board.getIdx());
					rs = pstmt.executeQuery();
					if(rs.next()) {
					
						board.setTitle(rs.getString("b_title"));
						board.setContent(rs.getString("b_content"));
						board.setFile(rs.getString("b_file"));
						return board;
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
	
	}
	
	
	public int edit_ok(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			if(conn!= null) {
				if(board.getFile()!=null && board.getFile()!=" ") {
				sql = "update tb_board set b_title=?, b_content=?, b_file=? where b_idx=?";
				pstmt.setString(1, board.getTitle());
				pstmt.setString(2, board.getContent());
				pstmt.setString(3, board.getFile());
				pstmt.setInt(4, board.getIdx());
				pstmt.executeUpdate();
				
				}else {
					sql = "update tb_board set b_title=?, b_content=? where b_idx=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getTitle());
					pstmt.setString(2, board.getContent());
					pstmt.setLong(3, board.getIdx());
					pstmt.executeUpdate();
					
				}
				return 1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int like(BoardDTO board) {
		
		try {
			int b_like = 0;
			conn = Dbconn.getConnection();
			if(conn!= null) {
				sql = "update tb_board set b_like = b_like + 1 where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setInt(1,board.getIdx());
				pstmt.executeUpdate();
				
				sql = "select b_like from tb_board where b_idx=?";
				pstmt  = conn.prepareStatement(sql);
				pstmt.setInt(1,board.getIdx());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					board.setLike(rs.getInt("b_like"));
				}
				return board.getLike();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}



	
	

	
	

