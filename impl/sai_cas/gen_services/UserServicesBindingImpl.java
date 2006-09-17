/**
 * UserServicesBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package sai_cas.gen_services;
import java.rmi.RemoteException;
import java.sql.SQLException;

import sai_cas.services.UserServices;
public class UserServicesBindingImpl implements sai_cas.gen_services.UserServices {

	public void createNewUser(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException 
	{
		try 
		{
			UserServices.createNewUser(in0, in1, in2, in3, in4, in5);
		}
		catch(SQLException e)
		{
			throw new RemoteException(e.toString());
		}
		
	}
	public String[][] listAllUsers(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException 
	{
		try 
		{
			return UserServices.listAllUsers(in0, in1);
		}
		catch(SQLException e)
		{
			throw new RemoteException(e.toString());
		}
		
	}

}
