package com.tim.cake.online.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.facade.ProductFacade;
import com.tim.cake.online.form.ProductForm;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin")
public class AdminController
{
	private static final Logger LOG = Logger.getLogger(AdminController.class);

	public static final String REDIRECT_PREFIX = "redirect:";

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "productValidator")
	private Validator productValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model)
	{
		LOG.info("login admin page...");
		return "admin/index";
	}

	@RequestMapping(value = "/productManage", method = RequestMethod.GET)
	public String productManage(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage)
	{
		Pagination page = new Pagination();
		if (currentPage <= 0)
		{
			page.setCurrentPage(1);
		}
		else
		{
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(5);

		SearchResult<ProductData> searchResult = productFacade.getAllProductWithPagniation(page, null);
		model.addAttribute("searchResults", searchResult);
		return "admin/productList";
	}


	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String productDisplay(Model model)
	{
		ProductForm productForm = new ProductForm();
		model.addAttribute("productForm", productForm);
		return "admin/addProduct";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(Model model, ProductForm productForm, BindingResult bindingResult)
	{
		productValidator.validate(productForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			LOG.error("add product error...");
			model.addAttribute("productForm", productForm);
			return "admin/addProduct";
		}
		productFacade.save(productForm);
		return REDIRECT_PREFIX + "/admin/productManage";
	}

	@RequestMapping(value = "/queryAllParent", method = RequestMethod.GET)
	@ResponseBody
	public String queryParentProducts(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "id", defaultValue = "0", required = false) String id)
	{
		Pagination page = new Pagination();
		if (currentPage <= 0)
		{
			page.setCurrentPage(1);
		}
		else
		{
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(3);

		ProductData productData = new ProductData();
		productData.setName(name);
		productData.setId(Integer.parseInt(id));

		SearchResult<ProductData> searchResult = productFacade.getAllProductWithPagniation(page, productData);
		JSONObject data = JSONObject.fromObject(searchResult);
		return data.toString();
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String updateProduct(Model model)
	{

		return "admin/updateProduct";
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(Model model)
	{
		return "admin/productList";
	}


	@RequestMapping(value = "/customerManage", method = RequestMethod.GET)
	public String customerManage(Model model)
	{

		return "admin/customerList";
	}
}
