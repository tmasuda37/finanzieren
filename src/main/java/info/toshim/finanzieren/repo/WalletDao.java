package info.toshim.finanzieren.repo;

import info.toshim.finanzieren.domain.Wallet;

import java.util.List;

public interface WalletDao
{
	public Wallet findById(Long id);

	public List<Wallet> findAllOrderedByName();

	public void register(Wallet wallet);
}
