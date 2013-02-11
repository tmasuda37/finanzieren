package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Balance;

public interface BalanceDao
{
	public Balance findByUserId(String userid);

	public void updateByUserId(Balance balance);
}
