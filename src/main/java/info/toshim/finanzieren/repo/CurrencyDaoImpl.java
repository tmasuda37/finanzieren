package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;

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
public class CurrencyDaoImpl implements CurrencyDao
{
	@Autowired
	private EntityManager em;

	public void save(Currency currency)
	{
		em.persist(currency);
		em.flush();
		return;
	}

	public Currency findById(int id)
	{
		return em.find(Currency.class, id);
	}

	public List<Currency> findAll()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Currency> criteria = cb.createQuery(Currency.class);
		Root<Currency> currency = criteria.from(Currency.class);
		criteria.select(currency);
		return em.createQuery(criteria).getResultList();
	}
}