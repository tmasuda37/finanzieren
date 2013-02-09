package info.toshim.finanzieren.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Kind implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String kind;

	public Kind()
	{
		super();
	}

	public Kind(int id)
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

	public String getKind()
	{
		return kind;
	}

	public void setKind(String kind)
	{
		this.kind = kind;
	}
}
