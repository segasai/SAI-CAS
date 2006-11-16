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
			java.lang.String in2, java.lang.String in3)
	throws java.rmi.RemoteException
	{
		sai_cas.services.EditingServices.setCatalogInfo(in0, in1, in2, in3);
	}

}
