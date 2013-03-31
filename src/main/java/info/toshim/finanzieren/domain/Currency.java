package info.toshim.finanzieren.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Currency implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@NotNull
	private String currency;

	public Currency()
	{
		super();
	}

	public Currency(int id)
	{
		super();
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	@Override
	public String toString()
	{
		return "Currency [id=" + id + ", currency=" + currency + "]";
	}
}