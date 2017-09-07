package com.tim.cake.online.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.exception.UpdatePasswordException;
import com.tim.cake.online.facade.CustomerFacade;
import com.tim.cake.online.form.CustomerForm;
import com.tim.cake.online.form.PasswordForm;


@Controller
public class CustomerController
{
	private static final Logger LOG = Logger.getLogger(CustomerController.class);
	public static final String REDIRECT_PREFIX = "redirect:";
	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "customerValidator")
	private Validator customerValidator;

	@Resource(name = "passwordValidator")
	private Validator passwordValidator;

	/**
	 * 进入用户注册页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model)
	{
		CustomerForm customerForm = new CustomerForm();

		model.addAttribute("customerForm", customerForm);

		return "users/register";
	}

	/**
	 * 注册功能
	 * 
	 * @param model
	 * @param customerForm
	 * @param bindingResult
	 * @return
	 */
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

		String name = customerForm.getName();
		if (!customerFacade.checkUserName(name))
		{
			bindingResult.rejectValue("name", "customer.name.already.exists");
			LOG.error("register error,userName already exists.");
			model.addAttribute("customerForm", customerForm);
			return "users/register";
		}
		customerFacade.register(customerForm);
		return "users/registerResult";
	}

	/**
	 * 进入个人中心页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model)
	{
		//customerFacade.getCurrentUserInfo();
		return "users/account";
	}

	/**
	 * 进入修改用户信息页面
	 */
	@RequestMapping(value = "/updateCustomerInfo", method = RequestMethod.GET)
	public String updateCustomerInfo(Model model, HttpServletRequest request)
	{
		CustomerForm customerForm = getCustomerInfo(request);
		model.addAttribute("customerForm", customerForm);
		return "users/update";
	}

	private CustomerForm getCustomerInfo(HttpServletRequest request)
	{
		CustomerForm customerForm = new CustomerForm();
		HttpSession session = request.getSession();
		CustomerData data = (CustomerData) session.getAttribute("customerData");
		customerForm.setName(data.getName());
		customerForm.setNickName(data.getNickName());
		customerForm.setEmail(data.getEmail());
		customerForm.setMobile(data.getMobile());
		customerForm.setId(data.getId());
		customerForm.setUpdate(true);
		return customerForm;
	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param customerForm
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/updateCustomerInfo", method = RequestMethod.POST)
	public String update(Model model, HttpServletRequest request, CustomerForm customerForm, BindingResult bindingResult)
	{
		customerValidator.validate(customerForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			LOG.error("update userInfo error...");
			model.addAttribute("customerForm", customerForm);
			return "users/register";
		}
		customerFacade.update(customerForm);
		return REDIRECT_PREFIX + "/account";
	}

	/**
	 * 进入修改密码页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String updatePassword(Model model)
	{
		model.addAttribute("passwordForm", new PasswordForm());
		return "users/updatePassword";
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updateCustomerPassword(HttpServletRequest request, Model model, PasswordForm passwordForm, BindingResult errors)
	{
		passwordValidator.validate(passwordForm, errors);

		if (errors.hasErrors())
		{
			LOG.error("reset password error...");
			model.addAttribute("customerForm", passwordForm);
			return "users/updatePassword";
		}

		try
		{
			customerFacade.updatePassword(passwordForm);
		}
		catch (UpdatePasswordException e)
		{
			LOG.error("update password error,", e);
			errors.rejectValue("oldPassword", "password.oldPassword.input.invalid");
			model.addAttribute("customerForm", passwordForm);
			return "users/updatePassword";
		}

		//注销用户
		HttpSession session = request.getSession();
		session.invalidate();

		return REDIRECT_PREFIX + "/login";
	}
}
