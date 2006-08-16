package sai_cas.output;

import java.io.PrintWriter;
import org.apache.log4j.Logger;
import sai_cas.db.DBInterface;

public class VOTableQueryResultsOutputter implements QueryResultsOutputter
{
	static Logger logger = Logger.getLogger("sai_cas.output.VOTableQueryOutputter");

	String resource, table, resourceInfo, resourceDescription;

	public VOTableQueryResultsOutputter()
	{}
	
	
	public void printError(PrintWriter out, String message)
	{
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE VOTABLE SYSTEM \"http://us-vo.org/xml/VOTable.dtd\">");
		out.print("<VOTABLE version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
		out.println("xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\">");
		out.print("<DESCRIPTION>");
		message.replace("<","&lt;");
		message.replace(">","&gt;");
		out.print(message);
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
//			String catalog = dbi.qr.getBaseCatalogName(1);
//			String table = dbi.qr.getBaseTableName(1);
//			String catalogDescription = dbi.getCatalogDescription(catalog);
			
			out.println("<RESOURCE name=\"" + resource + "\">");
			out.println("<DESCRIPTION>"+resourceDescription+"</DESCRIPTION>");
			out.println("<INFO>"+resourceInfo+"</INFO>");
/*			Cone search result from catalogue: "+catalog +
						", table: "+ table+"\n" +
						"RA="+ra+" DEC="+dec+" SR="+rad+"\n"+
						"Contact saicas@sai.msu.ru in case of problems"+
						"</INFO>");
*/
			out.println("<TABLE name=\"" + table + "\">");
				
			int ncols = dbi.qr.getColumnCount();
			
			logger.debug("Printing VOtable header ...");
			for (int i = 1; i <= ncols; i++)
			{
				String datatype = dbi.qr.getDatatype(i);
				
				out.println("<FIELD name=\"" + dbi.qr.getColumnName(i) +
						"\" ucd=\"" + dbi.qr.getUcd(i) +
						"\" unit=\"" + dbi.qr.getUnit(i) +
						"\" datatype=\"" + datatype + "\"" +
						((datatype.equals("char"))?" arraysize=\"*\" ":"") +
						">");
				/* !!!!!!!!!!!  TODO  !!!!!!!!!!!!!! 
				 * Here I inserted the stupid logic to set arraysize="*" 
				 * for every char field. Certainly the info about it should
				 * be stored in the database and retrieved from it. But
				 * currently I use arraysize="*" for everything
				 */
				
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
			out.println("<DESCRIPTION>ERROR:\nSQL Exception: " + e.getMessage() +
						"\nContact saicas@sai.msu.ru in case of problems\n"+
						"</DESCRIPTION>");    
		}
		
		out.println("</VOTABLE>");
	}
}