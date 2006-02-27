package sai_cas.services;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

import sai_cas.XMLCatalog;
import sai_cas.db.*;

public class MainAxisServices {
	public void insertCatalogfromURI(URI uriCatalog) throws Exception
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
	public void insertCatalog(String catalogString) throws Exception
	{
		Connection conn = DBConnection.getPooledConnection();
		DBInterface dbi = new DBInterface(conn);
		XMLCatalog xmlc = new XMLCatalog(catalogString);
		xmlc.insertDataToDB(dbi);			
	}
}
