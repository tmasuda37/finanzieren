package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.DailyAmount;
import info.toshim.finanzieren.domain.DailyAmountPk;

import java.util.Date;
import java.util.List;

public interface DailyAmountDao
{
	public void save(DailyAmount dailyAmount);

	public void save(List<DailyAmount> listDailyAmount);

	public void update(DailyAmount dailyAmount);

	public void update(List<DailyAmount> listDailyAmount);

	public DailyAmount findByDailyAmount(DailyAmountPk dailyAmountPk);

	public List<DailyAmount> findAllByUseidCurrency(String userid, Currency currency);

	public List<DailyAmount> findAllByUseidCurrencyDate(String userid, Currency currency, Date date);
}