package sai_cas.vo;

import sai_cas.db.*;
import sai_cas.output.*;
//import javax.servlet.jsp.*;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * The class printing the cone search results 
 * @author Sergey Koposov (math@sai.msu.ru) 
 */

public class ConeSearch
{
	static Logger logger = Logger.getLogger("sai_cas.vo.ConeSearch");
	
	PrintWriter out;
	String[] tableColumnList, raDecArray;
	String  outputColumnSelection;
	Connection conn;
	DBInterface dbi;
	String table, catalog, format, column_ID_MAIN_UCD, catalogDescription;
	QueryResultsOutputter qro;
	double ra, dec, rad;
	
	public static class ConeSearchException extends Exception
	{
		public ConeSearchException (String s)
		{
			super(s);
		}
	}
	public ConeSearch(PrintWriter out, String format)
	{
		this.out = out;
		this.format = format;	
		if (format.equals("votable"))
		{
			VOTableQueryResultsOutputter voqro = new VOTableQueryResultsOutputter();
			qro = voqro;
		}
		else if (format.equals("csv"))
		{
			qro = new CSVQueryResultsOutputter();		
		}
		else 
		{
			VOTableQueryResultsOutputter voqro = new VOTableQueryResultsOutputter();
			qro = voqro;
		}
	}
	
	public boolean initConeSearch(String catalog, String tableIn, double ra, double dec, double rad)
	{
		this.catalog = catalog;
		this.ra = ra;
		this.dec = dec;
		this.rad = rad;

		try
		{
			logger.debug("Trying to get the Pooled Connection...");
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

			if (tableIn == null)
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
						
			else 
			{
				if (!dbi.checkTableExists(catalog, tableIn))
				{
				throw new ConeSearchException("The table \"" + tableIn + "\" does not exist in the catalogue \""+ catalog + "\"");				
				}
				table = tableIn;
			}

			catalogDescription = dbi.getCatalogDescription(catalog);


			raDecArray = dbi.getRaDecColumns(catalog, table);

			if ((raDecArray[0] == null) || (raDecArray[1] == null))
			{
				throw new ConeSearchException("Selected table in the catalogue "+
						"does not have marked RA, DEC columns. The Cone Search cannot be run in that case...");
			}
			
			column_ID_MAIN_UCD = dbi.getColumn_ID_MAIN_UCD(catalog,table);

			tableColumnList = dbi.getColumnNames(catalog,table);
			
			if (rad > sai_cas.Parameters.getMaxConeSearchRadius())
			{
				throw new ConeSearchException("ERROR: Sorry, we currently do not allow queries with search radius greater than "+sai_cas.Parameters.getMaxConeSearchRadius()+" degrees.");
			}
			
			outputColumnSelection = "*";
			/* The default output list is the list of all table columns */
			
		}
		catch (java.sql.SQLException e)
		{
			DBInterface.close(dbi,conn,false);
			logger.error("Got the SQL exception...",e);
			qro.printError(out, "ERROR:\nSQL Exception: " + e.getMessage() +
						"\nContact saicas@sai.msu.su in case of problems\n");
			return false;
		}
		catch (ConeSearchException e)
		{
			DBInterface.close(dbi,conn,false);
			qro.printError(out, "ERROR: " + e.getMessage() +
						"\nContact saicas@sai.msu.su in case of problems\n");    
			return false;
		}
		return true;
	}

	public void setVerbosity(int verbosity, boolean withDistance)
	{
		if (verbosity == 1)
		{
			outputColumnSelection =
				(column_ID_MAIN_UCD == null ? "" : column_ID_MAIN_UCD + ",") + 
				raDecArray[0] + "," + raDecArray[1];
		}
		else
		{
		/* Leave the default outputColumnSelection (which is * ) */
		}
		if (withDistance)
		{
			outputColumnSelection = "q3c_dist("+raDecArray[0]+","+raDecArray[1]+","+ra+","+dec+") AS __dist," + outputColumnSelection;
		}
	}
	
	public void setColumnList(String columnList[], boolean withDistance)
	{
		HashSet hs = new HashSet<String>(Arrays.asList(tableColumnList));
		StringBuffer sb = new StringBuffer();
		
		if (withDistance)
		{
			sb.append("q3c_dist("+raDecArray[0]+","+raDecArray[1]+","+ra+","+dec+") AS __dist,"); 
		}
		
		for (int i = 0 ; i < columnList.length ; i++)
		{
			if (hs.contains(columnList[i]))
			{
				sb.append(columnList[i] + ",");
			}
		}
		if (sb.length() == 0)
		{
			outputColumnSelection = "*";
		}
		else
		{
			sb.setLength(sb.length()-1);
			outputColumnSelection = sb.toString();
		}
	}

	public void printConeSearch()
	{
		try
		{
			/* Now we execute the query */	
			dbi.executeQuery("select " + outputColumnSelection + " from " + 
				catalog + "." + table + " where q3c_radial_query("+raDecArray[0] +","+raDecArray[1]+","+ra+","+dec+","+rad+")");
				
			if (format.equals("votable"))
			{
				VOTableQueryResultsOutputter voqro = (VOTableQueryResultsOutputter) qro;
				voqro.setResource(catalog);
				voqro.setResourceDescription(catalogDescription);
				voqro.setResourceInfo("Cone search result from catalogue: "+catalog +
					", table: "+ table+"\n" +
					"RA="+ra+" DEC="+dec+" SR="+rad+"\n"+
					"Contact saicas@sai.msu.su in case of problems");
				voqro.setTable(table);
			}
			qro.print(out, dbi);

		}
		catch (java.sql.SQLException e)
		{
			logger.error("Got the SQL exception...",e);
			qro.printError(out, "ERROR:\nSQL Exception: " + e.getMessage() +
						"\nContact saicas@sai.msu.su in case of problems\n");    
		}				
		dbi.close();
	}
	
	public void printConeSearchError(String message)
	{
		qro.printError(out, message);
	}
}