package com.tim.cake.online.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomePageController
{
	private static final Logger LOG = Logger.getLogger(HomePageController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String findUser(Model model)
	{
		LOG.info("visit home page...");
		return "home/index";
	}
}
