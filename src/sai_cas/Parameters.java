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
		String[] res={"cas_user","aspen"};
		return res;
	}
	public static String[] getAdminDBUserPasswd()
	{
		String[] res={"cas_admin","aspen"};
		return res;
	}

}