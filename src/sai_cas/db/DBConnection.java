package sai_cas.db;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
//import org.apache.tomcat.dbcp.dbcp.datasources.*;

public class DBConnection
{
  public static Connection getPooledConnection() throws SQLException, javax.naming.NamingException
  {
    Context initContext = new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:/comp/env");
    DataSource ds = (DataSource)envContext.lookup("jdbc/postgres");
    Connection conn = ds.getConnection();
//    this.conn = conn;
    return conn;
  }

 /* public static Connection getPooledConnection1() throws SQLException, javax.naming.NamingException
  {
    Context initContext = new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:/comp/env");
    //ConnectionPoolDataSource ds1 = (ConnectionPoolDataSource)envContext.lookup("jdbc/postgres1");       
    PerUserPoolDataSource ds2 = (PerUserPoolDataSource)envContext.lookup("jdbc/postgres2");
//    ds2.setDataSourceName("jdbc/postgres1");
    Connection conn = ds2.getConnection("koposov","");
//    this.conn = conn;
    return conn;
  }
  */
  
  public static Connection getSimpleConnection() throws SQLException
  {
    Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas","math","");
//    this.conn = conn;
    return conn;
  }

  public static Connection getSimpleConnection(String user, String password) throws SQLException
  {
    Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas",user,password);
//    this.conn = conn;
    return conn;
  }

/*  public void close() throws SQLException
  {
    if (conn!=null) conn.close();
  }
*/
//  Connection conn;

}