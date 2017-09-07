package com.tim.cake.online.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;


public class AdminController
{
	private static final Logger LOG = Logger.getLogger(AdminController.class);

	public String home(Model model)
	{
		LOG.info("login admin page...");
		return "admin/home";
	}
}
