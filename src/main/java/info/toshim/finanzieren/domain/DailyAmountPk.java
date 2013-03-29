package info.toshim.finanzieren.domain;

import java.io.Serializable;
import java.util.Date;

import org.jboss.logging.Logger;

public class DailyAmountPk implements Serializable
{
	private static final Logger log = Logger.getLogger(DailyAmountPk.class);

	private static final long serialVersionUID = 1L;

	private String userid;

	private int currencyid;

	private Date date;

	public DailyAmountPk()
	{
		super();
	}

	public DailyAmountPk(String userid, int currencyid, Date date)
	{
		super();
		this.userid = userid;
		this.currencyid = currencyid;
		this.date = date;
	}

	public int hashCode()
	{
		return super.hashCode();
	}

	public boolean equals(Object obj)
	{
		if (obj instanceof DailyAmountPk)
		{
			DailyAmountPk dailyAmountPk = (DailyAmountPk) obj;
			if (dailyAmountPk.getUserid().equals(userid) && dailyAmountPk.getCurrencyid() == currencyid && dailyAmountPk.getDate().compareTo(date) == 0)
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

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}