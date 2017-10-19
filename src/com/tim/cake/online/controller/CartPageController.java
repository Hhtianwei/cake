package com.tim.cake.online.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tim.cake.online.data.CartData;
import com.tim.cake.online.facade.CartFacade;


@Controller
@RequestMapping("/cart")
public class CartPageController
{

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;


	@RequestMapping(method = RequestMethod.GET)
	public String show(Model model)
	{
		CartData cartData = cartFacade.getSessionCart();
		model.addAttribute("cartData", cartData);
		return "cart/index";
	}

	@RequestMapping("/addToCart")
	public String addToCart(Model model, @RequestParam(value = "productId", required = true) int productId,
			@RequestParam(value = "quantity", defaultValue = "1") int quantity)
	{
		cartFacade.addToCart(productId, quantity);
		return "redirect:" + "/cart";
	}
}
