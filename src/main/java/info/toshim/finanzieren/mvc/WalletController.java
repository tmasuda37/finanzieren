package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Balance;
import info.toshim.finanzieren.domain.BalancePk;
import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.mvc.core.ListOfDates;
import info.toshim.finanzieren.repo.BalanceDao;
import info.toshim.finanzieren.repo.CategoryDao;
import info.toshim.finanzieren.repo.CurrencyDao;
import info.toshim.finanzieren.repo.KindDao;
import info.toshim.finanzieren.repo.WalletDao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public String runRefreshBalance(Model model)
	{
		BigDecimal refreshSum;
		String userid = "a34256c6bc043f5e081c39cd58fb03f1";
		List<Currency> listCurrency = currencyDao.findAll();
		log.info("[listCurrency] " + listCurrency.size());
		for (int i = 0; i < listCurrency.size(); i++)
		{
			refreshSum = new BigDecimal(0.0);
			List<Wallet> listWallet = walletDao.findAllByCurrencyId(listCurrency.get(i).getId());
			log.info("[listWallet] " + listWallet.size());
			for (int j = 0; j < listWallet.size(); j++)
			{
				log.info("[listWallet] " + listWallet.get(j).getAmount());
				if (listWallet.get(j).getKind().getId() % 2 != 0)
				{
					refreshSum = refreshSum.subtract(listWallet.get(j).getAmount());
				} else
				{
					refreshSum = refreshSum.add(listWallet.get(j).getAmount());
				}
			}
			log.info("[Total Sum] ############## " + refreshSum);
			BalancePk balancePk = new BalancePk(userid, listCurrency.get(i).getId());
			Balance balance = balanceDao.findByBalance(balancePk);
			if (balance != null)
			{
				balance.setSum(refreshSum);
				balanceDao.update(balance);
			} else
			{
				balance = new Balance();
				balance.setUserid(userid);
				balance.setCurrencyid(listCurrency.get(i).getId());
				balance.setCurrency(listCurrency.get(i));
				balance.setSum(refreshSum);
				balanceDao.save(balance);
			}
		}
		return "redirect:/list";
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
			walletDao.save(wallet);
			if (wallet.isCard())
			{
				wallet.setId(-1);
				Kind kind = kindDao.findById(4);
				wallet.setKind(kind);
				Category category = categoryDao.findById(401);
				wallet.setCategory(category);
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