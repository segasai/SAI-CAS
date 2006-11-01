package sai_cas.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.io.File;

import java.util.List;
import java.util.Calendar;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import sai_cas.VOTABLEFile.VOTABLE;
import sai_cas.VOTABLEFile.Votable;
import sai_cas.VOTABLEFile.VotableException;
import sai_cas.db.*;
import sai_cas.output.CSVQueryResultsOutputter;
import sai_cas.output.QueryResultsOutputter;
import sai_cas.output.VOTableQueryResultsOutputter;
import sai_cas.vo.*;

public class InternalCrossMatchServlet extends HttpServlet {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("sai_cas.CrossMatchServlet");

	public enum formats {VOTABLE, CSV};
	String cats[], tabs[], raArray[], decArray[];
	String columns[];
	double ra, dec, rad, sr;
	
	public class InternalCrossMatchServletException extends Exception
	{
		InternalCrossMatchServletException()
		{
			super();
		}
		InternalCrossMatchServletException(String s)
		{
			super(s);
		}

	}

	public String getQuery()
	{
		int len = cats.length;
		String query="";
		String ra_col = "__q3c_ra", dec_col = "__q3c_dec";
		for (int i = 0; i<columns.length; i++)
		{
			if (i != 0) {query += ",";}
			
			if (columns[i].equals(raArray[0]))
			{
				query += columns[i] + " as " + ra_col + " ";
			}
			else if (columns[i].equals(decArray[0])) 
			{
				query += columns[i] +" as " + dec_col + " ";
			}
			else query += columns[i] + " "; 
		}
		
		query= "select * from ( select " + query + " from "+cats[0]+"."+tabs[0] + ") as a where q3c_radial_query("+ra_col+","+dec_col+","+ra+","+dec+","+rad+") offset 0";
		for (int i = 1; i < len; i++)
		{
			query = "select * from (" + query + ") as a left join " + cats[i]+"."+tabs[i] +" as b on q3c_join(a."+ra_col+",a."+dec_col+",b."+raArray[i]+",b."+decArray[i]+","+rad+") offset 0";
		}
		return query;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, java.io.IOException
	{

		PrintWriter out = response.getWriter();

		
		String	formatString = null;
		String cat, tab;
		formats format;

		cat = request.getParameter("cats");
		tab = request.getParameter("tabs");
		cats = cat.split(",");
		tabs = tab.split(",");
		
		String sra = request.getParameter("RA");
		String sdec = request.getParameter("DEC");
		String ssr = request.getParameter("SR");
		String srad = request.getParameter("RAD");


		if ((formatString==null)||(formatString.equalsIgnoreCase("votable")))
		{
			format = formats.VOTABLE;
		}
		else if (formatString.equalsIgnoreCase("CSV"))
		{
			format = formats.CSV;
		}
		else
		{
			format = formats.VOTABLE;
		}

		QueryResultsOutputter qro = null;
		CSVQueryResultsOutputter csvqro = null;
		VOTableQueryResultsOutputter voqro = null;			
		switch (format)
		{
		case CSV:
			response.setContentType("text/csv");			
			csvqro = new CSVQueryResultsOutputter();
			qro = csvqro;
			break;
		case VOTABLE:
			response.setContentType("text/xml");
			voqro = new VOTableQueryResultsOutputter();
			qro = voqro;
			break;
		}

		Connection conn = null;
		DBInterface dbi = null;

		try
		{
			rad = Double.parseDouble(srad);
			sr = Double.parseDouble(ssr);
			ra = Double.parseDouble(sra);
			dec = Double.parseDouble(sdec);
			
			if (cats.length != tabs.length)
			{
				throw new InternalCrossMatchServletException("Wrong list of tables or catalogs");			
			}
			

			String[] userPasswd = sai_cas.Parameters.getDefaultDBUserPasswd();
			String user = userPasswd[0];
			String passwd = userPasswd[1];
			conn = DBConnection.getPooledPerUserConnection(user, passwd);
			dbi = new DBInterface(conn);

			Votable vot = null;

			String raArray[] = new String[cats.length];
			for(int i=0; i<cats.length; i++)
			{
				String tmp[] = dbi.getRaDecColumns(cats[i], tabs[i]);
				raArray[i]= tmp[0];
				decArray[i]= tmp[1];
			}
			
			columns = dbi.getColumnNames(cats[0],tabs[0]);

			response.setHeader("Content-Disposition",
					"attachment; filename=crossmatch.dat");
			String query = getQuery();
			dbi.executeQuery(query);
			if (format.equals(formats.VOTABLE))
			{
				voqro.setResource("RES" );
				voqro.setResourceDescription("This is the table obtained by "+
						"Radius of the crossmatch: "+rad+"deg");
				voqro.setTable("main" );
			}
			qro.print(out,dbi);
		}
/*		catch (VotableException e)
		{
			qro.printError(out, "Error occured: "+ e.getMessage() + "Cannot read the VOTable, probably it is not well formed (remember that you must have 'xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\"' in the VOTABLE tag)");
		}
*/
		catch (NumberFormatException e)
		{
			qro.printError(out, "Error occured: " + e.getMessage());
		}
		catch (InternalCrossMatchServletException e)
		{
			qro.printError(out, "Error occured: " + e.getMessage());
		}
/*		catch (DBException e)
		{
			logger.error("DBException " + e);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			qro.printError(out, "Error occured: " + e + "\nCause: " + e.getCause() + "\nTrace: " + sw);
		}
*/
		catch (SQLException e)
		{
			logger.error("SQLException "+e);
			StringWriter sw =new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			qro.printError(out, "Error occured: " + e +"\nCause: " +e.getCause()+"\nTrace: "+sw);
		}
		finally 
		{
			DBInterface.close(dbi,conn,false);
			/* Always rollback */
		}
		
	}
}
