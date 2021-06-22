package casino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CasinoDAO extends JdbcDAO {
	
	private static CasinoDAO _cdao;
	
	private CasinoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_cdao = new CasinoDAO();
	}
	
	public static CasinoDAO getDao() {
		return  _cdao;
	}
	
	public int insertCustomer(CasinoDTO customer) {
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0;
		try {
			con = getConnection(); 
			
			String sql = "INSERT INTO CUSTOMER VALUES(?,?,?,?,?)"; 
			ps = con.prepareStatement(sql);
			ps.setInt(1, customer.getCno());
			ps.setString(2, customer.getCname());
			ps.setString(3, customer.getCbirth());
			ps.setString(4, customer.getCgrade());
			ps.setString(5, customer.getCenter());
			
			rows = ps.executeUpdate(); 
		} catch (SQLException e) {
			System.out.println("[에러]SQL 오류 = "+e.getMessage());
		} finally {
			close(con, ps);
		}
		return rows;
	}
	
	
	public int updateCutomer(CasinoDTO customer) {
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "UPDATE CUSTOMER SET CNAME=?,CBIRTH=?,CGRADE=?,CENTER=? WHERE CNO=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, customer.getCname());
			ps.setString(2, customer.getCbirth());
			ps.setString(3, customer.getCgrade());
			ps.setString(4, customer.getCenter());
			ps.setInt(5, customer.getCno());
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]UPDATE SQL 오류 = "+e.getMessage());
		} finally {
			close(con, ps);
		}
		return rows;
	}
	
	
	public int deleteCutomer(int no) {
		Connection con = null;
		PreparedStatement ps = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "DELETE FROM CUSTOMER WHERE CNO=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]DELETE SQL 오류 = "+e.getMessage());
		} finally {
			close(con, ps);
		}
		return rows;
	}
	
	public CasinoDTO selectNoCutomer(int no) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		CasinoDTO customer = null;
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM CUSTOMER WHERE CNO=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			
			re = ps.executeQuery();
			
			if(re.next()) {
				customer = new CasinoDTO();
				
				customer.setCno(re.getInt("Cno"));
				customer.setCname(re.getString("Cname"));
				customer.setCbirth(re.getString("Cbirth").substring(0,10));
				customer.setCgrade(re.getString("Cgrade"));
				customer.setCenter(re.getString("Center").substring(0,10));
			}
		} catch (SQLException e) {
			System.out.println("[에러]SELECT No Customer SQL 오류 = "+e.getMessage());
		} finally {
			close(con, ps, re);
		}
		return customer;
	}
	
	public List<CasinoDTO> selectNameCutomerList(int cnoTemp) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		
		List<CasinoDTO> customerList = new ArrayList<CasinoDTO>();
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM CUSTOMER WHERE CNO=? ORDER BY CNO";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cnoTemp);
			
			re = ps.executeQuery();
			
			while(re.next()) {
				CasinoDTO customer = new CasinoDTO();
				customer.setCno(re.getInt("cno"));
				customer.setCname(re.getString("cname"));
				customer.setCbirth(re.getString("Cbirth").substring(0,10));
				customer.setCgrade(re.getString("Cgrade"));
				customer.setCenter(re.getString("Center").substring(0,10));
				customerList.add(customer);
			}
		} catch (Exception e) {
			System.out.println("[에러]SELECT Name Customer SQL 오류 = "+e.getMessage());
		} finally {
			close(con, ps, re);
		}
		return customerList;
	}
	
	public List<CasinoDTO> selectAllCutomerList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet re = null;
		
		List<CasinoDTO> customerList = new ArrayList<CasinoDTO>();
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM CUSTOMER ORDER BY CNO";
			ps = con.prepareStatement(sql);
		
			
			re = ps.executeQuery();
			
			while(re.next()) {
				CasinoDTO customer = new CasinoDTO();
				customer.setCno(re.getInt("cno"));
				customer.setCname(re.getString("cname"));
				customer.setCbirth(re.getString("Cbirth").substring(0,10));
				customer.setCgrade(re.getString("Cgrade"));
				customer.setCenter(re.getString("Center").substring(0,10));
				customerList.add(customer);
			}
		} catch (Exception e) {
			System.out.println("[에러]SELECT ALL Customer SQL 오류 = "+e.getMessage());
		} finally {
			close(con, ps, re);
		}
		return customerList;
	}
}
