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

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

import sai_cas.VOTABLEFile.VOTABLE;
import sai_cas.VOTABLEFile.Votable;
import sai_cas.VOTABLEFile.VotableException;
import sai_cas.db.*;
import sai_cas.output.CSVQueryResultsOutputter;
import sai_cas.output.QueryResultsOutputter;
import sai_cas.output.VOTableQueryResultsOutputter;
import sai_cas.vo.*;

public class CrossMatchServlet extends HttpServlet {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("sai_cas.CrossMatchServlet");
	public enum formats {VOTABLE, CSV};

	public class CrossMatchServletException extends Exception
	{
		CrossMatchServletException()
		{
			super();
		}
		CrossMatchServletException(String s)
		{
			super(s);
		}

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, java.io.IOException
	{

		PrintWriter out = response.getWriter();

		
		String cat = null, tab = null, radString = null, raColumn = null,
			decColumn = null, formatString = null;
		formats format;

		List<FileItem> fileItemList = null;


		FileItemFactory factory = new DiskFileItemFactory();
		try 
		{		
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setSizeMax(50000000);
			/* Request size <= 50Mb */
			fileItemList = sfu.parseRequest(request);
			
		}
		catch (FileUploadException e)
		{
			throw new ServletException(e.getMessage()); 
			/* Nothing ...*/
		}
		
		FileItem fi = null;
		
		for (FileItem fi0: fileItemList)
		{
			if (fi0.getFieldName().equals("file"))//(!fi0.isFormField())
			{
				fi = fi0;
			}
			if (fi0.getFieldName().equals("tab"))//(!fi0.isFormField())
			{
				tab = fi0.getString();
			}
			if (fi0.getFieldName().equals("cat"))//(!fi0.isFormField())
			{
				cat = fi0.getString();
			}
			if (fi0.getFieldName().equals("rad"))//(!fi0.isFormField())
			{
				radString = fi0.getString();
			}
			if (fi0.getFieldName().equals("racol"))//(!fi0.isFormField())
			{
				raColumn = fi0.getString();
			}
			if (fi0.getFieldName().equals("deccol"))//(!fi0.isFormField())
			{
				decColumn = fi0.getString();
			}
			if (fi0.getFieldName().equals("format"))//(!fi0.isFormField())
			{
				formatString = fi0.getString();
			}
		}
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
			csvqro = new CSVQueryResultsOutputter(null);
			qro = csvqro;
			break;
		case VOTABLE:
			response.setContentType("text/xml");
			voqro = new VOTableQueryResultsOutputter();
			qro = voqro;
			break;
		}

		File uploadedFile = null;
		Connection conn = null;
		DBInterface dbi = null;

		try
		{
			double rad = 0;
			
			rad = Double.parseDouble(radString);
			
			if (fi == null)
			{
				throw new CrossMatchServletException("File should be specified" + fileItemList.size() );			
			}
			long size = fi.getSize();

			if (size > 10000000) 
			{
				throw new CrossMatchServletException("File is too big");
			}
			if (size == 0) 
			{
				throw new CrossMatchServletException("File must not be empty");
			}

			if (format.equals(formats.CSV))
			{
				if ((raColumn==null)||(decColumn==null))
				{
					throw new CrossMatchServletException("When you use the CSV format, you must specify which columns contain RA and DEC");
				}
			}
				
			uploadedFile = File.createTempFile("crossmatch",".dat",new File("/tmp/"));
			try
			{
				fi.write(uploadedFile);
			}
			catch (Exception e)
			{
				throw new CrossMatchServletException("Error in writing your data in the temporary file");
			}
			
			logger.debug("File written");
			String[] userPasswd = sai_cas.Parameters.getDefaultTempDBUserPasswd();
			String tempUser = userPasswd[0];
			String tempPasswd = userPasswd[1];
			conn = DBConnection.getPooledPerUserConnection(tempUser, tempPasswd);
			dbi = new DBInterface(conn,tempUser);
			Votable vot = null;
			switch (format)
			{
			case CSV:
				vot = Votable.getVOTableFromCSV(uploadedFile);
				if ((!vot.checkColumnExistance(raColumn)) || 
					(!vot.checkColumnExistance(decColumn)))
				{
					throw new CrossMatchServletException("The column names specified as RA and DEC should be present in the CSV file");
				}
				break;
			case VOTABLE:
				vot = new Votable (uploadedFile);
				break;
			}
			String userDataSchema = dbi.getUserDataSchemaName();
			String tableName = vot.insertDataToDB(dbi,userDataSchema);
			dbi.analyze(userDataSchema, tableName);
			String[] raDecArray = dbi.getRaDecColumns(cat, tab);
			String[] raDecArray1 = null;
			switch(format)
			{
			case VOTABLE:
				raDecArray1 = dbi.getRaDecColumnsFromUCD(userDataSchema,
						tableName);
				if (raDecArray1 == null)
				{
					throw new CrossMatchServletException( "Error occured: " + "You must have the columns in the table having the UCD of alpha or delta ('POS_EQ_RA_MAIN', 'POS_EQ_DEC_MAIN') to do the crossmatch");
				}
				break;
			case CSV:
				raDecArray1 = new String[2];
				raDecArray1[0] = raColumn;
				raDecArray1[1] = decColumn;	
			}

			response.setHeader("Content-Disposition",
					"attachment; filename=" + cat + "_" + 
					fi.getName() + "_" + String.valueOf(rad) + ".dat");
			dbi.executeQuery("select * from " + userDataSchema + "." + tableName +
					" AS a LEFT JOIN " + cat + "." + tab + " AS b "+
					"ON q3c_join(a."+raDecArray1[0]+",a."+raDecArray1[1]+",b."+
					raDecArray[0]+",b."+raDecArray[1]+","+rad+")");
			if (format.equals(formats.VOTABLE))
			{
				voqro.setResource(cat + "_" + fi.getName() );
				voqro.setResourceDescription("This is the table obtained by "+
						"crossmatching the table "+cat+"."+tab + " with the " +
						"user supplied table from the file " + fi.getName()+"\n"+
						"Radius of the crossmatch: "+rad+"deg");
				voqro.setTable("main" );
			}
			qro.print(out,dbi);
		}
		catch (VotableException e)
		{
			qro.printError(out, "Error occured: "+ e.getMessage() + "Cannot read the VOTable, probably it is not well formed (remember that you must have 'xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\"' in the VOTABLE tag)");
		}
		catch (NumberFormatException e)
		{
			qro.printError(out, "Error occured: " + e.getMessage());
		}
		catch (CrossMatchServletException e)
		{
			qro.printError(out, "Error occured: " + e.getMessage());
		}
		catch (DBException e)
		{
			logger.error("DBException " + e);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			qro.printError(out, "Error occured: " + e + "\nCause: " + e.getCause() + "\nTrace: " + sw);
		}
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
			try 
			{
				if (uploadedFile != null)
				{
					uploadedFile.delete();
				}
			}
			catch (Exception e)
			{
				logger.error("Failed to delete the temporary file: " + uploadedFile.getCanonicalPath());
			}
		}
		
	}
}
