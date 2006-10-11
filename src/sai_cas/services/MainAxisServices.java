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
	 * @throws Exception
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
			xmlc.insertDataToDB(dbi);
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
	 * @throws Exception
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
			xmlc.insertDataToDB(dbi);
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
	 *
	 * @param catalogString -- The whole catalogue as a string
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
	 */
	public static String[] getTableNames(String catalogName) throws Exception
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			result = dbi.getTableNames(catalogName);
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
	 * @throws Exception
	 */
	public static String[] getTableNames(String catalogName, String user, String password) throws Exception
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection(user, password);
			dbi = new DBInterface(conn, user);
			result = dbi.getTableNames(catalogName);
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
	 * @throws Exception
	 */
	public static String getTableDescription(String catalogName, String tableName) throws Exception
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
	 * @throws Exception
	 */
	public static String getTableDescription(String catalogName, String tableName, String user, String password) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnNames(String catalogName, String tableName) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnNames(String catalogName, String tableName, String user, String password) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnInfos(String catalogName, String tableName) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnInfos(String catalogName, String tableName, String user, String password) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnDescriptions(String catalogName, String tableName) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnDescriptions(String catalogName, String tableName, String user, String password) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnUnits(String catalogName, String tableName) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnUnits(String catalogName, String tableName, String user, String password) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnUCDs(String catalogName, String tableName) throws Exception
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
	 * @throws Exception
	 */
	public static String[] getColumnUCDs(String catalogName, String tableName, String user, String password) throws Exception
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


	public static String[] getColumnDatatypes(String catalogName, String tableName) throws Exception
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


	public static String[] getColumnDatatypes(String catalogName, String tableName, String user, String password) throws Exception
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
	 * @throws Exception
	 */
	public static String[][] getIndexes(String catalogName, String tableName) throws Exception
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



	public static String getConeSearchAsString(String cat, String tab,
		double ra, double dec, double sr, String format, int verbosity) 
	{	
		StringBuffer sb;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ConeSearch cs = new ConeSearch(pw, format);
		if (cs.initConeSearch(cat, tab, ra, dec, sr))
		{
			cs.setVerbosity(verbosity,false);
			cs.printConeSearch();
		}
		return sw.toString();	
	}	

	public static String getConeSearchAsString(String cat, String tab,
		double ra, double dec, double sr, String format) 
	{	
		StringBuffer sb;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ConeSearch cs = new ConeSearch(pw, format);
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
		StringBuffer sb;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ConeSearch cs = new ConeSearch(pw, format);
		if (cs.initConeSearch(cat, tab, ra, dec, sr))
		{
			cs.setColumnList(columnList, false);
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




