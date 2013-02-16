package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Balance;

import javax.persistence.EntityManager;

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

	public void saveOrUpdate(Balance balance)
	{
		em.persist(balance);
		em.flush();
		return;
	}

	public Balance findByUserId(String userid)
	{
		log.info("userid: " + userid);
		log.info("em: " + em);
		return em.find(Balance.class, userid);
	}
}