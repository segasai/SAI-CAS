package sai_cas.db;
import java.sql.*;
import java.util.Arrays;

import javax.sql.*;
import javax.naming.*;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.datasources.*;

public class DBConnection
{
	static Logger logger = Logger.getLogger("sai_cas.DBConnection");
	public static Connection getPooledConnection() throws SQLException, javax.naming.NamingException
	{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/postgres");
		if (ds == null)
		{
			logger.error("Cannot get the JNDI datasource");
		}
		Connection conn = ds.getConnection();
		if (conn == null)
		{
			logger.error("Cannot get the pooled connection");
		}
		else 
		{
			logger.info("The pooled DB connection successfully retrieved");
		}
		//this.conn = conn;
		return conn;
	}

	public static Connection getPooledConnection1() throws SQLException, javax.naming.NamingException
	{
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		//ConnectionPoolDataSource ds1 = (ConnectionPoolDataSource)envContext.lookup("jdbc/postgres1");       
		PerUserPoolDataSource ds2 = (PerUserPoolDataSource)envContext.lookup("jdbc/postgresPerUser");
		if (ds2 == null)
		{
			logger.error("Cannot get the JNDI datasource");
		}
		else
		{
			logger.debug("Successfully got the PerUserPoolDataSource");
		}
		//ds2.setDataSourceName("jdbc/postgres1");
		Connection conn = null;
		try
		{
//			ds2.setDataSourceName("java://comp/env/jdbc/DriverAdapterCPDS");
			conn = ds2.getConnection("math","");
		}
		catch (SQLException e)
		{
//			logger.error(Arrays.toString(e.getStackTrace()));
			logger.error(e.getStackTrace()[0]);
			logger.error(e.getMessage());
			logger.error(e.getCause());

		}
		//this.conn = conn;
		return conn;
	}
	
  
	public static Connection getSimpleConnection() throws SQLException
	{
		Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas","math","");
		//this.conn = conn;
		return conn;
	}
	
	public static Connection getSimpleConnection(String user, String password) throws SQLException
	{
		Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas",user,password);
		//this.conn = conn;
		return conn;
	}

/*  public void close() throws SQLException
  {
    if (conn!=null) conn.close();
  }
*/
//  Connection conn;

}