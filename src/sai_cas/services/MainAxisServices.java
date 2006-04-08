package sai_cas.services;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import sai_cas.XMLCatalog;
import sai_cas.XMLCatalogException;
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
	
	public static void insertCatalogfromURI(URI uriCatalog) throws Exception
	{
		
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(uriCatalog);
			xmlc.insertDataToDB(dbi);
			DBInterface.close(dbi, conn);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
		}
			
	}
	
	/**
	 *
	 * @param catalogString -- The whole catalogue as a string
	 * @throws Exception
	 * @return void
	 */
	public static void insertCatalog(String catalogString) throws SQLException
	{
		Connection conn = null;
		DBInterface dbi = null;
		XMLCatalog xmlc;
		
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			xmlc = new XMLCatalog(catalogString);
			xmlc.insertDataToDB(dbi);
			DBInterface.close(dbi, conn);
		}
		catch (SQLException e)
		{
			logger.error("Got an exception... ", e);
			DBInterface.close(dbi, conn, false);
		}
		catch (XMLCatalogException e)
		{
			logger.error("Got an XMLCatalog exception... ", e);
			DBInterface.close(dbi, conn, false);
		
		}
		catch (DBException e)
		{
			logger.error("Got an DB exception... ", e);
			DBInterface.close(dbi, conn, false);
		}
	}
	
	/**
	 * 
	 * @return String[] -- the array of catalogues in the system
	 * @throws Exception
	 */
	public static String[] getCatalogNames() 
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



	public static String getConeSearch(String cat, String tab, double ra, double dec, double sr) 
	{	
		StringBuffer sb;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try
		{
			ConeSearch.printVOTableConeSearch(pw, cat, tab, ra, dec, sr);
		}
		catch (IOException e)
		{
			logger.error("Got an exception... ", e);
		}
		return sw.toString();	
	}
}




