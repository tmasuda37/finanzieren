package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Balance;
import info.toshim.finanzieren.domain.BalancePk;
import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.mvc.core.ListOfDates;
import info.toshim.finanzieren.repo.BalanceDao;
import info.toshim.finanzieren.repo.CategoryDao;
import info.toshim.finanzieren.repo.CurrencyDao;
import info.toshim.finanzieren.repo.KindDao;
import info.toshim.finanzieren.repo.WalletDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class WalletController
{
	private static final Logger log = Logger.getLogger(WalletController.class);

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CurrencyDao currencyDao;

	@Autowired
	private KindDao kindDao;

	@Autowired
	private BalanceDao balanceDao;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayStatus(Model model)
	{
		return "redirect:/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String displayList(Model model)
	{
		List<Wallet> listWallet = walletDao.findAll();
		List<Balance> listBalance = balanceDao.findByUserid("a34256c6bc043f5e081c39cd58fb03f1");
		model.addAttribute("listWallet", listWallet);
		model.addAttribute("listBalance", listBalance);
		return "list";
	}

	@RequestMapping(value = "/list/{id}/delete", method = RequestMethod.GET)
	public String deleteList(@PathVariable("id") int id)
	{
		Wallet wallet = walletDao.findById(id);
		this.calcBalanceByDeleteRecord(wallet);
		walletDao.delete(id);
		return "redirect:/list";
	}

	@RequestMapping(value = "/exp", method = RequestMethod.GET)
	public String displayExp(Model model)
	{
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findExpAll();
		ListOfDates listOfDates = new ListOfDates();
		List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
		Wallet wallet = new Wallet();
		model.addAttribute("regWalletRecord", wallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		model.addAttribute("listWlDate", listWlDate);
		return "exp";
	}

	@RequestMapping(value = "/exp", method = RequestMethod.POST)
	public String registerExp(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		if (!result.hasErrors())
		{
			walletDao.save(wallet);
			this.calcBalanceByNewRecord(wallet);
			return "redirect:/exp";
		} else
		{
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findExpAll();
			ListOfDates listOfDates = new ListOfDates();
			List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
			model.addAttribute("regWalletRecord", wallet);
			model.addAttribute("listWlcurrency", listWlcurrency);
			model.addAttribute("listWlcategory", listWlcategory);
			model.addAttribute("listWlDate", listWlDate);
			return "exp";
		}
	}

	@RequestMapping(value = "/inc", method = RequestMethod.GET)
	public String displayInc(Model model)
	{
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findIncAll();
		ListOfDates listOfDates = new ListOfDates();
		List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
		Wallet wallet = new Wallet();
		model.addAttribute("regWalletRecord", wallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		model.addAttribute("listWlDate", listWlDate);
		return "inc";
	}

	@RequestMapping(value = "/inc", method = RequestMethod.POST)
	public String registerInc(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		if (!result.hasErrors())
		{
			walletDao.save(wallet);
			this.calcBalanceByNewRecord(wallet);
			return "redirect:/inc";
		} else
		{
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findIncAll();
			ListOfDates listOfDates = new ListOfDates();
			List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
			model.addAttribute("regWalletRecord", wallet);
			model.addAttribute("listWlcurrency", listWlcurrency);
			model.addAttribute("listWlcategory", listWlcategory);
			model.addAttribute("listWlDate", listWlDate);
			return "inc";
		}
	}

	private void calcBalanceByNewRecord(Wallet wallet)
	{
		BalancePk balancePk = new BalancePk(wallet.getUserid(), wallet.getCurrency().getId());
		Balance balance = balanceDao.findByBalance(balancePk);
		if (balance == null)
		{
			balance = new Balance();
			balance.setUserid(wallet.getUserid());
			balance.setCurrencyid(wallet.getCurrency().getId());
			balance.setCurrency(wallet.getCurrency());
			balanceDao.save(balance);
		}
		Double tmpAmount = 0.0;
		if (wallet.getKind().getId() % 2 != 0)
		{
			tmpAmount = -1 * wallet.getAmount();
		} else
		{
			tmpAmount = wallet.getAmount();
		}
		balance.setSum(tmpAmount + balance.getSum());
		balance.setCurrency(wallet.getCurrency());
		balanceDao.update(balance);
	}

	private void calcBalanceByDeleteRecord(Wallet wallet)
	{
		BalancePk balancePk = new BalancePk(wallet.getUserid(), wallet.getCurrency().getId());
		Balance balance = balanceDao.findByBalance(balancePk);
		if (balance == null)
		{
			balance = new Balance();
			balance.setUserid(wallet.getUserid());
			balance.setCurrencyid(wallet.getCurrency().getId());
			balance.setCurrency(wallet.getCurrency());
			balanceDao.save(balance);
		}
		Double tmpAmount = 0.0;
		if (wallet.getKind().getId() % 2 != 0)
		{
			tmpAmount = wallet.getAmount();
		} else
		{
			tmpAmount = -1 * wallet.getAmount();
		}
		balance.setSum(tmpAmount + balance.getSum());
		balance.setCurrency(wallet.getCurrency());
		balanceDao.update(balance);
	}
}