package info.toshim.finanzieren.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(DailyAmountPk.class)
public class DailyAmount implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private String userid;

	@Id
	private int currencyid;

	@Id
	private Date date;

	private BigDecimal sum;

	@ManyToOne
	@JoinColumn
	private Currency currency;

	public DailyAmount()
	{
		super();
	}

	public DailyAmount(String userid, int currencyid, Date date, BigDecimal sum, Currency currency)
	{
		super();
		this.userid = userid;
		this.currencyid = currencyid;
		this.date = date;
		this.sum = sum;
		this.currency = currency;
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

	public BigDecimal getSum()
	{
		return sum;
	}

	public void setSum(BigDecimal sum)
	{
		this.sum = sum;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	@Override
	public String toString()
	{
		return "DailyAmount [userid=" + userid + ", currencyid=" + currencyid + ", date=" + date + ", sum=" + sum + ", currency=" + currency + "]";
	}
}