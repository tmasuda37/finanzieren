package info.toshim.finanzieren.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Wallet implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String userid = "a34256c6bc043f5e081c39cd58fb03f1";

	@NotNull
	private Date date;

	@ManyToOne
	@JoinColumn
	private Kind kind;

	@ManyToOne
	@JoinColumn
	private Category category;

	@NotNull
	@Digits(fraction = 2, integer = 7)
	private Double amount;

	@ManyToOne
	@JoinColumn
	private Currency currency;

	@Size(max = 256)
	private String note;

	public Wallet()
	{
		super();
		this.id = -1;
	}

	public Wallet(String userid, Date date, Kind kind, Category category, Double amount, Currency currency, String note)
	{
		super();
		this.userid = userid;
		this.date = date;
		this.kind = kind;
		this.category = category;
		this.amount = amount;
		this.currency = currency;
		this.note = note;
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

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Kind getKind()
	{
		return kind;
	}

	public void setKind(Kind kind)
	{
		this.kind = kind;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public Double getAmount()
	{
		return amount;
	}

	public void setAmount(Double amount)
	{
		this.amount = amount;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	@Override
	public String toString()
	{
		return "Wallet [id=" + id + ", userid=" + userid + ", date=" + date + ", kind=" + kind + ", category=" + category + ", amount=" + amount + ", currency=" + currency + ", note=" + note + "]";
	}
}