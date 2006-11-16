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


package sai_cas.db;
import java.sql.*;
import java.util.Arrays;

import javax.sql.*;
import javax.naming.*;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.datasources.*;

import sai_cas.Parameters;

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

		/* By default we always return the notAutoCommited connection */ 
		try
		{
			conn.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			logger.warn("Failed to disable the AutoCommit mode...");
		}

		return conn;
	}

	public static Connection getPooledPerUserConnection() throws SQLException
	{
		String[] defaultDBUserPasswd = Parameters.getDefaultDBUserPasswd();
		return getPooledPerUserConnection(defaultDBUserPasswd[0], defaultDBUserPasswd[1]);
	}

	public static Connection getPooledPerUserAdminConnection() throws SQLException
	{
		String[] adminDBUserPasswd = Parameters.getAdminDBUserPasswd();
		return getPooledPerUserConnection(adminDBUserPasswd[0], adminDBUserPasswd[1]);
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
			conn = pupds.getConnection(DBInterface.getInternalLoginName(user),
				password);
		}
		catch (SQLException e)
		{
			logger.error("Failed to get the Pooled Connection");
			logger.error("The cause of that" + e.getMessage()+" "+e.getCause());
			throw e;
		}
		try
		{
			conn.setAutoCommit(false);
		}
		catch (SQLException e)
		{
			logger.warn("Failed to disable the AutoCommit mode...");
		}

		logger.info("The pooled DB connection successfully retrieved");
		return conn;
	}
	
  
	public static Connection getSimpleConnection() throws SQLException
	{
		Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas",
			"math", "");
		return conn;
	}
	
	public static Connection getSimpleConnection(String user, String password) throws SQLException
	{
		Connection conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cas",
			DBInterface.getInternalLoginName(user), password);
		return conn;
	}


}