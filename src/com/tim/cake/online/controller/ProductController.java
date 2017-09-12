package com.tim.cake.online.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.facade.ProductFacade;


@Controller
@RequestMapping("/product")
public class ProductController
{
	@Resource(name = "productFacade")
	private ProductFacade productFacade;

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

	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public String productList(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage)
	{
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(5);
		SearchResult<ProductData> searchResults = productFacade.getAllProductWithPagniation(page, null);
		model.addAttribute("searchResults", searchResults);
		return "home/index";
	}

	@RequestMapping(value = "/productDetail/{id}", method = RequestMethod.GET)
	public String productDetail(Model model, @PathVariable int id)
	{
		ProductData productData = productFacade.getProductById(id);
		if (productData == null)
		{
			return "product/productNotFound";
		}
		model.addAttribute("product", productData);
		return "product/productDetail";
	}
}
