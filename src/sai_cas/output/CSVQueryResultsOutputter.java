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
import sai_cas.Parameters;;

public class CSVQueryResultsOutputter implements QueryResultsOutputter
{
	static Logger logger = Logger.getLogger("sai_cas.output.CVSQueryResultsOutputter");

	String resource, table, resourceInfo, resourceDescription, nulls;
	String delimiter = ',';	
	public CSVQueryResultsOutputter(String nulls)
	{
		this.nulls = nulls;
	}
		
	public void printError(PrintWriter out, String message)
	{
		String messageCorrected = "#" + message.replace("\n","\n#");
		out.print("#"+messageCorrected);
	}

	public void setDelimiter(String delimiter)
	{
		if (delimiter!=null)
		{
			this.delimiter = delimiter;
		}
	}

	public void print(PrintWriter out, DBInterface dbi)
	{

		
		try
		{	
			int ncols = dbi.qr.getColumnCount();
			
			logger.debug("Printing CSV header ...");
			for (int i = 1; i <= ncols; i++)
			{
				String datatype = dbi.qr.getDatatype(i);
				
				out.print(dbi.qr.getColumnName(i));
				if (i != ncols)
				{
					out.print(delimiter);
				}
			}
			out.println("");
			logger.debug("Retrieving and outputting the data ...");
			
			String dataArray[];
			while (dbi.qr.next())
			{
				dataArray = dbi.qr.getStringArray();
				for (int i = 0; i < ncols; i++)
				{
					String s = dataArray[i];
					if (s == null)
					{
						if (nulls ==null)
						{	s = "";
						}
						else
						{
							s=nulls;
						}
					}
					if ((s.indexOf(delimiter)!=-1)||(s.indexOf("\n")!=-1)||(s.indexOf("\"")!=-1))
					{
						s=s.replace("\"","\"\"");
						s="\""+s+"\"";
					}
					if (i != 0) 
					{
						out.print(delimiter + s);
					}
					else
					{
						out.print(s);					
					}	
				}
				out.println("");        
			}
			logger.debug("Finished outputting the data ...");

		}
		catch (java.sql.SQLException e)
		{
			logger.error("Got the SQL exception...",e);
			/** TODO
			 *  I'm not really sure that I should report errors here...
			 */
			out.print("#ERROR:\nSQL Exception: " + e.getMessage() +
						"\nContact "+Parameters.getSupportEmail()+" in case of problems\n");    

		}
	}
}