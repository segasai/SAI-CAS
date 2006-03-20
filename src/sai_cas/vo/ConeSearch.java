package sai_cas.vo;

import sai_cas.db.*;

//import javax.servlet.jsp.*;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The class printing the cone search results 
 * @author Sergey Koposov (math@sai.msu.ru) 
 */

public class ConeSearch
{
	static Logger logger = Logger.getLogger("sai_cas.ConeSearch");

	public static class ConeSearchException extends Exception
	{
		public ConeSearchException (String s)
		{
			super(s);
		}
	}
	public static void printVOTableConeSearch(PrintWriter out, String catalog,
			String inputTable, double ra, double dec, double rad)
	throws java.io.IOException
	{
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE VOTABLE SYSTEM \"http://us-vo.org/xml/VOTable.dtd\">");
		out.print("<VOTABLE version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		out.println("xsi:noNamespaceSchemaLocation=\"http://www.ivoa.net/xml/VOTable/v1.1\">");
		Connection conn = null;
		DBInterface dbi = null;
		String table = inputTable;
		
		try
		{
			//Connection conn = DBConnection.getSimpleConnection();
			logger.debug("Trying to get the Pooled Connection");
			conn = DBConnection.getPooledPerUserConnection();
			if (conn == null)
			{
				logger.error("The ConeSearchServlet failed to get the connection to the DB..");
				throw new ConeSearchException("Cannot connect to the database...\n Sorry");	
			}	
			dbi = new DBInterface (conn);
			
			if (!dbi.checkCatalogExists(catalog))
			{
				throw new ConeSearchException("Catalog " + catalog + " does not exist in our system");
			}
			if (table == null)
			{
				String[] tableArray = dbi.getTableNames(catalog);
				if (tableArray.length > 1)
				{
					throw new ConeSearchException("Current catalogue \""+
							catalog+
							"\" has more than one table, so you must specify," +
							" which one you want to query.\n"+
							"The catalogue contains following tables: "+Arrays.toString(tableArray));
				}
				table = tableArray[0];
			}
			else if (!dbi.checkTableExists(catalog, table))
			{
				throw new ConeSearchException("The table \"" + table + "\" does not exist in the catalogue \""+ catalog + "\"");				
			}

			String[] raDecArray=dbi.getRaDecColumns(catalog,table);
			if ((raDecArray[0]==null)||(raDecArray[1]==null))
			{
				throw new ConeSearchException("Selected table in the catalogue "+
						"do not have marked RA, DEC columns. Cannot run Cone Search in that case...");
			}
			String catalogDescription = dbi.getCatalogDescription(catalog);
			
			dbi.executeQuery("select * from " + catalog + "." + table + " where q3c_radial_query("+raDecArray[0] +","+raDecArray[1]+","+ra+","+dec+","+rad+")");
			out.println("<RESOURCE name=\"" + catalog + "\">");
			out.println("<DESCRIPTION>"+catalogDescription+"</DESCRIPTION>");
			out.println("<INFO>Cone search result from catalogue: "+catalog +
						", table: "+ table+"\n" +
						"RA="+ra+" DEC="+dec+" SR="+rad+"\n"+
						"Contact saicas@sai.msu.ru in case of problems"+
						"</INFO>");
			out.println("<TABLE name=\"" + table + "\">");
				
			int ncols = dbi.qr.getColumnCount();
			
			logger.debug("Printing VOtable header ...");
			for (int i = 1; i <= ncols; i++)
			{
				out.println("<FIELD name=\"" + dbi.qr.getColumnName(i) +
						"\" ucd=\"" + dbi.qr.getUcd(i) +
						"\" unit=\"" + dbi.qr.getUnit(i) +
						"\" datatype=\"" + dbi.qr.getDatatype(i) + "\">");
				
				out.print("<DESCRIPTION>");
				out.print(dbi.qr.getColumnDescription(i));
				out.println("</DESCRIPTION>");
				out.print("<INFO>");
				out.print(dbi.qr.getColumnInfo(i));
				out.println("</INFO>");

				out.println("</FIELD>");
			}
			out.println("<DATA>");
			out.println("<TABLEDATA>");
			logger.debug("Retrieving and outputting the data ...");
			
			String dataArray[];
			while (dbi.qr.next())
			{
				out.println("<TR>");
				dataArray = dbi.qr.getStringArray();
				for (int i = 0; i < ncols; i++)
				{
					String s = dataArray[i];
					if (s == null) s = "";
					out.print("<TD>" + s + "</TD>");
				}
				out.println("\n</TR>");        
			}
			out.println("</TABLEDATA>");
			out.println("</DATA>");
			out.println("</TABLE>");    
			out.println("</RESOURCE>");
			logger.debug("Finished outputting the data ...");

		}
		catch (java.sql.SQLException e)
		{
			logger.error("Got the SQL exception...",e);
			out.println("<DESCRIPTION>ERROR:\nSQL Exception: " + e.getMessage() + "</DESCRIPTION>");    
		}
		catch (ConeSearchException e)
		{
			out.println("<DESCRIPTION>ERROR: " + e.getMessage() + "</DESCRIPTION>");    
		}
		
		out.println("</VOTABLE>");
		
		try 
		{
			if (conn != null)
			{
				dbi.close();
			}
		}
		catch (SQLException e)
		{
/* 

 !!!!!!!!!!!!!!!!!!!! TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!
 
  Add the handler for the SQLException in the case of problems with .close() calls.
*/

		}    
	}
	
	public static void printVOTableConeSearchError(PrintWriter out, String error_message) 
		throws IOException
		{
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE VOTABLE SYSTEM \"http://us-vo.org/xml/VOTable.dtd\">");
		out.print("<VOTABLE version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		out.println("xsi:noNamespaceSchemaLocation=\"http://www.ivoa.net/xml/VOTable/v1.1\">");
		out.print("<DESCRIPTION>");
		out.println(error_message);
		out.println("Contact email: saicas@sai.msu.ru");
		out.println("</DESCRIPTION>");
		out.println("</VOTABLE>");
		} 
	
}