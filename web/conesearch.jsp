<%@ 
page language="java" import="java.sql.*" %><%@ 
page language="java" import="javax.sql.*" %><%@ 
page language="java" import="sai_cas.*" %><%@
page language="java" import="sai_cas.db.*" %><%@
page language="java" import="sai_cas.vo.*" %><%@ 
page session="false"  %><%@ 
page contentType="text/xml" %><%

String cat = request.getParameter("cat");
String tab = request.getParameter("tab");

String sra = request.getParameter("RA");
String sdec = request.getParameter("DEC");
String ssr = request.getParameter("SR");
double ra, dec, sr;

try {
	if ((sra == null) || (sdec == null) || (ssr == null))
	{
		throw new Exception("ERROR: RA, DEC and SR must be defined");
	}

	if (cat == null)
	{
		throw new Exception("ERROR: Catalog name must be defined");
	}
	application.log("ConeSearch run; catalog: " + cat + " table: " + tab + " RA = " + sra + " DEC = " + sdec + " SR = " + ssr);
		
	ra = Double.parseDouble(sra);
	dec = Double.parseDouble(sdec);
	sr = Double.parseDouble(ssr);
	
	ConeSearch.printVOTableConeSearch(out, cat, tab, ra, dec, sr);
}	
catch (NumberFormatException e) 
{
	ConeSearch.printVOTableConeSearchError(out, e.toString());
}
catch (Exception e) 
{
	ConeSearch.printVOTableConeSearchError(out, e.getMessage());
}

%>