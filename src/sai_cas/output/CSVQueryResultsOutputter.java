package sai_cas.output;
import java.io.PrintWriter;
import org.apache.log4j.Logger;
import sai_cas.db.DBInterface;
import sai_cas.Parameters;;

public class CSVQueryResultsOutputter implements QueryResultsOutputter
{
	static Logger logger = Logger.getLogger("sai_cas.output.CVSQueryResultsOutputter");

	String resource, table, resourceInfo, resourceDescription;

	public CSVQueryResultsOutputter()
	{}
		
	public void printError(PrintWriter out, String message)
	{
		String messageCorrected = "#" + message.replace("\n","\n#");
		out.print("#"+messageCorrected);
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
					out.print(",");
				}
			}
			out.print("\n");
			logger.debug("Retrieving and outputting the data ...");
			
			String dataArray[];
			while (dbi.qr.next())
			{
				dataArray = dbi.qr.getStringArray();
				for (int i = 0; i < ncols; i++)
				{
					String s = dataArray[i];
					if (s == null) s = "";
					if (i != 0) 
					{
						out.print("," + s);
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