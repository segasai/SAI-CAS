package sai_cas.services;

import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import sai_cas.XMLCatalog;
import sai_cas.XMLCatalogException;
import sai_cas.db.*;
import sai_cas.vo.ConeSearch;
import sai_cas.Votable;
import sai_cas.VotableException;

public class EditingServices {
	static Logger logger = Logger.getLogger("sai_cas.MainAxisServices");
	
	public static void setUCD(String catalog, String table, String column, 
		String ucd) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			ArrayList<String> alColumn  = new ArrayList<String>();
			alColumn.add(column);
			ArrayList<String> alUcd  = new ArrayList<String>();
			alUcd.add(column);
			
			dbi.setUcds (catalog, table, alColumn, alUcd);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
	} 
	public static void setUnit(String catalog, String table, String column, 
		String unit) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setUnit (catalog, table, column, unit);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}

	public static void setColumnName(String catalog, String table, String columnName, 
		String newColumnName) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setUnit (catalog, table, columnName, newColumnName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}


	public static void setColumnDescription(String catalog, String table, String column,
		String description) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setAttributeDescription(catalog, table, column, description);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}

	public static void setColumnInfo(String catalog, String table, String column,
		String description) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setAttributeDescription(catalog, table, column, description);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}


	public static void setTableDescription(String catalog, String table,
		String description) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setTableDescription(catalog, table, description);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}

	public static void setTableInfo(String catalog, String table,
		String info) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setTableDescription(catalog, table, info);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}


	public static void setCatalogDescription(String catalog,
		String description) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setCatalogDescription(catalog, description);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}

	public static void setCatalogInfo(String catalog, String table,
		String info) throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			dbi.setCatalogInfo(catalog, info);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);	
	}
}


