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
		Connection conn = DBConnection.getSimpleConnection(adminLogin, adminPassword);
		//(login, password);
		DBInterface dbi = new DBInterface(conn);
		dbi.executeSimpleQuery("select cas_create_ordinary_user('"+newLogin+"','"+newPassword+"','"+fullName+"','"+email+"');");
		dbi.close();
	}
}
