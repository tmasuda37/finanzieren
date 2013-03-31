package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Wallet;

import java.util.Date;
import java.util.List;

public interface WalletDao
{
	public void save(Wallet wallet);

	public void update(Wallet wallet);

	public void delete(int id);

	public Wallet findById(int id);

	public List<Wallet> findAll();

	public List<Wallet> findAllByCurrency(Currency currency);

	public List<Wallet> findAllByDate(Date date);

	public List<Wallet> findAllByDateCurrency(Date date, Currency currency);

	public List<Wallet> getExpSummary();

	public List<Wallet> getExpSummaryByDate(Date date);

	public List<Wallet> getExpSummaryByDateCurrency(Date date, Currency currency);

	public List<Wallet> getDailyAmountSummaryByCurrencyDate(Currency currency, Date date);
}
