package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Balance;

public interface BalanceDao
{
	public void saveOrUpdate(Balance balance);

	public Balance findByUserId(String userid);
}
