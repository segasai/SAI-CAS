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



package sai_cas.gen_services;

import java.sql.Date; 
import java.util.Calendar; 

import org.apache.log4j.Logger;

import sai_cas.services.MainAxisServices;


public class MainAxisServicesBindingImpl implements sai_cas.gen_services.MainAxisServices
{
	static Logger logger = Logger.getLogger("sai_cas.AXIS_SERVICES");
	public void insertCatalogFromURI(java.lang.String uriCatalog,
		java.lang.String adminUser,
		java.lang.String adminPassword) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalogFromURI...");
//		logger.debug("The following catalogue is being inserted " + uriCatalog);
		
		sai_cas.services.MainAxisServices.insertCatalogFromURI(uriCatalog,
			adminUser, adminPassword);
	}

	public void insertCatalog(java.lang.String catalogString,
		java.lang.String adminUser,
		java.lang.String adminPassword) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalog...");
//		logger.debug("The following catalogue is being inserted" + catalogString);
		
		try
		{
			sai_cas.services.MainAxisServices.insertCatalog(catalogString,
				adminUser, adminPassword);
		}
		catch (java.rmi.RemoteException e)
		{
			throw new java.rmi.RemoteException(e.getMessage());
		}
		catch (java.lang.NullPointerException e)
		{
			throw new java.rmi.RemoteException(e.getMessage());
		}

	}



	public void insertTable(java.lang.String catalogString,
		java.lang.String adminUser,
		java.lang.String adminPassword) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalog...");
//		logger.debug("The following catalogue is being inserted" + catalogString);
		
		try
		{
			sai_cas.services.MainAxisServices.insertTable(catalogString,
				adminUser, adminPassword);
		}
		catch (java.rmi.RemoteException e)
		{
			throw new java.rmi.RemoteException(e.getMessage());
		}
		catch (java.lang.NullPointerException e)
		{
			throw new java.rmi.RemoteException(e.getMessage());
		}
	}

	public void insertCatalogFromVotable(java.lang.String catalogString, String user, String password) throws java.rmi.RemoteException
	{
		logger.info("Running insertCatalogFromVotable...");
//		logger.debug("The following catalogue is being inserted" + catalogString);
		
		sai_cas.services.MainAxisServices.insertCatalogFromVotable(catalogString,user,password);
	}

	public String dumpCatalog(java.lang.String catalogName,
		java.lang.String adminUser,
		java.lang.String adminPassword) throws java.rmi.RemoteException
	{
		logger.info("Dumping the Catalog " + catalogName + "...");
		
		try
		{
			return sai_cas.services.MainAxisServices.dumpCatalog(catalogName,
				adminUser, adminPassword);
		}
		catch (java.rmi.RemoteException e)
		{
			throw new java.rmi.RemoteException(e.getMessage());
		}
		catch (java.lang.NullPointerException e)
		{
			throw new java.rmi.RemoteException(e.getMessage());
		}

	}


	public void deleteCatalog(String catalog, String user, String password) throws java.rmi.RemoteException
	{
		MainAxisServices.deleteCatalog(catalog, user, password);
	}

	public void deleteTable(String catalog, String table, String user, String password) throws java.rmi.RemoteException
	{
		MainAxisServices.deleteTable(catalog, table, user, password);
	}


	public java.lang.String[] getCatalogNames() throws java.rmi.RemoteException
	{
		return MainAxisServices.getCatalogNames();
	}

	public java.lang.String[] getCatalogNames(String user, String password) throws java.rmi.RemoteException
	{
		return MainAxisServices.getCatalogNames(user, password);
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

	public java.lang.String getCatalogInfo(String cat, String user, String password) throws java.rmi.RemoteException
	{
		try 
		{
			return MainAxisServices.getCatalogInfo(cat, user, password);
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

	public java.lang.String getCatalogDescription(String cat, String user, String password) throws java.rmi.RemoteException
	{
		try 
		{
			return MainAxisServices.getCatalogDescription(cat, user, password);
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

	public java.lang.String[] getTableNames(java.lang.String catalogName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getTableNames(catalogName, user, password);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}

	public java.lang.String getTableDescription(java.lang.String catalogName, String tableName) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getTableDescription(catalogName, tableName);
		}
		catch(Exception e)
		{
			logger.error("Catched exception ",e);
			return null;			
		}
	}

	public java.lang.String getTableDescription(java.lang.String catalogName, String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getTableDescription(catalogName, tableName, user, password);
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

	public java.lang.String[] getColumnNames(java.lang.String catalogName, java.lang.String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnNames(catalogName, tableName, user, password);
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

	public java.lang.String[] getColumnInfos(java.lang.String catalogName, java.lang.String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnInfos(catalogName, tableName, user, password);
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
	

	public java.lang.String[] getColumnDescriptions(java.lang.String catalogName, java.lang.String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnDescriptions(catalogName, tableName, user, password);
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

	public java.lang.String[] getColumnUnits(java.lang.String catalogName, java.lang.String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnUnits(catalogName, tableName, user, password);
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


	public java.lang.String[] getColumnDatatypes(java.lang.String catalogName, java.lang.String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnDatatypes(catalogName, tableName, user, password);
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




	public java.lang.String[] getColumnUCDs(java.lang.String catalogName, java.lang.String tableName, String user, String password) throws java.rmi.RemoteException
	{
		try
		{
			return MainAxisServices.getColumnUCDs(catalogName, tableName, user, password);
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

	public long getTableCount(String catalogName, String tableName)
	{
		return MainAxisServices.getTableCount(catalogName, tableName);
	}

	public long getTableCount(java.lang.String catalogName, String tableName, String user, String password)
	{
		return MainAxisServices.getTableCount(catalogName, tableName, user, password);
	}

	
	public String getConeSearchAsString(String cat, String tab, double ra, double dec, double sr, String format) 
	{
		return MainAxisServices.getConeSearchAsString(cat, tab, ra, dec, sr, format);
	}


	public String getConeSearchAsString_withColumns(String cat, String tab, double ra, double dec, double sr, String format, String columnList[])
	{
		return MainAxisServices.getConeSearchAsString(cat, tab, ra, dec, sr, format, columnList);
	}
	
	public Calendar getDBLastChangedDate()
	{
		return (MainAxisServices.getDBLastChangedDate());
	}

	
}
