package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Kind;

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
public class KindDaoImpl implements KindDao
{
	@Autowired
	private EntityManager em;

	public void save(Kind kind)
	{
		em.persist(kind);
		em.flush();
		return;
	}

	public Kind findById(int id)
	{
		return em.find(Kind.class, id);
	}

	public List<Kind> findAll()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Kind> criteria = cb.createQuery(Kind.class);
		Root<Kind> kind = criteria.from(Kind.class);
		criteria.select(kind);
		return em.createQuery(criteria).getResultList();
	}
}