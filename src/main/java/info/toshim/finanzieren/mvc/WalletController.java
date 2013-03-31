package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Balance;
import info.toshim.finanzieren.domain.BalancePk;
import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.DailyAmount;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.mvc.core.DateTools;
import info.toshim.finanzieren.mvc.core.GetDatesForSql;
import info.toshim.finanzieren.mvc.core.ListOfDates;
import info.toshim.finanzieren.repo.BalanceDao;
import info.toshim.finanzieren.repo.CategoryDao;
import info.toshim.finanzieren.repo.CurrencyDao;
import info.toshim.finanzieren.repo.DailyAmountDao;
import info.toshim.finanzieren.repo.KindDao;
import info.toshim.finanzieren.repo.WalletDao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

	@Autowired
	private DailyAmountDao dailyAmountDao;

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
		Date date = new Date();
		ListOfDates listOfDates = new ListOfDates();
		Wallet wallet = new Wallet();
		wallet.setCurrency(new Currency(1));
		List<Balance> listBalance = balanceDao.findByUserid("a34256c6bc043f5e081c39cd58fb03f1");
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<DailyAmount> listDailyAmount = dailyAmountDao.findAllByUseidCurrency("a34256c6bc043f5e081c39cd58fb03f1", wallet.getCurrency());
		List<String> listWlDate = listOfDates.getListOfDates(12, ListOfDates.MONTH_MODE);
		List<Wallet> listWallet = walletDao.findAllByDateCurrency(date, wallet.getCurrency(), 31);
		List<Wallet> listSummary = walletDao.getExpSummaryByDateCurrency(date, wallet.getCurrency());
		model.addAttribute("listBalance", listBalance);
		model.addAttribute("listDailyAmount", listDailyAmount);
		model.addAttribute("listSummary", listSummary);
		model.addAttribute("listWallet", listWallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlDate", listWlDate);
		model.addAttribute("regWalletRecord", wallet);
		return "list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String displayListWithData(@ModelAttribute("regWalletRecord") Wallet wallet, Model model)
	{
		ListOfDates listOfDates = new ListOfDates();
		runRefreshBalance(wallet);
		runRefreshDailyAmount(wallet);
		List<Balance> listBalance = balanceDao.findByUserid("a34256c6bc043f5e081c39cd58fb03f1");
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<DailyAmount> listDailyAmount = dailyAmountDao.findAllByUseidCurrencyDate("a34256c6bc043f5e081c39cd58fb03f1", wallet.getCurrency(), wallet.getDate());
		List<String> listWlDate = listOfDates.getListOfDates(12, ListOfDates.MONTH_MODE);
		List<Wallet> listWallet = walletDao.findAllByDateCurrency(wallet.getDate(), wallet.getCurrency(), 31);
		List<Wallet> listSummary = walletDao.getExpSummaryByDateCurrency(wallet.getDate(), wallet.getCurrency());
		model.addAttribute("listBalance", listBalance);
		model.addAttribute("listDailyAmount", listDailyAmount);
		model.addAttribute("listSummary", listSummary);
		model.addAttribute("listWallet", listWallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlDate", listWlDate);
		model.addAttribute("regWalletRecord", wallet);
		return "list";
	}

	private void runRefreshBalance(Wallet wallet)
	{
		BigDecimal refreshSum;
		String userid = "a34256c6bc043f5e081c39cd58fb03f1";
		refreshSum = new BigDecimal(0.0);
		List<Wallet> listWallet = walletDao.findAllByCurrency(wallet.getCurrency());
		for (int j = 0; j < listWallet.size(); j++)
		{
			if (listWallet.get(j).getKind().getId() % 2 != 0)
			{
				refreshSum = refreshSum.subtract(listWallet.get(j).getAmount());
			} else
			{
				refreshSum = refreshSum.add(listWallet.get(j).getAmount());
			}
		}
		BalancePk balancePk = new BalancePk(userid, wallet.getCurrency().getId());
		Balance balance = balanceDao.findByBalance(balancePk);
		if (balance != null)
		{
			balance.setSum(refreshSum);
			balanceDao.update(balance);
		} else
		{
			balance = new Balance();
			balance.setUserid(userid);
			balance.setCurrencyid(wallet.getCurrency().getId());
			balance.setCurrency(wallet.getCurrency());
			balance.setSum(refreshSum);
			balanceDao.save(balance);
		}
	}

	private void runRefreshDailyAmount(Wallet wallet)
	{
		String userid = "a34256c6bc043f5e081c39cd58fb03f1";
		GetDatesForSql getDatesForSql = new GetDatesForSql();
		HashMap<String, Date> map = getDatesForSql.getFirstLastDateOfMonth(wallet.getDate());
		int days = DateTools.getNumInDates(map.get(GetDatesForSql.HM_KEY_START_DATE), map.get(GetDatesForSql.HM_KEY_END_DATE));
		List<DailyAmount> listDailyAmount = new ArrayList<DailyAmount>(days);
		Calendar cal = Calendar.getInstance();
		cal.setTime(map.get(GetDatesForSql.HM_KEY_START_DATE));
		for (int j = 0; j < days; j++)
		{
			listDailyAmount.add(new DailyAmount(userid, wallet.getCurrency().getId(), cal.getTime(), new BigDecimal(0.0), wallet.getCurrency()));
			cal.add(Calendar.DATE, +1);
		}
		dailyAmountDao.update(listDailyAmount);
		List<Wallet> listWallet = walletDao.getDailyAmountSummaryByCurrencyDate(wallet.getCurrency(), wallet.getDate());
		listDailyAmount = new ArrayList<DailyAmount>(listWallet.size());
		for (int j = 0; j < listWallet.size(); j++)
		{
			listDailyAmount.add(new DailyAmount(userid, wallet.getCurrency().getId(), listWallet.get(j).getDate(), listWallet.get(j).getAmount(), wallet.getCurrency()));
		}
		dailyAmountDao.update(listDailyAmount);
	}

	@RequestMapping(value = "/list/{id}/delete", method = RequestMethod.GET)
	public String deleteList(@PathVariable("id") int id)
	{
		walletDao.delete(id);
		return "redirect:/refresh";
	}

	@RequestMapping(value = "/list/{id}/edit", method = RequestMethod.GET)
	public String editList(@PathVariable("id") int id, Model model)
	{
		Wallet wallet = walletDao.findById(id);
		String retPath = "";
		switch (wallet.getKind().getId())
		{
		case 1:
			retPath = "exp";
			break;
		case 2:
			retPath = "inc";
			break;
		case 3:
			retPath = "tro";
			break;
		case 4:
			retPath = "tri";
			break;
		}
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findByKindId(wallet.getKind().getId());
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(wallet.getDate());
		List<String> listWlDate = new ArrayList<String>();
		listWlDate.add(strDate);
		model.addAttribute("regWalletRecord", wallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		model.addAttribute("listWlDate", listWlDate);
		return retPath;
	}

	@RequestMapping(value = "/list/{id}/edit", method = RequestMethod.POST)
	public String editList(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		walletDao.update(wallet);
		return "redirect:/list";
	}

	@RequestMapping(value = "/exp", method = RequestMethod.GET)
	public String displayExp(Model model)
	{
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findByKindId(1);
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
	public String registerExp(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model) throws IllegalAccessException, InvocationTargetException
	{
		if (!result.hasErrors())
		{
			if (wallet.isCard())
			{
				wallet.setNote("Debit/Credit Card/Withdrawal");
				walletDao.save(wallet);
				wallet.setId(-1);
				Kind kind = kindDao.findById(4);
				wallet.setKind(kind);
				Category category = categoryDao.findById(401);
				wallet.setCategory(category);
				walletDao.save(wallet);
			} else
			{
				walletDao.save(wallet);
			}
			return "redirect:/exp";
		} else
		{
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findByKindId(1);
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
		List<Category> listWlcategory = categoryDao.findByKindId(2);
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
			return "redirect:/inc";
		} else
		{
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findByKindId(2);
			ListOfDates listOfDates = new ListOfDates();
			List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
			model.addAttribute("regWalletRecord", wallet);
			model.addAttribute("listWlcurrency", listWlcurrency);
			model.addAttribute("listWlcategory", listWlcategory);
			model.addAttribute("listWlDate", listWlDate);
			return "inc";
		}
	}

	@RequestMapping(value = "/tro", method = RequestMethod.GET)
	public String displayTro(Model model)
	{
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findByKindId(3);
		ListOfDates listOfDates = new ListOfDates();
		List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
		Wallet wallet = new Wallet();
		model.addAttribute("regWalletRecord", wallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		model.addAttribute("listWlDate", listWlDate);
		return "tro";
	}

	@RequestMapping(value = "/tro", method = RequestMethod.POST)
	public String registerTro(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		if (!result.hasErrors())
		{
			walletDao.save(wallet);
			return "redirect:/tro";
		} else
		{
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findByKindId(3);
			ListOfDates listOfDates = new ListOfDates();
			List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
			model.addAttribute("regWalletRecord", wallet);
			model.addAttribute("listWlcurrency", listWlcurrency);
			model.addAttribute("listWlcategory", listWlcategory);
			model.addAttribute("listWlDate", listWlDate);
			return "tro";
		}
	}

	@RequestMapping(value = "/tri", method = RequestMethod.GET)
	public String displayTri(Model model)
	{
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findByKindId(4);
		ListOfDates listOfDates = new ListOfDates();
		List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
		Wallet wallet = new Wallet();
		model.addAttribute("regWalletRecord", wallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		model.addAttribute("listWlDate", listWlDate);
		return "tri";
	}

	@RequestMapping(value = "/tri", method = RequestMethod.POST)
	public String registerTri(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		if (!result.hasErrors())
		{
			walletDao.save(wallet);
			return "redirect:/tri";
		} else
		{
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findByKindId(4);
			ListOfDates listOfDates = new ListOfDates();
			List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
			model.addAttribute("regWalletRecord", wallet);
			model.addAttribute("listWlcurrency", listWlcurrency);
			model.addAttribute("listWlcategory", listWlcategory);
			model.addAttribute("listWlDate", listWlDate);
			return "tri";
		}
	}
}