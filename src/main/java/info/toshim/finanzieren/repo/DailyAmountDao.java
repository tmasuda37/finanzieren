package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.DailyAmount;

import java.util.List;

public interface DailyAmountDao
{
	public void save(DailyAmount dailyAmount);

	public void save(List<DailyAmount> listDailyAmount);

	public void update(DailyAmount dailyAmount);

	public void update(List<DailyAmount> listDailyAmount);

	public List<DailyAmount> findAllByUseidCurrencyDate(String userid, Currency currency);
}