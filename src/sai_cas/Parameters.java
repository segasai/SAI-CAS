package sai_cas;

public class Parameters
{
	public static double getMaxConeSearchRadius()
	{
		return 3;
	}
	public static String getVOTableXSLURL()
	{
		return "http://vo.astronet.ru/files/VOTable2XHTML.xsl";
	}
	public static String[] getDefaultDBUserPasswd()
	{
		String[] res={"user","aspen"};
		return res;
	}
	public static String[] getDefaultTempDBUserPasswd()
	{
		String[] res={"user_tmp","aspen"};
		return res;
	}
	public static String[] getAdminDBUserPasswd()
	{
		String[] res={"admin","aspen"};
		return res;
	}

}