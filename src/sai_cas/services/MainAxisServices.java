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
		Connection conn = DBConnection.getPooledConnection();
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
		Connection conn = DBConnection.getPooledConnection();
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
		Connection conn = DBConnection.getPooledConnection();
		DBInterface dbi = new DBInterface(conn);
		String []result = dbi.getCatalogNames();
		conn.close();
		return result;
	}
	
}
