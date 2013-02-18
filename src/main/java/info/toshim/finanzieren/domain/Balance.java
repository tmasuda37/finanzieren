package info.toshim.finanzieren.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(BalancePk.class)
public class Balance implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private String userid;

	@Id
	private int currencyid;

	private Double sum = 0.0;

	@ManyToOne
	@JoinColumn
	private Currency currency;

	public Balance()
	{
		super();
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

	public Double getSum()
	{
		return sum;
	}

	public void setSum(Double sum)
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
}