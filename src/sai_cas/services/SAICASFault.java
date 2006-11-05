package sai_cas.services;
import org.apache.axis2.AxisFault;
public class SAICASFault extends /*java.rmi.RemoteException*/org.apache.axis2.AxisFault
{
	public SAICASFault(String s)
	{
		super(s);
	}
	public SAICASFault(String s,String s1)
	{
		super(s,s1);
	}

/*	public XFault()
	{
		super();
	}
*/
}