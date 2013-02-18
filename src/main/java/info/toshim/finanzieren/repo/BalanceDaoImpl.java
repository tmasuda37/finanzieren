package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Balance;
import info.toshim.finanzieren.domain.BalancePk;

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
public class BalanceDaoImpl implements BalanceDao
{
	private static final Logger log = Logger.getLogger(BalanceDaoImpl.class);

	@Autowired
	private EntityManager em;

	public void save(Balance balance)
	{
		em.persist(balance);
		em.flush();
		return;
	}

	public void update(Balance balance)
	{
		em.merge(balance);
		em.flush();
		return;
	}

	public Balance findByBalance(BalancePk balancePk)
	{
		return em.find(Balance.class, balancePk);
	}

	public List<Balance> findByUserid(String userid)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Balance> criteria = cb.createQuery(Balance.class);
		Root<Balance> balance = criteria.from(Balance.class);
		criteria.select(balance);
		criteria.where(cb.equal(balance.get("userid"), userid));
		return em.createQuery(criteria).getResultList();
	}
}