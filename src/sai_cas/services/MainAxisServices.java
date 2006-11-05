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
import sai_cas.db.*;
import sai_cas.vo.ConeSearch;
import sai_cas.Votable;
import sai_cas.VotableException;

public class MainAxisServices {
	static Logger logger = Logger.getLogger("sai_cas.MainAxisServices");

	/**
	 * 
	 * @param catalogName
	 * @param tableName
	 * @return String[][] -- the array of string pairs (indexName, indexDefinition) for given catalog and table
	 * @throws Exception
	 */
	public static String[][] getIndexes(String catalogName, String tableName) throws XFault
	{
		Connection conn = null;
		DBInterface dbi = null;
		String result[][] = null;
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);


			result = dbi.getIndexes(catalogName, tableName);
		throw new XFault("");
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


	
	public static Calendar getDBLastChangedDate()
	{
		//return Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.set(2006,05,17);
		return c;
	}

}




