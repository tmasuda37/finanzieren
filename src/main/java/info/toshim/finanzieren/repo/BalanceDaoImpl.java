package info.toshim.finanzieren.repo;

import java.util.List;

import info.toshim.finanzieren.domain.Balance;
import info.toshim.finanzieren.domain.Kind;
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
public class BalanceDaoImpl implements BalanceDao
{
	@Autowired
	private EntityManager em;

	public Balance findByUserId(String userid)
	{
		return em.find(Balance.class, userid);
	}

	public void updateByUserId(Balance balance)
	{
		em.persist(balance);
		em.flush();
		return;
	}
}