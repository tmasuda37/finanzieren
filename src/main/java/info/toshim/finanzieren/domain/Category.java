package info.toshim.finanzieren.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Category implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@NotNull
	private String category;

	@ManyToOne
	@JoinColumn
	private Kind kind;

	public Category()
	{
		super();
		this.id = -1;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public Kind getKind()
	{
		return kind;
	}

	public void setKind(Kind kind)
	{
		this.kind = kind;
	}

	@Override
	public String toString()
	{
		return "Category [id=" + id + ", category=" + category + "]";
	}
}