package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Category;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao
{
	@Autowired
	private EntityManager em;

	public void save(Category category)
	{
		em.persist(category);
		em.flush();
		return;
	}

	public Category findById(int id)
	{
		return em.find(Category.class, id);
	}

	public List<Category> findAll()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = cb.createQuery(Category.class);
		Root<Category> category = criteria.from(Category.class);
		criteria.select(category);
		return em.createQuery(criteria).getResultList();
	}

	public List<Category> findByKindId(int kindId)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = cb.createQuery(Category.class);
		Root<Category> category = criteria.from(Category.class);
		criteria.select(category);
		criteria.where(cb.equal(category.get("kind").get("id"), kindId));
		return em.createQuery(criteria).getResultList();
	}
}