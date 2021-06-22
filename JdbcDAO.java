package casino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class JdbcDAO {
	private static PoolDataSource _pd;
	
	static {
		_pd = PoolDataSourceFactory.getPoolDataSource();
		try {
			_pd.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
			_pd.setURL("jdbc:oracle:thin:@192.168.13.14:1521:xe");
			_pd.setUser("scott");
			_pd.setPassword("tiger");
			_pd.setInitialPoolSize(3);
			_pd.setMaxPoolSize(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public Connection getConnection() {
		Connection con = null;
		try {
			con = _pd.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con) {
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection con,PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Connection con,PreparedStatement ps,ResultSet re) {
		try {
			if(re!=null) re.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





















