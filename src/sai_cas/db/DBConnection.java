package sai_cas.db;
import java.sql.*;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.sql.*;
import javax.naming.*;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.datasources.*;

public class DBConnection
{
	static Logger logger = Logger.getLogger("sai_cas.DBConnection");
	public static Connection getPooledConnection() 
	{
		Connection conn;
		DataSource ds;
		try
		{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/postgres");
		}
		catch (NamingException e)
		{
			logger.error("Cannot get the JNDI datasource");
			return null;
		}
		if (ds == null)
		{
			logger.error("Cannot get the JNDI datasource");
			return null;	
		}
		try
		{
			conn = ds.getConnection();
		}
		catch (SQLException e)
		{
			logger.error("Cannot get the pooled connection");
			return null;
		}
		if (conn == null)
		{
			logger.error("Cannot get the pooled connection");
			return null;
		}

		logger.info("The pooled DB connection successfully retrieved");

		//this.conn = conn;

		/* By default we always return the notAutoCommited connection */ 
		try
		{
			conn.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			logger.warn("Failed to disable the AutoCommit mode...")
		}

		return conn;
	}

	public static Connection getPooledPerUserConnection() throws SQLException
	{
		/** TODO
		 * This parameters should be defined somewhere in the global properties
		 */
		return getPooledPerUserConnection("cas_user","aspen");
	}
	
	public static Connection getPooledPerUserConnection(String user, String password) throws SQLException
	{
		Connection conn = null;
		PerUserPoolDataSource pupds;
		try
		{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			//Context envContext = initContext.getContext();
			pupds = (PerUserPoolDataSource)envContext.lookup("jdbc/postgresPerUser");
		}
		catch (NamingException e)
		{
			logger.error("Cannot get the JNDI datasource, catched exception ("+e.getMessage()+" "+e.getCause()+")");
			return null;
		}
		if (pupds == null)
		{
			logger.error("Cannot get the JNDI datasource");
			return null;	
		}

		logger.debug("Successfully got the PerUserPoolDataSource");

		try
		{
			//ds2.setDataSourceName("java://comp/env/jdbc/DriverAdapterCPDS");
			conn = pupds.getConnection(user, password);
		}
		catch (SQLException e)
		{
			logger.error("Failed to get the Pooled Connection");
			logger.error("The cause of that" + e.getMessage()+" "+e.getCause());
			return null;
		}
		try
		{
			conn.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			logger.warn("Failed to disable the AutoCommit mode...")
		}

		logger.info("The pooled DB connection successfully retrieved");
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