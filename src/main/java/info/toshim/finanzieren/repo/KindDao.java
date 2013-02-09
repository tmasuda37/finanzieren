package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Kind;

import java.util.List;

public interface KindDao
{
	public void save(Kind kind);

	public Kind findById(int id);

	public List<Kind> findAll();
}
