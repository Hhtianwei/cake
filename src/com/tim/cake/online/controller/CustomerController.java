package com.tim.cake.online.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tim.cake.online.facade.CustomerFacade;
import com.tim.cake.online.form.CustomerForm;


@Controller
public class CustomerController
{
	private static final Logger LOG = Logger.getLogger(CustomerController.class);

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "customerValidator")
	private Validator customerValidator;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model)
	{
		CustomerForm customerForm = new CustomerForm();

		model.addAttribute("customerForm", customerForm);

		return "users/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String save(Model model, CustomerForm customerForm, BindingResult bindingResult)
	{
		customerValidator.validate(customerForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			LOG.error("register error...");
			model.addAttribute("customerForm", customerForm);
			return "users/register";
		}
		customerFacade.register(customerForm);
		return "users/registerResult";
	}
}
