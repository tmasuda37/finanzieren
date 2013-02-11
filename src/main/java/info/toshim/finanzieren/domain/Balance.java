package info.toshim.finanzieren.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Balance implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private String userid;

	@OneToOne
	@JoinColumn
	private Currency currency;

	private Double sum = 0.0;

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public Double getSum()
	{
		return sum;
	}

	public void setSum(Double sum)
	{
		this.sum = sum;
	}
}
