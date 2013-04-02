package info.toshim.finanzieren.mvc.core;

public class FinanzierenTools
{
	public static String getFinanzierenUri(String orgUri)
	{
		return orgUri.replaceAll("/finanzieren/", "");
	}
}
