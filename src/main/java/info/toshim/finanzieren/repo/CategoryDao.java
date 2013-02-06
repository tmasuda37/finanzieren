package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Category;

import java.util.List;

public interface CategoryDao
{
	public void save(Category category);

	public Category findById(int id);

	public List<Category> findAll();
}
