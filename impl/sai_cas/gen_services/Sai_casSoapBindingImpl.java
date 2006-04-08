/**
 * Sai_casSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package sai_cas.gen_services;
import org.apache.log4j.Logger;

import sai_cas.services.MainAxisServices;


public class Sai_casSoapBindingImpl implements sai_cas.gen_services.MainAxisServices
{
	static Logger logger = Logger.getLogger("sai_cas.AXIS_SERVICES");
	public void insertCatalogfromURI(java.lang.Object uriCatalog) throws java.rmi.RemoteException
	{
	}

	public void insertCatalog(java.lang.String catalogString) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalog...");
		logger.debug("The following catalogue has been inserted" + catalogString);
		
		try 
		{
			sai_cas.services.MainAxisServices.insertCatalog(catalogString);
		}
		catch (Exception e)
		{
			logger.error("Catched exception ",e);
		}
	}
	public java.lang.String[] getCatalogNames() throws java.rmi.RemoteException
	{
		try 
		{
			return MainAxisServices.getCatalogNames();
		}
		catch (Exception e)
		{
			logger.error("Catched exception ",e);
			return null;
		}
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

	public java.lang.String[] getTableNames(java.lang.String catalogName) throws java.rmi.RemoteException {
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

	public java.lang.String[] getColumnNames(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException {
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


	public java.lang.String[] getColumnInfos(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException {
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
	

	public java.lang.String[] getColumnDescriptions(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException {
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


	public java.lang.String[] getColumnUnits(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException {
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


	public java.lang.String[] getColumnUCDs(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException {
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


	public java.lang.String[][] getIndexes(java.lang.String catalogName, java.lang.String tableName) throws java.rmi.RemoteException {
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
	public String getConeSearch(String cat, String tab, double ra, double dec, double sr) 
	{
		return getConeSearch(cat, tab, ra, dec, sr);
	}
	
}
