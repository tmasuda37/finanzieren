package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.domain.Wallet;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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

	public List<Wallet> findAllByCurrencyId(int currencyId)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		criteria.select(wallet);
		criteria.where(cb.equal(wallet.get("currency").get("id"), currencyId));
		criteria.orderBy(cb.desc(wallet.get("date")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Wallet> getExpSummaryGroupByCategory()
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		Expression<BigDecimal> sumAmount = cb.sum(wallet.get("amount").as(BigDecimal.class));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date thisMonth = cal.getTime();
		cal.add(Calendar.MONTH, +1);
		Date nextMonth = cal.getTime();
		criteria.multiselect(wallet.get("category"), wallet.get("currency"), sumAmount);
		criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), thisMonth), cb.lessThan(wallet.get("date").as(Date.class), nextMonth));
		criteria.groupBy(wallet.get("currency"), wallet.get("category"));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Wallet> getExpSummaryGroupByCategoryWithCurrency(Date startDate, Currency currency)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		Expression<BigDecimal> sumAmount = cb.sum(wallet.get("amount").as(BigDecimal.class));
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date thisMonth = cal.getTime();
		cal.add(Calendar.MONTH, +1);
		Date nextMonth = cal.getTime();
		criteria.multiselect(wallet.get("category"), wallet.get("currency"), sumAmount);
		if (currency.getId() != -1)
		{
			criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.equal(wallet.get("currency"), currency), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), thisMonth), cb.lessThan(wallet.get("date").as(Date.class), nextMonth));
		} else
		{
			criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), thisMonth), cb.lessThan(wallet.get("date").as(Date.class), nextMonth));
		}
		criteria.groupBy(wallet.get("currency"), wallet.get("category"));
		return em.createQuery(criteria).getResultList();
	}
}