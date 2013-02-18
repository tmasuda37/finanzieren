package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Wallet;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class WalletDaoImpl implements WalletDao
{
	private static final Logger log = Logger.getLogger(WalletDaoImpl.class);

	@Autowired
	private EntityManager em;

	public void save(Wallet wallet)
	{
		em.persist(wallet);
		em.flush();
		return;
	}

	public void update(Wallet wallet)
	{
		em.merge(wallet);
		em.flush();
		return;
	}

	public void delete(int id)
	{
		findById(id);
		em.remove(findById(id));
		return;
	}

	public Wallet findById(int id)
	{
		return em.find(Wallet.class, id);
	}

	public List<Wallet> findAll()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		criteria.select(wallet).orderBy(cb.desc(wallet.get("date")));
		return em.createQuery(criteria).getResultList();
	}
}