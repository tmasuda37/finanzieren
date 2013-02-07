package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Currency;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.domain.Wallet;
import info.toshim.finanzieren.repo.CategoryDao;
import info.toshim.finanzieren.repo.WalletDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@RequestMapping(method = RequestMethod.GET)
	public String displayExp(Model model)
	{
		List<Currency> listWlcurrency = null;
		List<Category> listWlcategory = categoryDao.findAll();
		model.addAttribute("regWalletRecord", new Wallet());
		model.addAttribute("listWlcurrency", listWlcurrency);
		model.addAttribute("listWlcategory", listWlcategory);
		return "exp";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerExp(@Valid @ModelAttribute("regWalletRecord") Wallet wallet, BindingResult result, Model model)
	{
		log.info("It comes until here!");
		String userid = "12345678901234567890123456789012";
		Date wlDate = new Date();
		Kind wlKind = new Kind("TEST");
		Category wlCategory = new Category("FOOD");
		Double wlAmount = wallet.getWlAmount();
		Currency wlCurrency = new Currency("JPY");
		String wlNote = "Starbucks";
		wallet = new Wallet(userid, wlDate, wlKind, wlCategory, wlAmount, wlCurrency, wlNote);
		walletDao.save(wallet);
		return "exp";
	}
}
