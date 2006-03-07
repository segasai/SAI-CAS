package sai_cas.services;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import sai_cas.XMLCatalog;
import sai_cas.db.*;

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
		Connection conn = DBConnection.getPooledPerUserConnection();
		DBInterface dbi = new DBInterface(conn);
		XMLCatalog xmlc = new XMLCatalog(uriCatalog);
		xmlc.insertDataToDB(dbi);	
	}
	
	/**
	 *
	 * @param catalogString -- The whole catalogue as a string
	 * @throws Exception
	 * @return void
	 */
	public static void insertCatalog(String catalogString) throws Exception
	{
		Connection conn = DBConnection.getPooledPerUserConnection();
		DBInterface dbi = new DBInterface(conn);
		XMLCatalog xmlc = new XMLCatalog(catalogString);
		xmlc.insertDataToDB(dbi);
		conn.commit();
		conn.close();
	}
	
	/**
	 * 
	 * @return String[] -- the array of catalogues in the system
	 * @throws Exception
	 */
	public static String[] getCatalogNames() throws Exception
	{
		Connection conn = DBConnection.getPooledPerUserConnection();
		DBInterface dbi = new DBInterface(conn);
		String []result = dbi.getCatalogNames();
		conn.close();
		return result;
	}
	/**
	 * 
	 * @param catalogName
	 * @return the array of tables in the catalogue
	 * @throws Exception
	 */
	public static String[] getTableNames(String catalogName) throws Exception
	{
		Connection conn = DBConnection.getPooledPerUserConnection();
		DBInterface dbi = new DBInterface(conn);
		String result[] = dbi.getTableNames(catalogName);
		conn.close();
		return result;
	}
	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return the array of string pairs (indexName, indexDefinition) for give catalog and table
	 * @throws Exception
	 */
	public static String[][] getIndexes(String catalogName, String tableName) throws Exception
	{
		Connection conn = DBConnection.getPooledPerUserConnection();
		DBInterface dbi = new DBInterface(conn);
		String result[][] = dbi.getIndexes(catalogName,tableName);		
		conn.close();
		return result;
	}

}
