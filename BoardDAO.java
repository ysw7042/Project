package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.BoardDTO;

public class BoardDAO extends JdbcDAO {
	private static BoardDAO _dao;
	
	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new BoardDAO();
	}
	
	public static BoardDAO getDAO() {
		return _dao;
	}
	
	//BOARD ���̺� ����� ��ü �Խñۿ� ������ �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	// => �˻� ����� �����ϱ� ���� �Ű������� �˻� ���� ���� ���޹޾� ����
	//public int selectBoardCount() {
	public int selectBoardCount(String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			con=getConnection();
			
			//�޼ҵ��� �Ű������� ����� ���� ���� �ٸ� SQL ����� �����Ͽ� ����ǵ��� ����
			// => ���� SQL(Dynamic SQL)
			if(keyword.equals("")) {//�˻� ����� ������� ���� ���
				String sql="select count(*) from board";
				pstmt=con.prepareStatement(sql);
			} else {//�˻� ����� ����� ���
				String sql="select count(*) from board where "+search+" like '%'||?||'%' and status!=9";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[����]selectBoardCount() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			 close(con, pstmt, rs);
		}
		return count;
	}
		
	//�Խñ� ���� ���ȣ�� �Խñ� ���� ���ȣ�� ���޹޾� BOARD ���̺� ����� �Խñۿ���
	//��������� �������� ������ �Խñ� ����� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	//public List<BoardDTO> selectBoardList(int startRow, int endRow) {
	public List<BoardDTO> selectBoardList(int startRow, int endRow, String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardDTO> boardList=new ArrayList<BoardDTO>();
		try {
			con=getConnection();
			
			if(keyword.equals("")) {
				String sql="select * from (select rownum rn,temp.* from "
						+ "(select * from board order by ref desc,re_step) temp) "
						+ "where rn between ? and ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else {
				String sql="select * from (select rownum rn,temp.* from "
						+ "(select * from board where "+search+" like '%'||?||'%' and status!=9 "
						+ "order by ref desc,re_step) temp) where rn between ? and ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setRegDate(rs.getString("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReStep(rs.getInt("re_step"));
				board.setReLevel(rs.getInt("re_level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setStatus(rs.getInt("status"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("[����]selectBoardList() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return boardList;
	}
	
	//BOARD_SEQ ������ ��ü�� ������(�ڵ� ������)�� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	public int selectNextNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int nextNum=0;
		try {
			con=getConnection();
			
			String sql="select board_seq.nextval from dual";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				nextNum=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[����]selectNextNum() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return nextNum;
	}
	
	//��� ���� ������ ���޹޾� BOARD ���̺� ����� �Խñ� ���� �÷����� �����ϰ� �������� ������ ��ȯ�ϴ� �޼ҵ�
	// => �Խñ��� �׷��ȣ�� �θ���� �׷��ȣ�� ���� �Խñ��� �ۼ����� �θ���� �ۼ�������
	//    ū �Խñ��� �ۼ��� �÷����� 1 �����ǵ��� ����
	public int updateReStep(int ref, int reStep) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update board set re_step=re_step+1 where ref=? and re_step>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, reStep);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[����]updateReStep() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//�Խñ��� ���޹޾� BOARD ���̺� �����Ͽ� �����ϰ� �������� ������ ��ȯ�ϴ� �޼ҵ�
	public int insertBoard(BoardDTO board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into board values(?,?,?,?,sysdate,0,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board.getNum());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getSubject());
			pstmt.setInt(5, board.getRef());
			pstmt.setInt(6, board.getReStep());
			pstmt.setInt(7, board.getReLevel());
			pstmt.setString(8, board.getContent());
			pstmt.setString(9, board.getIp());
			pstmt.setInt(10, board.getStatus());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[����]insertBoard() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//�۹�ȣ�� ���޹޾� BOARD ���̺� ����� �ش� �۹�ȣ�� �Խñ��� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	public BoardDTO selectNumBoard(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDTO board=null;
		try {
			con=getConnection();
			
			String sql="select * from board where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setRegDate(rs.getString("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReStep(rs.getInt("re_step"));
				board.setReLevel(rs.getInt("re_level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			System.out.println("[����]selectNumBoard() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return board;
	}
	
	//�۹�ȣ�� ���޹޾� BOARD ���̺� ����� �ش� �۹�ȣ�� �Խñ� ��ȸ���� �����ϰ� �������� ������ ��ȯ�ϴ� �޼ҵ�
	public int updateReadCount(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update board set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[����]updateReadCount() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//�۹�ȣ�� ���޹޾� BOARD ���̺� ����� �ش� �۹�ȣ�� �Խñ��� ���� ó���ϰ� ó������ ������ ��ȯ�ϴ� �޼ҵ�
	public int deleteBoard(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update board set status=9 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[����]deleteBoard() �޼ҵ��� SQL ���� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
}












