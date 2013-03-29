package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.mvc.core.GetDatesForSql;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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

	public List<Wallet> findAllByCurrency(Currency currency)
	{
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		criteria.select(wallet);
		criteria.where(cb.equal(wallet.get("currency"), currency));
		criteria.orderBy(cb.desc(wallet.get("date")));
		return em.createQuery(criteria).getResultList();
	}

	public List<Wallet> findAllByDate(Date date)
	{
		/*
		 * Prepare SQL Input Data
		 */
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth(date);
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		criteria.select(wallet);
		criteria.where(cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.orderBy(cb.desc(wallet.get("date")));
		return em.createQuery(criteria).getResultList();
	}

	public List<Wallet> findAllByDateCurrency(Date date, Currency currency)
	{
		/*
		 * Prepare SQL Input Data
		 */
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth(date);
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		criteria.select(wallet);
		criteria.where(cb.equal(wallet.get("currency"), currency), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.orderBy(cb.desc(wallet.get("date")));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Wallet> getExpSummary()
	{
		/*
		 * Prepare SQL Input Data
		 */
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth();
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		Expression<BigDecimal> sumAmount = cb.sum(wallet.get("amount").as(BigDecimal.class));
		criteria.multiselect(wallet.get("category"), wallet.get("currency"), sumAmount);
		criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.groupBy(wallet.get("currency"), wallet.get("category"));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Wallet> getExpSummaryByDate(Date date)
	{
		/*
		 * Prepare SQL Input Data
		 */
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth(date);
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		Expression<BigDecimal> sumAmount = cb.sum(wallet.get("amount").as(BigDecimal.class));
		criteria.multiselect(wallet.get("category"), wallet.get("currency"), sumAmount);
		criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.groupBy(wallet.get("currency"), wallet.get("category"));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Wallet> getExpSummaryByDateCurrency(Date date, Currency currency)
	{
		/*
		 * Prepare SQL Input Data
		 */
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth(date);
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		Expression<BigDecimal> sumAmount = cb.sum(wallet.get("amount").as(BigDecimal.class));
		criteria.multiselect(wallet.get("category"), wallet.get("currency"), sumAmount);
		criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.equal(wallet.get("currency"), currency), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.groupBy(wallet.get("currency"), wallet.get("category"));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Wallet> getDailyAmountSummaryByCurrency(Currency currency)
	{
		/*
		 * Prepare SQL Input Data
		 */
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth();
		/*
		 * Start SQL
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Wallet> criteria = cb.createQuery(Wallet.class);
		Root<Wallet> wallet = criteria.from(Wallet.class);
		Expression<BigDecimal> sumAmount = cb.sum(wallet.get("amount").as(BigDecimal.class));
		criteria.multiselect(wallet.get("date"), wallet.get("currency"), sumAmount);
		criteria.where(cb.equal(wallet.get("kind").get("id"), Kind.EXP), cb.equal(wallet.get("currency"), currency), cb.greaterThanOrEqualTo(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(wallet.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.groupBy(wallet.get("date"));
		criteria.orderBy(cb.desc(wallet.get("date")));
		return em.createQuery(criteria).getResultList();
	}
}