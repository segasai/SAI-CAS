package sai_cas.servlet;

import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import sai_cas.servlet.ConeSearchServlet.ConeSearchServletException;
import sai_cas.vo.ConeSearch;

public class SQLQueryExecutorServlet extends GenericServlet
{
	public void service(ServletRequest request, ServletResponse response) 
		throws ServletException, java.io.IOException
	{
		
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		
		String query = request.getParameter("query");
		
		try 
		{
			
//			ConeSearch.printVOTableConeSearch(out, cat, tab, ra, dec, sr);
			
		}	
		catch (NumberFormatException e) 
		{
			ConeSearch.printVOTableConeSearchError(out, e.toString());
		}
/*		catch (ConeSearchServletException e) 
		{
			ConeSearch.printVOTableConeSearchError(out, e.getMessage());
		}
*/
	}
}
