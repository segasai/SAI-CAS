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


package sai_cas.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.io.File;

import java.util.List;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.Locale;

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
		String ra_col = "__match_ra", dec_col = "__match_dec";
/*		for (int i = 0; i<columns.length; i++)
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
*/
		query = raArray[0] +" AS " + ra_col + ", " + decArray[0] + " AS " + dec_col +", *";
		
		query= "select * from ( select " + query + " from "+cats[0]+"."+tabs[0] + ") as a where q3c_radial_query("+ra_col+","+dec_col+","+ra+","+dec+","+sr+") offset 0";
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

		
		String	formatString;
		String cat, tab;
		formats format;
		String fileExtension;

		cat = request.getParameter("cats");
		tab = request.getParameter("tabs");
		cats = cat.split(",");
		tabs = tab.split(",");
		
		String sra = request.getParameter("RA");
		String sdec = request.getParameter("DEC");
		String ssr = request.getParameter("SR");
		String srad = request.getParameter("RAD");
		formatString = request.getParameter("format");		
		String nulls = request.getParameter("NULLS");

		if ((formatString==null)||(formatString.equalsIgnoreCase("votable")))
		{
			format = formats.VOTABLE;
			fileExtension = "xml";
		}
		else if (formatString.equalsIgnoreCase("CSV"))
		{
			format = formats.CSV;
			fileExtension = "csv";
		}
		else
		{
			format = formats.VOTABLE;
			fileExtension = "xml";
		}

		QueryResultsOutputter qro = null;
		CSVQueryResultsOutputter csvqro = null;
		VOTableQueryResultsOutputter voqro = null;			
		switch (format)
		{
		case CSV:
			response.setContentType("text/csv");			
			csvqro = new CSVQueryResultsOutputter(nulls);
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
			if ((sra==null)||(sdec==null)||(ssr==null)||(srad==null))
			{
				throw new InternalCrossMatchServletException("You must specify the RA,DEC, SR and RAD fields");							
			}
			
			rad = Double.parseDouble(srad);
			sr = Double.parseDouble(ssr);
			ra = Double.parseDouble(sra);
			dec = Double.parseDouble(sdec); 

			if (sr>sai_cas.Parameters.getMaxConeSearchRadius())
			{
				throw new InternalCrossMatchServletException("The radius of the query is too big");
			}
			/* TODO 
			 * !!!!!!!! IMPORTANT !!!!!!!!!!!!!! 
			 * I should redo the logic of the max crossmatch radius 
			 */
			if (rad>10./3600) //10 arcseconds
			{
				throw new InternalCrossMatchServletException("The radius of the  is too big");
			}

			if ((cat==null)||(tab==null))
			{
				throw new InternalCrossMatchServletException("The catalog and table lists must be specified");
			}
			cats = cat.split(",");
			tabs = tab.split(",");

			if (cats.length != tabs.length)
			{
				throw new InternalCrossMatchServletException("Wrong list of tables or catalogs");
			}
			for(int i=0; i<cats.length; i++)
			{
				if ((cats[i]==null)||(tabs[i]==null))
				{
				throw new InternalCrossMatchServletException("The table and catalogs must not be nulls");					
				}
			}

			for(int i=0; i<cats.length; i++)
			{
				if ((cats[i]==null)||(tabs[i]==null))
				{
					throw new InternalCrossMatchServletException("The table and catalogs must not be nulls");					
				}
			}			

			String[] userPasswd = sai_cas.Parameters.getDefaultDBUserPasswd();
			String user = userPasswd[0];
			String passwd = userPasswd[1];
			conn = DBConnection.getPooledPerUserConnection(user, passwd);
			dbi = new DBInterface(conn);

			for(int i=0; i<cats.length; i++)
			{
				if (!dbi.checkTableExists(cats[i],tabs[i])) 
				{
					throw new InternalCrossMatchServletException("The table "+cats[i]+"."+tabs[i] +" does not exist");
				}
			}			


			raArray = new String[cats.length];
			decArray = new String[cats.length];

			for(int i=0; i<cats.length; i++)
			{
				String tmp[] = dbi.getRaDecColumns(cats[i], tabs[i]);
				raArray[i]= tmp[0];
				decArray[i]= tmp[1];
			}
			
			//columns = dbi.getColumnNames(cats[0],tabs[0]);

			String outputFilename="crossmatch."+fileExtension;

			response.setHeader("Content-Disposition",
					"attachment; filename=" + outputFilename);

			String query = getQuery();
			dbi.executeQuery(query);
			if (format.equals(formats.VOTABLE))
			{
				String tmp=""; 
				for(int i=0;i<cats.length;i++)
				{
					tmp+=" "+cats[i]+"."+tabs[i];
				}
				
				voqro.setResource("crossmatch_res" );
				voqro.setResourceDescription("This table was obtained by crossmatching the tables: \n"+
				tmp+"\n with the crossmatch radius of " + rad + " degrees in the"+
				sr+" degrees circular region around ("+ra+","+dec+")");
				voqro.setTable("main");
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
