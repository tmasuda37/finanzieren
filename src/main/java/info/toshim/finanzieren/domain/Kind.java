package info.toshim.finanzieren.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Kind implements Serializable
{
	public static final String EXP = "1";

	public static final String INC = "2";

	public static final String TRI = "3";

	public static final String TRO = "4";

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@NotNull
	private String kind;

	public Kind()
	{
		super();
		this.id = -1;
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

	@Override
	public String toString()
	{
		return "Kind [id=" + id + ", kind=" + kind + "]";
	}
}
