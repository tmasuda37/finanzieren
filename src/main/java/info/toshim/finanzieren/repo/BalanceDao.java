package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Balance;
import info.toshim.finanzieren.domain.BalancePk;

import java.util.List;

public interface BalanceDao
{
	public void save(Balance balance);

	public void update(Balance balance);

	public Balance findByBalance(BalancePk balancePk);

	public List<Balance> findByUserid(String userid);
}
