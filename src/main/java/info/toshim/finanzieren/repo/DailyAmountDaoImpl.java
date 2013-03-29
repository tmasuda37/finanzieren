package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.DailyAmount;
import info.toshim.finanzieren.mvc.core.GetDatesForSql;

import java.util.Date;
import java.util.HashMap;
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
public class DailyAmountDaoImpl implements DailyAmountDao
{
	@Autowired
	private EntityManager em;

	@Override
	public void save(DailyAmount dailyAmount)
	{
		em.persist(dailyAmount);
		em.flush();
		return;
	}

	@Override
	public void save(List<DailyAmount> listDailyAmount)
	{
		for (int i = 0; i < listDailyAmount.size(); i++)
		{
			em.persist(listDailyAmount.get(i));
			em.flush();
		}
		return;
	}

	@Override
	public void update(DailyAmount dailyAmount)
	{
		em.merge(dailyAmount);
		em.flush();
		return;
	}

	@Override
	public void update(List<DailyAmount> listDailyAmount)
	{
		for (int i = 0; i < listDailyAmount.size(); i++)
		{
			em.merge(listDailyAmount.get(i));
			em.flush();
		}
		return;
	}

	@Override
	public List<DailyAmount> findAllByUseidCurrencyDate(String userid, Currency currency)
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
		CriteriaQuery<DailyAmount> criteria = cb.createQuery(DailyAmount.class);
		Root<DailyAmount> dailyAmount = criteria.from(DailyAmount.class);
		criteria.select(dailyAmount);
		criteria.where(cb.equal(dailyAmount.get("userid"), userid), cb.equal(dailyAmount.get("currency"), currency), cb.greaterThanOrEqualTo(dailyAmount.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_START_DATE)), cb.lessThan(dailyAmount.get("date").as(Date.class), map.get(GetDatesForSql.HM_KEY_END_DATE)));
		criteria.orderBy(cb.desc(dailyAmount.get("date")));
		return em.createQuery(criteria).getResultList();
	}
}
