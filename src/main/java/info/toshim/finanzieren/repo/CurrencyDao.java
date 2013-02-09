package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Currency;

import java.util.List;

public interface CurrencyDao
{
	public void save(Currency currency);

	public Currency findById(int id);

	public List<Currency> findAll();
}
