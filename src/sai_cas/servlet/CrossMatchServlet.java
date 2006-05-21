package sai_cas.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import java.util.List;
import java.util.Calendar;

import java.sql.Connection;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;

import sai_cas.db.*;
import sai_cas.vo.*;
import sai_cas.Votable;

public class CrossMatchServlet extends HttpServlet {
	
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
		String cat = request.getParameter("cat");
		String tab = request.getParameter("tab");
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
			/* Nothing ...*/
		}
		
		/* First file */
		
		FileItem fi = fileItemList.get(0);
		long size = fi.getSize();
		if (size > 10000000) {throw new ServletException("File is too big");}
		File uploadedFile = null;
		Connection conn = null;
		DBInterface dbi = null;
		try
		{
			uploadedFile = File.createTempFile("crossmatch",".dat",new File("/tmp/"));
			fi.write(uploadedFile);
			conn = DBConnection.getPooledPerUserConnection("cas_user_tmp","aspen");
			dbi = new DBInterface(conn,"cas_metadata_tmp");
			Votable vot = new Votable (uploadedFile);
			vot.insertDataToDB(dbi,"cas_data_user_tmp");
		}
		catch (Exception e)
		{
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
