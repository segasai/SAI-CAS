package sai_cas.servlet;
import java.io.*;
//import java.util.*;
//import java.sql.*;
//import javax.sql.*;

import javax.servlet.*;
import sai_cas.db.*;
import sai_cas.vo.*;

public class ConeSearchServlet extends GenericServlet {
	
	public class ConeSearchServletException extends Exception
	{
		ConeSearchServletException()
		{
			super();
		}
		ConeSearchServletException(String s)
		{
			super(s);
		}

	}
	public void service(ServletRequest request, ServletResponse response) 
		throws ServletException, java.io.IOException
	{

		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		String cat = request.getParameter("cat");
		String tab = request.getParameter("tab");
		
		String sra = request.getParameter("RA");
		String sdec = request.getParameter("DEC");
		String ssr = request.getParameter("SR");

		String sverbosity = request.getParameter("VERB");
		/* We set up the the default maximal verbosity */
		int verbosity;
		try 
		{
			verbosity = Integer.parseInt(sverbosity);
			if ((verbosity < 1) || (verbosity > 3))
			{
				throw new Exception();
			}
		}
		catch (Exception e) 
		{
			verbosity = 3;
		}
		


		if (sra == null)
		{
			sra = request.getParameter("ra");
		}
		if (sdec == null)
		{
			sdec = request.getParameter("dec");
		}
		if (ssr == null) 
		{
			ssr = request.getParameter("sr");
		}


		try 
		{
			double ra, dec, sr;
			
			if ((sra == null) || (sdec == null) || (ssr == null))
			{
				throw new ConeSearchServletException("ERROR: RA, DEC and SR must be defined");
			}
				
			if (cat == null)
			{
				throw new ConeSearchServletException("ERROR: Catalog name must be defined");
			}
						
			ra = Double.parseDouble(sra);
			dec = Double.parseDouble(sdec);
			sr = Double.parseDouble(ssr);
			
			ConeSearch.printVOTableConeSearch(out, cat, tab, ra, dec, sr, verbosity);
		}	
		catch (NumberFormatException e) 
		{
			ConeSearch.printVOTableConeSearchError(out, "ERROR: Invalid input for (RA,DEC,SR)");
		}
		catch (ConeSearchServletException e) 
		{
			ConeSearch.printVOTableConeSearchError(out, e.getMessage());
		}
	}
}
