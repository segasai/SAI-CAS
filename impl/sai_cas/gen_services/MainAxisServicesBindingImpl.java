/**
 * Sai_casSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package sai_cas.gen_services;

import java.sql.Date; 
import java.util.Calendar; 

import org.apache.log4j.Logger;

import sai_cas.services.MainAxisServices;


public class MainAxisServicesBindingImpl implements sai_cas.gen_services.MainAxisServices
{
	static Logger logger = Logger.getLogger("sai_cas.AXIS_SERVICES");
	public void insertCatalogFromURI(java.lang.String uriCatalog) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalogFromURI...");
		logger.debug("The following catalogue is being inserted " + uriCatalog);
		
		sai_cas.services.MainAxisServices.insertCatalogFromURI(uriCatalog);
	}

	public void insertCatalog(java.lang.String catalogString) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalog...");
		logger.debug("The following catalogue is being inserted" + catalogString);
		
		sai_cas.services.MainAxisServices.insertCatalog(catalogString);
	}

	public void insertCatalogFromVotable(java.lang.String catalogString, String user, String password) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalogFromVotable...");
		logger.debug("The following catalogue is being inserted" + catalogString);
		
		sai_cas.services.MainAxisServices.insertCatalogFromVotable(catalogString,user,password);
	}

	public java.lang.String[] getCatalogNames() throws java.rmi.RemoteException
	{
		return MainAxisServices.getCatalogNames();
	}


	public java.lang.String getCatalogInfo(String cat) throws java.rmi.RemoteException
	{
		try 
		{
			return MainAxisServices.getCatalogInfo(cat);
		}
		catch (Exception e)
		{
			logger.error("Catched exception ",e);
			return null;
		}	
	}

	public java.lang.String getCatalogDescription(String cat) throws java.rmi.RemoteException
	{
		try 
		{
			return MainAxisServices.getCatalogDescription(cat);
		}
		catch (Exception e)
		{
			logger.error("Catched exception ",e);
			return null;
		}	
	}

	public java.lang.String[] getTableNames(java.lang.String catalogName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getTableNames(catalogName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}

	public java.lang.String[] getColumnNames(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnNames(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}


	public java.lang.String[] getColumnInfos(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnInfos(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}
	

	public java.lang.String[] getColumnDescriptions(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnDescriptions(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}


	public java.lang.String[] getColumnUnits(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnUnits(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}

	public java.lang.String[] getColumnDatatypes(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnDatatypes(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}




	public java.lang.String[] getColumnUCDs(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnUCDs(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}


	public java.lang.String[][] getIndexes(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getIndexes(catalogName,tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}
	
	public String getConeSearchAsString(String cat, String tab, double ra, double dec, double sr, String format) 
	{
		return MainAxisServices.getConeSearchAsString(cat, tab, ra, dec, sr, format);
	}

	public String getConeSearchAsString(String cat, String tab, double ra, double dec, double sr, String format, int verbosity) 
	{
		return MainAxisServices.getConeSearchAsString(cat, tab, ra, dec, sr,
			format, verbosity);
	}

	public String getConeSearchAsString(String cat, String tab, double ra, double dec, double sr, String format, String columnList[])
	{
		return MainAxisServices.getConeSearchAsString(cat, tab, ra, dec, sr, format, columnList);
	}
	
/*	public Date getDBLastChangedDate()
	{
		return (MainAxisServices.getDBLastChangedDate());
	}
*/
	public Calendar getDBLastChangedDate()
	{
		return (MainAxisServices.getDBLastChangedDate());
	}

	
}
