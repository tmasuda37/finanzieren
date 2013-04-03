package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Kind;

import java.util.List;

public interface CategoryDao
{
	public void save(Category category);

	public Category findById(int id);

	public List<Category> findAll();

	public List<Category> findAllKind(Kind kind);

	List<Category> findByKindId(int kindId);
}
