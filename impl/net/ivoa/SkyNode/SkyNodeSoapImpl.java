/**
 * SkyNodeSoapImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.ivoa.SkyNode;

import sai_cas.db.*;
import sai_cas.*;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import org.apache.log4j.Logger;
import net.ivoa.SkyNode.MetaTable;

public class SkyNodeSoapImpl implements net.ivoa.SkyNode.SkyNodeSoap {
	static Logger logger = Logger.getLogger("net.ivoa.SkyNode.SkyNodeSoap");

	public net.ivoa.SkyNode.VOData performQuery(
			net.ivoa.www.xml.ADQL.v0_7_4.SelectType select,
			java.lang.String format) throws java.rmi.RemoteException
	{
		return null;
	}

	public net.ivoa.SkyNode.VOData executePlan(net.ivoa.SkyNode.ExecPlan plan)
			throws java.rmi.RemoteException
	{
		return null;
	}

	public net.ivoa.SkyNode.MetaColumn column(java.lang.String table,
			java.lang.String column) throws java.rmi.RemoteException
	{
		return null;
	}

	public net.ivoa.SkyNode.MetaColumn[] columns(java.lang.String table)
			throws java.rmi.RemoteException
	{
		String catalogName, tableName; 
		String tmp[] = table.split(".");
		Connection conn = null;
		DBInterface dbi = null;
		MetaColumn[] mca;

		if (tmp.length != 2)
		{
			return null;
		}
		catalogName = tmp[0];
		tableName = tmp[1];
		try
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			String[] columnNames = dbi.getColumnNames(catalogName, tableName);
			String[] columnDescriptions = dbi.getColumnDescriptions(catalogName, tableName);
			String[] columnUCDs = dbi.getColumnUCDs(catalogName, tableName);
			String[] columnUnits = dbi.getColumnUnits(catalogName, tableName);

			mca = new MetaColumn[columnNames.length];
			
			for(int i = 0; i < columnNames.length; i++)
			{
				MetaColumn mc = new MetaColumn();
				mc.setDescription(columnDescriptions[i]);
				mc.setName(columnNames[i]);
				mc.setUCD(columnUCDs[i]);
				mc.setUnit(columnUnits[i]);
				mca[i] = mc;
			}
		}
		catch (SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new java.rmi.RemoteException(e.getMessage());			
		}
		
		DBInterface.close(dbi, conn);
		
		return mca;
	}

	public nvo_region.RegionType footprint(nvo_region.RegionType region)
			throws java.rmi.RemoteException
	{
		return null;
	}

	public java.lang.String[] formats() throws java.rmi.RemoteException
	{
		return null;
	}

	public net.ivoa.SkyNode.MetaFunction[] functions()
			throws java.rmi.RemoteException
	{
		return null;
	}

	public net.ivoa.SkyNode.Availability getAvailability()
			throws java.rmi.RemoteException
	{
		return null;
	}

	public float queryCost(long planid,
			net.ivoa.www.xml.ADQL.v0_7_4.SelectType select)
			throws java.rmi.RemoteException
	{
		return -3;
	}

	public net.ivoa.SkyNode.MetaTable table(java.lang.String table)
			throws java.rmi.RemoteException
	{
		return null;
	}

	public net.ivoa.SkyNode.MetaTable[] tables()
			throws java.rmi.RemoteException 
	{
		Connection conn = null;
		DBInterface dbi = null;
		String[] catalogNamesArray = null;
		String[] tableNamesArray = null;
		List<String> catalogTableNamesList = null;
		List<String> catalogTableDescriptionsList = null;
		try 
		{
			conn = DBConnection.getPooledPerUserConnection();
			dbi = new DBInterface(conn);
			catalogNamesArray = dbi.getCatalogNames();
			catalogTableNamesList = new ArrayList<String>();
			catalogTableDescriptionsList = new ArrayList<String>();

			for (String cat : Arrays.asList(catalogNamesArray)) {
				tableNamesArray = dbi.getTableNames(cat);
				String catalogDescription = dbi.getCatalogDescription(cat);
				for (String tab : Arrays.asList(tableNamesArray)) {
					catalogTableNamesList.add(cat + "." + tab);
					String tableDescription = dbi.getTableDescription(cat, tab);
					if (tableDescription == null) {
						tableDescription = "";
					}
					catalogTableDescriptionsList.add(catalogDescription + "\n"
							+ tableDescription);
				}
			}
		} catch (SQLException e)
		{
			logger.error("Caught an exception... ", e);
			DBInterface.close(dbi, conn);
			throw new java.rmi.RemoteException(e.getMessage());
		}
		DBInterface.close(dbi, conn);
		net.ivoa.SkyNode.MetaTable[] metaTableArray = new net.ivoa.SkyNode.MetaTable[catalogTableNamesList
				.size()];
		int i = 0;
		Iterator<String> it = catalogTableDescriptionsList.iterator();
		for (String s : catalogTableNamesList)
		{
			net.ivoa.SkyNode.MetaTable mt = new net.ivoa.SkyNode.MetaTable();
			mt.setName(s);
			mt.setDescription(it.next());
			metaTableArray[i++] = mt;
		}
		return metaTableArray;
	}

}
