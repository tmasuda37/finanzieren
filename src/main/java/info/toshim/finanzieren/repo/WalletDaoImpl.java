package info.toshim.finanzieren.repo;

import java.util.List;

import info.toshim.finanzieren.domain.Wallet;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WalletDaoImpl implements WalletDao
{
	@Autowired
	private EntityManager em;

	public void register(Wallet wallet)
	{
		em.persist(wallet);
		return;
	}

	@Override
	public Wallet findById(Long id)
	{
		return em.find(Wallet.class, id);
	}

	@Override
	public List<Wallet> findAllOrderedByName()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		/*
		 * Swap criteria statements if you would like to try out type-safe
		 * criteria queries, a new feature in JPA 2.0
		 * criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		 */
		criteria.select(wallet);
		return em.createQuery(criteria).getResultList();
	}
}
