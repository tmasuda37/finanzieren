package info.toshim.finanzieren.mvc;

import info.toshim.finanzieren.domain.Category;
import info.toshim.finanzieren.domain.Kind;
import info.toshim.finanzieren.repo.CategoryDao;
import info.toshim.finanzieren.repo.KindDao;

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
@RequestMapping(value = "/setting")
public class SettingController
{
	private static final Logger log = Logger.getLogger(SettingController.class);

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private KindDao kindDao;

	/**
	 * 設定をクリックした時に実行される
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String root(Model model)
	{
		Category category = new Category();
		List<Kind> listKind = kindDao.findAll();
		model.addAttribute("listKind", listKind);
		model.addAttribute("regCategoryRecord", category);
		return "setting";
	}

	/**
	 * 分類登録をクリックした時に実行される
	 * 
	 * @param category
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.POST)
	public String registerCategory(@ModelAttribute("regCategoryRecord") @Valid Category category, BindingResult result, Model model)
	{
		List<Category> listCategory = categoryDao.findAllKind(category.getKind());
		int max = 0;
		for (int i = 0; i < listCategory.size(); i++)
		{
			if (max < listCategory.get(i).getId())
			{
				max = listCategory.get(i).getId();
			}
		}
		max += 1;
		categoryDao.save(new Category(max, category.getCategory(), category.getKind()));
		return "redirect:/setting";
	}
}