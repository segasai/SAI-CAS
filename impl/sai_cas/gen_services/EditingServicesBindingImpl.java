/**
 * EditingServicesBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sai_cas.gen_services;
import sai_cas.services.EditingServices;

public class EditingServicesBindingImpl implements sai_cas.gen_services.EditingServices
{

	public void renameTable(String catalog, String table, String newTable, String user, String password) throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.renameTable(catalog, table, newTable, user, password);
	}

	public void renameColumn(String catalog, String table, String column, String newColumn, String user, String password) throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.renameColumn(catalog, table, column, newColumn, user, password);
	}


	public void setUCD(java.lang.String in0, java.lang.String in1,
			java.lang.String in2, java.lang.String in3, java.lang.String in4,
			java.lang.String in5) throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setUCD(in0, in1, in2, in3, in4, in5);
	}

	public void setUnit(java.lang.String in0, java.lang.String in1,
			java.lang.String in2, java.lang.String in3, java.lang.String in4,
			java.lang.String in5) throws java.rmi.RemoteException {
		sai_cas.services.EditingServices.setUnit(in0, in1, in2, in3, in4, in5);
	}

	public void setColumnDescription(java.lang.String in0,
			java.lang.String in1, java.lang.String in2, java.lang.String in3,
			java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setColumnDescription(in0, in1, in2, in3, in4, in5);
	}

	public void setColumnInfo(java.lang.String in0, java.lang.String in1,
			java.lang.String in2, java.lang.String in3, java.lang.String in4,
			java.lang.String in5) throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setColumnInfo(in0, in1, in2, in3, in4, in5);
	}

	public void setTableDescription(java.lang.String in0, java.lang.String in1,
			java.lang.String in2, java.lang.String in3, java.lang.String in4)
	throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setTableDescription(in0, in1, in2, in3, in4);
	}

	public void setTableInfo(java.lang.String in0, java.lang.String in1,
			java.lang.String in2, java.lang.String in3, java.lang.String in4)
	throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setTableInfo(in0, in1, in2, in3, in4);
	}

	public void setCatalogDescription(java.lang.String in0,
			java.lang.String in1, java.lang.String in2, java.lang.String in3)
	throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setCatalogDescription(in0, in1, in2, in3);
	}

	public void setCatalogInfo(java.lang.String in0, java.lang.String in1,
			java.lang.String in2, java.lang.String in3, java.lang.String in4)
	throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setCatalogInfo(in0, in1, in2, in3, in4);
	}

}
