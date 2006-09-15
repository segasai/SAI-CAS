package sai_cas.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.io.File;

import java.util.List;
import java.util.Calendar;
import java.util.logging.Logger;

import java.sql.Connection;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

import sai_cas.db.*;
import sai_cas.output.VOTableQueryResultsOutputter;
import sai_cas.vo.*;
import sai_cas.Votable;
import sai_cas.VotableException;

//import org.apache.log4j.Logger;

public class CrossMatchServlet extends HttpServlet {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("sai_cas.CrossMatchServlet");

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
/*		String cat = request.getParameter("cat");
		String tab = request.getParameter("tab");
		String rad = request.getParameter("rad");
		*/
		response.setContentType("text/xml");

		
		String cat = null, tab = null, rad = null;

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
				rad = fi0.getString();
			}

		}
		//logger.debug("CAT: " + cat + " TAB: " + tab + " RAD: " + rad);

		
		if (fi == null)
		{
			throw new ServletException("File should be specified" + fileItemList.size() );			
		}
		long size = fi.getSize();
		if (size > 10000000) 
		{
			throw new ServletException("File is too big");
		}
		File uploadedFile = null;
		Connection conn = null;
		DBInterface dbi = null;
		VOTableQueryResultsOutputter voqro = new VOTableQueryResultsOutputter();

		try
		{
			uploadedFile = File.createTempFile("crossmatch",".dat",new File("/tmp/"));
			fi.write(uploadedFile);
			logger.debug("File written");
			String tempUser = "cas_user_tmp";
			String tempPasswd = "aspen";
			conn = DBConnection.getPooledPerUserConnection(tempUser, tempPasswd);
			dbi = new DBInterface(conn,tempUser);
			Votable vot = new Votable (uploadedFile);
			String userDataSchema = dbi.getUserDataSchemaName();
			String tableName = vot.insertDataToDB(dbi,userDataSchema);
			dbi.analyze(userDataSchema, tableName);
			String[] raDecArray = dbi.getRaDecColumns(cat, tab);
			String[] raDecArray1 = dbi.getRaDecColumnsFromUCD(userDataSchema,
				tableName);
			if (raDecArray1 == null)
			{
				voqro.printError(out, "Error occured: " + "You must have the columns in the table having the UCD of alpha or delta ('POS_EQ_RA_MAIN', 'POS_EQ_DEC_MAIN') to do the crossmatch");
			}
			else
			{
				dbi.executeQuery("select * from " + userDataSchema + "." + tableName +
						" AS a LEFT JOIN " + cat + "." + tab + " AS b "+
						"ON q3c_join(a."+raDecArray1[0]+",a."+raDecArray1[1]+",b."+
						raDecArray[0]+",b."+raDecArray[1]+","+rad+")");
				voqro.print(out,dbi);
			}
			
		}
		catch (VotableException e)
		{
			voqro.printError(out, "Error occured: " + "Cannot read the VOTable, probably it is not well formed (remember that you must have 'xmlns=\"http://www.ivoa.net/xml/VOTable/v1.1\"' in the VOTABLE tag)");
		}
		catch (Exception e)
		{
			logger.error("Something is wrong "+e);
			StringWriter sw =new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			voqro.printError(out, "Error occured: " + e +"\nCause: " +e.getCause()+"\nTrace: "+sw);
		}
		finally 
		{
			DBInterface.close(dbi,conn,false);
			/* Always rollback */
			try 
			{
				uploadedFile.delete();
			}
			catch (Exception e)
			{
			}
		}
		
	}
}
