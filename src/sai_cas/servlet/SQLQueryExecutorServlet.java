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
		


	}
}
