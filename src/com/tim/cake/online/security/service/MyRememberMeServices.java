package com.tim.cake.online.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.facade.CustomerFacade;


public class MyRememberMeServices extends TokenBasedRememberMeServices
{
	private static final Logger LOG = Logger.getLogger(MyRememberMeServices.class);
	private CustomerFacade customerFacade;

	public MyRememberMeServices(String key, UserDetailsService userDetailsService)
	{
		super(key, userDetailsService);
	}

	@Override
	protected UserDetails processAutoLoginCookie(String cookieTokens[], HttpServletRequest request, HttpServletResponse response)
	{
		UserDetails user = super.processAutoLoginCookie(cookieTokens, request, response);
		HttpSession session = request.getSession();

		CustomerData customerData = customerFacade.getCustomerByName(user.getUsername());

		//save userInfo to session 
		session.setAttribute("customerData", customerData);
		LOG.info("-----MyRememberMeServices-----user-----------" + user.getUsername() + "--------------------");
		return user;
	}

	public CustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	public void setCustomerFacade(CustomerFacade customerFacade)
	{
		this.customerFacade = customerFacade;
	}

}
