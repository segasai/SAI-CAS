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

/**
 * The class printing the cone search results 
 * @author Sergey Koposov (math@sai.msu.ru) 
 */

public class ConeSearch
{


	public static class ConeSearchException extends Exception
	{
		public ConeSearchException (String s)
		{
			super(s);
		}
	}
	public static void printVOTableConeSearch(PrintWriter out, String catalog,
			String table, double ra, double dec, double rad)
	throws java.io.IOException
	{
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE VOTABLE SYSTEM \"http://us-vo.org/xml/VOTable.dtd\">");
		out.print("<VOTABLE version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		out.println("xsi:noNamespaceSchemaLocation=\"http://www.ivoa.net/xml/VOTable/VOTable/v1.1\">");
		Connection conn = null;
		DBInterface dbi = null;
		
		try
		{
			//Connection conn = DBConnection.getSimpleConnection();
			conn = DBConnection.getPooledConnection();
			if (conn == null)
			{
				throw new ConeSearchException("Cannot connect to the database...\n Sorry");	
			}	
			dbi = new DBInterface (conn);
			
			if (dbi.checkCatalogExists(catalog) && dbi.checkTableExists(catalog, table))
			{
				dbi.executeQuery("select * from " + catalog + "." + table + "");      
			}
			else 
			{
				throw new ConeSearchException("Catalog " + catalog + " or table " + table + " do not exist");
			}
			
			out.println("<RESOURCE name=\"" + catalog + "\">");
			out.println("<TABLE name=\"" + table + "\">");
				
				
			//if (dbi.qr==null) throw new SQLException("UUUUUUUU");    
			int ncols = dbi.qr.getColumnCount();
			
			for (int i = 1; i <= ncols; i++)
			{
				out.println("<FIELD name=\"" + dbi.qr.getColumnName(i) +
						"\" ucd=\"" + dbi.qr.getUcd(i) +
						"\" unit=\"" + dbi.qr.getUnit(i) +
						"\" datatype=\"" + dbi.qr.getDatatype(i) + "\">");
				
				out.print("<DESCRIPTION>");
				out.print(dbi.qr.getColumnDescription(i));
				out.println("</DESCRIPTION>");
				out.println("</FIELD>");
			}
			out.println("<TABLEDATA>");
			
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
				out.println("</TR>");        
			}
			out.println("</TABLEDATA>");
			out.println("</TABLE>");    
			
			out.println("</RESOURCE>");
		}
		catch (javax.naming.NamingException e)
		{
			out.println("<DESCRIPTION>ERROR:\nNaming Exception: " + e.getMessage() + "</DESCRIPTION>");
			StackTraceElement[] ste =  e.getStackTrace();
			List <StackTraceElement> stel = Arrays.asList(ste);
			out.println("<INFO>");
			for (StackTraceElement ste0 : stel)
			{
				out.println(ste0);
			}
			out.println("</INFO>");
		}
		catch (java.sql.SQLException e)
		{
			out.println("<DESCRIPTION>ERROR:\nSQL Exception: " + e.getMessage() + "</DESCRIPTION>");    
		}
		catch (ConeSearchException e)
		{
			out.println("<DESCRIPTION>ERROR: " + e.getMessage() + "</DESCRIPTION>");    
		}
		
		out.println("</VOTABLE>");
		
		try 
		{
			if (dbi != null)
			{
				if (dbi.qr != null) dbi.qr.close();
			}
			if (conn != null) { conn.commit(); conn.close();}
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
		out.println("xsi:noNamespaceSchemaLocation=\"http://www.ivoa.net/xml/VOTable/VOTable/v1.1\">");
		out.print("<DESCRIPTION>");
		out.println(error_message);
		out.println("</DESCRIPTION>");
		out.println("</VOTABLE>");
		} 
	
}