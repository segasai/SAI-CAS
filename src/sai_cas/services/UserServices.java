/*
	   Copyright (C) 2005-2006 Sergey Koposov
   
    Author: Sergey Koposov
    Email: math@sai.msu.ru 
    http://lnfm1.sai.msu.ru/~math

    This file is part of SAI CAS

    SAI CAS is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    SAI CAS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SAI CAS; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/



package sai_cas.services;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import sai_cas.db.DBConnection;
import sai_cas.db.DBInterface;
;

public class UserServices 
{
	static Logger logger = Logger.getLogger("sai_cas.UserServices");
	
	public static void createNewUser(String adminLogin,
			String adminPassword, String newLogin, String newPassword,
			String email, String fullName) throws SQLException
	{
		Connection conn = DBConnection.getPooledPerUserConnection(adminLogin, adminPassword);
		//(login, password);
		DBInterface dbi = new DBInterface(conn);
		dbi.executeSimpleQuery("select cas_create_ordinary_user('" + 
			DBInterface.getInternalLoginName(newLogin) + "','" + newPassword +
			"','" + fullName + "','" + email + "');");
		dbi.close();
	}
	public static String[][] listAllUsers(String adminLogin,
			String adminPassword) throws SQLException
	{
		Connection conn = DBConnection.getPooledPerUserConnection(adminLogin,
			adminPassword);
		String [][] result;
		DBInterface dbi = new DBInterface(conn);
		result = dbi.getUserNamesAndEmails();
		dbi.close();
		return result;
	}

	
	public static void deleteUser(String adminLogin,
			String adminPassword, String login) throws SQLException
	{
		Connection conn = DBConnection.getPooledPerUserConnection(adminLogin,
			adminPassword);
		DBInterface dbi = new DBInterface(conn);
		dbi.executeSimpleQuery("select cas_delete_user('" +
			DBInterface.getInternalLoginName(login) + "');");
		dbi.close();
	}

	
	public static boolean validateLoginPassword(String login, String password)
	{
		try
		{
			Connection conn = DBConnection.getPooledPerUserConnection(login,
				password);
			conn.close();
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
}
