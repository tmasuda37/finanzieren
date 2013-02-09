package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.mvc.core.ListOfDates;
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

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String displayExp(Model model)
	{
		List<Currency> listWlcurrency = currencyDao.findAll();
		List<Category> listWlcategory = categoryDao.findAll();
		ListOfDates listOfDates = new ListOfDates();
		List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
		Wallet wallet = new Wallet();
		model.addAttribute("regWalletRecord", wallet);
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		model.addAttribute("listWlDate", listWlDate);
		return "exp";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerExp(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		if (!result.hasErrors())
		{
			log.info("[OKAY] The validation error is not happened!");
			Kind kind = new Kind(1);
			wallet.setKind(kind);
			walletDao.save(wallet);
			return "redirect:/";
		} else
		{
			log.info("[OKAY] The validation error is not happened!");
			log.info(result.getAllErrors());
			List<Currency> listWlcurrency = currencyDao.findAll();
			List<Category> listWlcategory = categoryDao.findAll();
			ListOfDates listOfDates = new ListOfDates();
			List<String> listWlDate = listOfDates.getListOfDates(10, ListOfDates.DAY_MODE);
			model.addAttribute("regWalletRecord", wallet);
			model.addAttribute("listWlcurrency", listWlcurrency);
			model.addAttribute("listWlcategory", listWlcategory);
			model.addAttribute("listWlDate", listWlDate);
			return "exp";
		}
	}
}
