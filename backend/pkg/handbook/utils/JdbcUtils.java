package pkg.handbook.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	
	private static ComboPooledDataSource ds = null;
	static{
		ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/handbook");
		ds.setUser("root");
		ds.setPassword("011010");
		ds.setAcquireIncrement(5);
		ds.setInitialPoolSize(5);
		ds.setMinPoolSize(3);
		ds.setMaxPoolSize(100);
		ds.setMaxStatements(20);
		ds.setMaxStatementsPerConnection(20);
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}
