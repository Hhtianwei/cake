package com.tim.cake.online.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.facade.ProductFacade;


@Controller
public class HomePageController
{
	private static final Logger LOG = Logger.getLogger(HomePageController.class);

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String findUser(Model model)
	{
		LOG.info("visit home page...");

		Pagination page = new Pagination();
		page.setCurrentPage(1);
		page.setPageSize(5);
		SearchResult<ProductData> searchResults = productFacade.getAllProductWithPagniation(page, null);
		model.addAttribute("searchResults", searchResults);
		return "home/index";
	}
}
