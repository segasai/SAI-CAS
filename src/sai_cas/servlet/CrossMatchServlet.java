package sai_cas.servlet;


import java.io.IOException;
import java.io.PrintWriter;
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
		try
		{
			uploadedFile = File.createTempFile("crossmatch",".dat",new File("/tmp/"));
			fi.write(uploadedFile);
			logger.debug("File written");
			conn = DBConnection.getPooledPerUserConnection("cas_user_tmp","aspen");
			dbi = new DBInterface(conn,"cas_metadata_tmp");
			Votable vot = new Votable (uploadedFile);
			String userDataSchema = "cas_data_user_tmp";
			String tableName = vot.insertDataToDB(dbi,userDataSchema);
			
			String[] raDecArray = dbi.getRaDecColumns(cat, tab);
			//dbi.close();
			String[] raDecArray1 = dbi.getRaDecColumnsFromUCD(userDataSchema,
				tableName);
			
			dbi.executeQuery("select * from " + userDataSchema + "." + tableName +
					" AS a, " + cat + "." + tab + " AS b "+
					"WHERE q3c_join(a."+raDecArray1[0]+",a."+raDecArray1[1]+",b."+
					raDecArray[0]+",b."+raDecArray[1]+","+rad+")");
			VOTableQueryResultsOutputter voqro = new VOTableQueryResultsOutputter();
			voqro.print(out,dbi);
			
		}
		catch (Exception e)
		{
			logger.error("Something is wrong "+e);
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
			{}
		}
		
	}
}
