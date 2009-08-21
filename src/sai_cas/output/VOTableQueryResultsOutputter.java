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


package sai_cas.output;

import java.io.PrintWriter;
import org.apache.log4j.Logger;
import sai_cas.db.DBInterface;
import sai_cas.Parameters;

public class VOTableQueryResultsOutputter implements QueryResultsOutputter
{
	static Logger logger = Logger.getLogger("sai_cas.output.VOTableQueryOutputter");

	String resource, table, resourceInfo, resourceDescription;

	public VOTableQueryResultsOutputter()
	{}
	
	private String xmlQuote(String str)
	{
		return "<![CDATA["+ str + "]]>";
	}
	
	public void printError(PrintWriter out, String message)
	{
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE VOTABLE SYSTEM \"http://us-vo.org/xml/VOTable.dtd\">");
		out.print("<VOTABLE version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		out.println("xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\">");
		out.print("<DESCRIPTION>");
		out.print(xmlQuote(message));
		out.println("</DESCRIPTION>");
		out.println("</VOTABLE>");
	}

	public void setResource(String s)
	{
		resource = s;
	}
	public void setTable(String t)
	{
		table = t;
	}
	public void setResourceDescription(String s)
	{
		resourceDescription = s;
	}
	public void setResourceInfo(String s)
	{
		resourceInfo = s;
	}

	public void print(PrintWriter out, DBInterface dbi)
	{

		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<?xml-stylesheet type=\"text/xsl\" href=\""+sai_cas.Parameters.getVOTableXSLURL()+"\"?>");
		out.println("<!DOCTYPE VOTABLE SYSTEM \"http://us-vo.org/xml/VOTable.dtd\">");
		out.print("<VOTABLE version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		out.println("xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\">");
//		String table = inputTable;
		
		try
		{

			out.println("<RESOURCE name=\"" +
				((resource == null) ? "" : resource) + "\">");
			out.println("<DESCRIPTION>" +
				xmlQuote((resourceDescription == null) ? "" : resourceDescription) +
				"</DESCRIPTION>");
			out.println("<INFO>"+
				xmlQuote((resourceInfo == null) ? "" : resourceInfo) +
				"</INFO>");

			out.println("<TABLE name=\"" + ((table==null)?"":table) + "\">");
				
			int ncols = dbi.qr.getColumnCount();
			
			logger.debug("Printing VOtable header ...");
			for (int i = 1; i <= ncols; i++)
			{
				String datatype = dbi.qr.getDatatype(i);
				
				out.println("<FIELD ID=\"col"+i+"\" "+
						"name=\"" + dbi.qr.getColumnName(i) +
						"\" ucd=\"" + dbi.qr.getUcd(i) +
						"\" unit=\"" + dbi.qr.getUnit(i) +
						"\" datatype=\"" + datatype + "\"" +
						(((datatype != null) && datatype.equals("char"))?" arraysize=\"*\" ":"") +
						">");
				/* !!!!!!!!!!!  TODO  !!!!!!!!!!!!!! 
				 * Here I inserted the stupid logic to set arraysize="*" 
				 * for every char field. Certainly the info about it should
				 * be stored in the database and retrieved from it. But
				 * currently I use arraysize="*" for everything
				 */
				
				out.print("<DESCRIPTION>");
				out.println(xmlQuote(dbi.qr.getColumnDescription(i)));
				out.print(xmlQuote(dbi.qr.getColumnInfo(i)));
				out.println("</DESCRIPTION>");
				/*
				out.print("<INFO>");
				out.println("</INFO>");
				*/

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
			out.println("<DESCRIPTION>ERROR:\nSQL Exception: " + e.getMessage() +
						"\nContact "+Parameters.getSupportEmail()+" in case of problems\n"+
						"</DESCRIPTION>");    
		}
		
		out.println("</VOTABLE>");
	}
}