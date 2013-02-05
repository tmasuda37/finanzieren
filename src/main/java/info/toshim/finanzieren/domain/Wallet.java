package info.toshim.finanzieren.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wallet
{
	@Id
	@GeneratedValue
	private int id;

	private String userid;

	private Date wlDate;

	private int wlKind;

	private int wlCategory;

	private Double wlAmount;

	private int wlCurrency;

	private String wlNote;

	public Wallet()
	{
	}

	public Wallet(String userid, Date wlDate, int wlKind, int wlCategory, Double wlAmount, int wlCurrency, String wlNote)
	{
		super();
		this.userid = userid;
		this.wlDate = wlDate;
		this.wlKind = wlKind;
		this.wlCategory = wlCategory;
		this.wlAmount = wlAmount;
		this.wlCurrency = wlCurrency;
		this.wlNote = wlNote;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public Date getWlDate()
	{
		return wlDate;
	}

	public void setWlDate(Date wlDate)
	{
		this.wlDate = wlDate;
	}

	public int getWlKind()
	{
		return wlKind;
	}

	public void setWlKind(int wlKind)
	{
		this.wlKind = wlKind;
	}

	public int getWlCategory()
	{
		return wlCategory;
	}

	public void setWlCategory(int wlCategory)
	{
		this.wlCategory = wlCategory;
	}

	public Double getWlAmount()
	{
		return wlAmount;
	}

	public void setWlAmount(Double wlAmount)
	{
		this.wlAmount = wlAmount;
	}

	public int getWlCurrency()
	{
		return wlCurrency;
	}

	public void setWlCurrency(int wlCurrency)
	{
		this.wlCurrency = wlCurrency;
	}

	public String getWlNote()
	{
		return wlNote;
	}

	public void setWlNote(String wlNote)
	{
		this.wlNote = wlNote;
	}
}
