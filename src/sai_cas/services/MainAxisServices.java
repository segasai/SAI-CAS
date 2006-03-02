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
	static Logger logger = Logger.getLogger("MainAxisServices");
	public static void insertCatalogfromURI(URI uriCatalog) throws Exception
	/*
	 * @params uriCatalog the 
	 * 
	 */
	{
		Connection conn = DBConnection.getPooledConnection();
		DBInterface dbi = new DBInterface(conn);
		XMLCatalog xmlc = new XMLCatalog(uriCatalog);
		xmlc.insertDataToDB(dbi);	
	}
	public static void insertCatalog(String catalogString) throws Exception
	{
		Connection conn = DBConnection.getPooledConnection();
		DBInterface dbi = new DBInterface(conn);
		logger.info("XXXXXXXXXXX");
		XMLCatalog xmlc = new XMLCatalog(catalogString);
		logger.info("YYYYYYYYYYY");
		xmlc.insertDataToDB(dbi);			
	}
}
