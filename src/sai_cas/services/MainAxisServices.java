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
import java.net.URI;
import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Arrays;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import sai_cas.XMLCatalog;
import sai_cas.XMLCatalogException;
import sai_cas.VOTABLEFile.Votable;
import sai_cas.VOTABLEFile.VotableException;
import sai_cas.db.*;
import sai_cas.vo.ConeSearch;

public class MainAxisServices {
	static Logger logger = Logger.getLogger("sai_cas.MainAxisServices");
	
	/**
	 * 
	 * @param uriCatalog  -- The URI of the catalogue
	 * @throws RemoteException
	 * @return void
	 */
	
	public static void insertCatalogFromURI(String uriCatalog,
		String adminUser, String adminPassword) throws RemoteException
	{
		
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		URI uri;
		try
		{
			uri = new URI (uriCatalog);
		}
		catch(URISyntaxException e)
		{
			throw new RemoteException(e.getMessage());
		}
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection(adminUser, adminPassword);
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(uri);
			xmlc.insertCatalogueToDB(dbi);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());			
		}
		catch (XMLCatalogException e)
		{
			logger.error("Got an XMLCatalog exception... ", e);
			DBInterface.close(dbi, conn, false);		
			throw new RemoteException(e.getMessage());
		}
		catch (DBException e)
		{
			logger.error("Got an DB exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());
		}			
		DBInterface.close(dbi, conn);
	}
	
	/**
	 * The method is meant to be run as an admin ! user !! 
	 * @param catalogString -- The whole catalogue as a string
	 * @throws RemoteException
	 * @return void
	 */
	public static void insertCatalog(String catalogString,
		String adminUser,
		String adminPassword) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection(adminUser,
				adminPassword);
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(catalogString);
			xmlc.insertCatalogueToDB(dbi);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());			
		}
		catch (XMLCatalogException e)
		{
			logger.error("Got an XMLCatalog exception... ", e);
			DBInterface.close(dbi, conn, false);		
			throw new RemoteException(e.getMessage());
		}
		catch (DBException e)
		{
			logger.error("Got an DB exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
	}


	/**
	 * The method is meant to be run as an admin ! user !! 
	 * @param catalogString -- The whole catalogue as a string
	 * @throws RemoteException
	 * @return void
	 */
	public static void insertTable(String catalogString,
		String adminUser,
		String adminPassword) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection(adminUser,
				adminPassword);
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(catalogString);
			xmlc.insertTableToDB(dbi);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());			
		}
		catch (XMLCatalogException e)
		{
			logger.error("Got an XMLCatalog exception... ", e);
			DBInterface.close(dbi, conn, false);		
			throw new RemoteException(e.getMessage());
		}
		catch (DBException e)
		{
			logger.error("Got an DB exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
	}

	/**
	 * The method is meant to be run as an admin ! user !! 
	 * @param catalogString -- The whole catalogue as a string
	 * @throws RemoteException
	 * @return void
	 */
	public static String dumpCatalog(String catalogName,
		String adminUser,
		String adminPassword) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection(adminUser,
				adminPassword);
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(dbi, catalogName);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());			
		}
		catch (XMLCatalogException e)
		{
			logger.error("Got an XMLCatalog exception... ", e);
			DBInterface.close(dbi, conn, false);		
			throw new RemoteException(e.getMessage());
		}
		catch (DBException e)
		{
			logger.error("Got an DB exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
		return xmlc.toString();
	}




	/**
	 *
	 * @param catalogString -- The whole catalogue as a string
	 * @throws RemoteException
	 * @return void
	 */
	public static void insertCatalogFromVotable(String catalogString, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		Votable vot;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			vot = new Votable(catalogString);
			String userDataSchema = dbi.getUserDataSchemaName();
			vot.insertDataToDB(dbi, userDataSchema);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());			
		}
		catch (VotableException e)
		{
			logger.error("Got an Votable exception... ", e);
			DBInterface.close(dbi, conn, false);		
			throw new RemoteException(e.getMessage());
		}
		catch (DBException e)
		{
			logger.error("Got an DB exception... ", e);
			DBInterface.close(dbi, conn, false);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
	}


	/**
	 * 
	 * @return String -- the info about the catalogue
	 * @throws RemoteException
	 */
	public static void deleteCatalog(String catalog, String adminUser, String adminPassword)  throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(adminUser, adminPassword);
			dbi = new DBInterface(conn);// I do not put the user name here in 
										// the constructor, since that WS is not
										// supposed to be called by usual user
										// only by admin
			dbi.deleteCatalog(catalog);
		}
		catch(SQLException e)
		{
			logger.debug("Caught an exception... ", e);			
			throw new RemoteException(e.getMessage());		
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
	}


	/**
	 * 
	 * @return String -- the info about the catalogue
	 * @throws RemoteException
	 */
	public static void deleteTable(String catalog, String table, String user, String password)  throws java.rmi.RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			dbi.deleteTable(catalog, table);
		}
		catch(SQLException e)
		{
			logger.debug("Caught an exception... ", e);			
			throw new RemoteException(e.getMessage());		
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
	}

	
	/**
	 * 
	 * @return String[] -- the array of catalogues in the system
	 * @throws RemoteException
	 */
	public static String[] getCatalogNames() throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getCatalogNames();
			Arrays.sort(result);

		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
		return result;
	}

	/**
	 * 
	 * @return String[] -- the array of catalogues in the system
	 * @throws RemoteException
	 */
	public static String[] getCatalogNames(String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String []result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getCatalogNames();
			Arrays.sort(result);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
		return result;
	}


	/**
	 * 
	 * @return String -- the info about the catalogue
	 * @throws RemoteException
	 */
	public static String getCatalogInfo(String catalog) 
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getCatalogInfo(catalog);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}




	/**
	 * 
	 * @return String -- the info about the catalogue
	 * @throws RemoteException
	 */
	public static String getCatalogInfo(String catalog, String user, String password) 
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getCatalogInfo(catalog);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @return String -- the Description of the catalogue
	 * @throws RemoteException
	 */
	public static String getCatalogDescription(String catalog) 
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getCatalogDescription(catalog);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @return String -- the Description of the catalogue
	 * @throws RemoteException
	 */
	public static String getCatalogDescription(String catalog, String user, String password) 
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getCatalogDescription(catalog);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @return String[] the array of table names in the catalogue
	 * @throws RemoteException
	 */
	public static String[] getTableNames(String catalogName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getTableNames(catalogName);
			Arrays.sort(result);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @return String[] the array of table names in the catalogue
	 * @throws RemoteException
	 */
	public static String[] getTableNames(String catalogName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getTableNames(catalogName);
			Arrays.sort(result);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param catalogName
	 * @return String the table description in the catalogue
	 * @throws RemoteException
	 */
	public static String getTableDescription(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getTableDescription(catalogName, tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @return String the table description in the catalogue
	 * @throws RemoteException
	 */
	public static String getTableDescription(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getTableDescription(catalogName, tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}



	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] the array of column names in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnNames(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getColumnNames(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] the array of column names in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnNames(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getColumnNames(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] the array of infos about the columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnInfos(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getColumnInfos(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] the array of infos about the columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnInfos(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getColumnInfos(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] the array of descriptions of the columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnDescriptions(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getColumnDescriptions(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] the array of descriptions of the columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnDescriptions(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getColumnDescriptions(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] -- the array of units of columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnUnits(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getColumnUnits(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] -- the array of units of columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnUnits(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getColumnUnits(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] -- the array of UCD's of columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnUCDs(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getColumnUCDs(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[] -- the array of UCD's of columns in the table of the catalogue
	 * @throws RemoteException
	 */
	public static String[] getColumnUCDs(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getColumnUCDs(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	public static String[] getColumnDatatypes(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getColumnDatatypes(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	public static String[] getColumnDatatypes(String catalogName, String tableName, String user, String password) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getColumnDatatypes(catalogName,tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[][] -- the array of string pairs (indexName, indexDefinition) for given catalog and table
	 * @throws RemoteException
	 */
	public static String[][] getIndexes(String catalogName, String tableName) throws RemoteException
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[][] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getIndexes(catalogName, tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ",e);
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}



	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return the (approximate) row count of the table
	 * @throws RemoteException
	 */
	public static long getTableCount(String catalogName, String tableName)
	{
		Connection conn = null;
		DBInterface dbi = null;
		long result = -1;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getTableCount(catalogName, tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}

	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return the (approximate) row count of the table
	 * @throws RemoteException
	 */
	public static long getTableCount(String catalogName, String tableName, String user, String password)
	{
		Connection conn = null;
		DBInterface dbi = null;
		long result = -1;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getTableCount(catalogName, tableName);
		}
		catch(SQLException e)
		{
			logger.error("Caught an exception... ", e);			
		}
		finally
		{
			DBInterface.close(dbi, conn);
		}
		return result;
	}


	public static String getConeSearchAsString(String cat, String tab,
		double ra, double dec, double sr, String format) 
	{	
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		logger.debug("Invoking getConeSearchAsString without columnList");
		ConeSearch cs = new ConeSearch(pw, format, null);
		if (cs.initConeSearch(cat, tab, ra, dec, sr))
		{
			cs.setVerbosity(3, false);
			cs.printConeSearch();
		}
		return sw.toString();	
	}	

	public static String getConeSearchAsString(String cat, String tab,
		double ra, double dec, double sr, String format, String columnList[] ) 
	{	
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		logger.debug("Invoking getConeSearchAsString with columnList");
		ConeSearch cs = new ConeSearch(pw, format, null);
		if (cs.initConeSearch(cat, tab, ra, dec, sr))
		{
			if (columnList!=null)
			{
				cs.setColumnList(columnList, false);
			}
			else
			{
				cs.setVerbosity(3, false);			
			}
			cs.printConeSearch();
		}
		return sw.toString();	
	}	

	
	public static Calendar getDBLastChangedDate()
	{
		//return Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.set(2006,05,17);
		return c;
	}

}




