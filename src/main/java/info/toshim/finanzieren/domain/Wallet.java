package info.toshim.finanzieren.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Size;

@Entity
public class Wallet implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String userid;

	private Date wlDate;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(referencedColumnName = "id")
	private Kind wlKind;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(referencedColumnName = "id")
	private Category wlCategory;

	private Double wlAmount;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(referencedColumnName = "id")
	private Currency wlCurrency;

	@Size(max = 256)
	private String wlNote;

	public Wallet()
	{
		super();
	}

	public Wallet(String userid, Date wlDate, Kind wlKind, Category wlCategory, Double wlAmount, Currency wlCurrency, String wlNote)
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

	public Kind getWlKind()
	{
		return wlKind;
	}

	public void setWlKind(Kind wlKind)
	{
		this.wlKind = wlKind;
	}

	public Category getWlCategory()
	{
		return wlCategory;
	}

	public void setWlCategory(Category wlCategory)
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

	public Currency getWlCurrency()
	{
		return wlCurrency;
	}

	public void setWlCurrency(Currency wlCurrency)
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