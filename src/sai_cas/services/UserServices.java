package sai_cas.services;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import sai_cas.db.DBConnection;
import sai_cas.db.DBInterface;
;

public class UserServices 
{
	static Logger logger = Logger.getLogger("sai_cas.MainAxisServices");
	
	public static void createNewUser(String adminLogin,
			String adminPassword, String newLogin, String newPassword,
			String email, String fullName) throws SQLException
	{
		Connection conn = DBConnection.getPooledPerUserConnection(adminLogin, adminPassword);
		//(login, password);
		DBInterface dbi = new DBInterface(conn);
		dbi.executeSimpleQuery("select cas_create_ordinary_user('"+newLogin+"','"+newPassword+"','"+fullName+"','"+email+"');");
		dbi.close();
	}
	public static String[][] listAllUsers(String adminLogin,
			String adminPassword) throws SQLException
	{
		Connection conn = DBConnection.getPooledPerUserConnection(adminLogin, adminPassword);
		String [][] result;
		DBInterface dbi = new DBInterface(conn);
		result = dbi.getUserNames();
		dbi.close();
		return result;
	}

	
	public static void deleteUser(String adminLogin,
			String adminPassword, String login) throws SQLException
	{
		Connection conn = DBConnection.getPooledPerUserConnection(adminLogin, adminPassword);
		DBInterface dbi = new DBInterface(conn);
		dbi.executeSimpleQuery("select cas_delete_user('"+login+"');");
		dbi.close();
	}

	
	public static boolean validateLoginPassword(String login, String password)
	{
		try
		{
			Connection conn = DBConnection.getPooledPerUserConnection(login, password);
			conn.close();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
}
