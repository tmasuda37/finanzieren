package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Wallet;

import java.util.List;

public interface WalletDao
{
	public void save(Wallet wallet);

	public Wallet findById(int id);

	public List<Wallet> findAll();
}
