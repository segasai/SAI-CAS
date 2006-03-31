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

		double ra, dec, sr;

		try 
		{
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
			
			/* !!!!!!!! TODO !!!!!!!!!! 
			 * The maxSR parameter should be defined in some specific place 
			 * for example in .properties file or in the database... 
			 */
			if (sr > 3)
			{
				throw new ConeSearchServletException("ERROR: Sorry we currently do not allow queries with search radius greater than 3 degrees.");
			}
			
			ConeSearch.printVOTableConeSearch(out, cat, tab, ra, dec, sr);

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
