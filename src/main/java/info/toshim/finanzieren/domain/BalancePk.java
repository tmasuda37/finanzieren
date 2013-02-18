package info.toshim.finanzieren.domain;

import java.io.Serializable;

import org.jboss.logging.Logger;

public class BalancePk implements Serializable
{
	private static final Logger log = Logger.getLogger(BalancePk.class);

	private static final long serialVersionUID = 1L;

	private String userid;

	private int currencyid;

	public BalancePk()
	{
		super();
	}

	public BalancePk(String userid, int currencyid)
	{
		super();
		this.userid = userid;
		this.currencyid = currencyid;
	}

	public int hashCode()
	{
		return super.hashCode();
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof BalancePk)
		{
			BalancePk balancePk = (BalancePk) obj;
			if (balancePk.getUserid().equals(userid) && balancePk.getCurrencyid() == currencyid)
			{
				return true;
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public int getCurrencyid()
	{
		return currencyid;
	}

	public void setCurrencyid(int currencyid)
	{
		this.currencyid = currencyid;
	}
}