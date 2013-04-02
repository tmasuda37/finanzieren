package info.toshim.finanzieren.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
	private BigDecimal amount;

	@ManyToOne
	@JoinColumn
	private Currency currency;

	@Size(max = 256)
	private String note;

	@Transient
	private boolean card;

	public Wallet()
	{
		super();
	}

	public Wallet(String userid, Date date, Kind kind, Category category, BigDecimal amount, Currency currency, String note)
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

	public Wallet(Category category, Currency currency, BigDecimal amount)
	{
		super();
		this.category = category;
		this.currency = currency;
		this.amount = amount;
	}

	public Wallet(Date date, Currency currency, BigDecimal amount)
	{
		super();
		this.date = date;
		this.currency = currency;
		this.amount = amount;
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

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
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

	public boolean isCard()
	{
		return card;
	}

	public void setCard(boolean card)
	{
		this.card = card;
	}

	@Override
	public String toString()
	{
		return "Wallet [id=" + id + ", userid=" + userid + ", date=" + date + ", kind=" + kind + ", category=" + category + ", amount=" + amount + ", currency=" + currency + ", note=" + note + ", card=" + card + "]";
	}
}