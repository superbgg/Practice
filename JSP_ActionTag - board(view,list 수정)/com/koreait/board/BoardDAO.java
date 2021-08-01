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
				if(board.getFile()!=null && !board.getFile().equals("")) {
				sql = "update tb_board set b_title=?, b_content=?, b_file=? where b_idx=?";
				pstmt = conn.prepareStatement(sql);
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
	
	public int like2(BoardDTO board) {
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
	
	public BoardDTO like(BoardDTO board) {
		try {
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
					return board;
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	public BoardDTO view(BoardDTO board) {
		try {
			conn = Dbconn.getConnection();
			if(conn!= null) {
				sql = "update tb_board set b_hit = b_hit + 1 where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,board.getIdx());
				pstmt.executeUpdate();
				
				sql = "select b_idx, b_userid, b_title, b_content, b_regdate, b_like, b_hit, b_file from tb_board where b_idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getIdx());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board.setUserid(rs.getString("b_userid"));
					board.setTitle(rs.getString("b_title"));
					board.setContent(rs.getString("b_content"));
					board.setRegdate(rs.getString("b_regdate"));
					board.setLike(rs.getInt("b_like"));
					board.setHit(rs.getInt("b_hit"));
					board.setFile(rs.getString("b_file"));
				}
				return board;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
		public int totalcnt() { //전체 게시글 가져오는 메소드
			try {
				conn = Dbconn.getConnection();
				if(conn!= null) {
					sql = "select count(b_idx) as total from tb_board";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					int totalCount =0;
					if(rs.next()){
						totalCount = rs.getInt("total");
					}
					return totalCount; //totalCount 변수 값을 반환 
				}
			}catch(Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
				
		public ArrayList<BoardDTO> page(int start, int pagePerCount) {
			try {
				conn = Dbconn.getConnection();
				if(conn!= null) {
					sql = "select b_idx, b_userid, b_title, b_regdate, b_hit, b_like, b_file from tb_board order by b_idx desc limit ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, pagePerCount);
					rs = pstmt.executeQuery();
				
					ArrayList<BoardDTO> boardList = new ArrayList();
					
					while(rs.next()){
						BoardDTO board = new BoardDTO();
						board.setIdx(rs.getInt("b_idx"));
						board.setUserid(rs.getString("b_userid"));
						board.setTitle(rs.getString("b_title"));
						board.setRegdate(rs.getString("b_regdate").substring(0, 10));
						board.setHit(rs.getInt("b_hit"));
						board.setLike(rs.getInt("b_like"));
						board.setFile(rs.getString("b_file"));
						boardList.add(board);		
					}
					return boardList;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
}
	
	

	
	

