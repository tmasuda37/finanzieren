package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Member;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.repo.WalletDao;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/wallet")
public class WalletController
{
	@Autowired
	private WalletDao walletDao;

	@RequestMapping(method = RequestMethod.GET)
	public String registerNewWalletRecord(Model model)
	{
		// String userid = "12345678901234567890123456789012";
		// Date wlDate = new Date();
		// int wlKind = 0;
		// int wlCategory = 0;
		// Double wlAmount = 580.0;
		// int wlCurrency = 0;
		// String wlNote = "Starbucks";
		// Wallet wallet = new Wallet(userid, wlDate, wlKind, wlCategory,
		// wlAmount, wlCurrency, wlNote);
		// walletDao.register(wallet);
		model.addAttribute("newWalletRecord", new Wallet());
		return "addWalletRecord";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	List<Wallet> lookupMemberById(@PathVariable("id") Long id)
	{
		return walletDao.findAllOrderedByName();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerNewMember(@Valid @ModelAttribute("newWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		String userid = "12345678901234567890123456789012";
		Date wlDate = new Date();
		int wlKind = 0;
		int wlCategory = 0;
		Double wlAmount = wallet.getWlAmount();
		int wlCurrency = 0;
		String wlNote = "Starbucks";
		wallet = new Wallet(userid, wlDate, wlKind, wlCategory, wlAmount, wlCurrency, wlNote);
		walletDao.register(wallet);
		return "success";
	}
}
