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

	public List<Wallet> findAllByCurrencyId(int currencyId);

	public List<Wallet> getExpSummaryGroupByCategory();

	public List<Wallet> getExpSummaryGroupByCategoryWithCurrency(Date startDate, Currency currency);
}
