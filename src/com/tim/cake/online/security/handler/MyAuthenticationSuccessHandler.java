package com.tim.cake.online.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.facade.CustomerFacade;
import com.tim.cake.online.security.service.DefaultBruteForceAttackCounter;
import com.tim.cake.online.service.CartService;


public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
	private static final Logger LOG = Logger.getLogger(MyAuthenticationSuccessHandler.class);

	private DefaultBruteForceAttackCounter bruteForceAttackCounter;

	private CustomerFacade customerFacade;

	private CartService cartService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException
	{
		String name = authentication.getName();
		bruteForceAttackCounter.removeLoginFailureUser(name);
		CustomerData customerData = customerFacade.getCustomerByName(name);

		//save userInfo to session 
		request.getSession().setAttribute("customerData", customerData);

		//merge session cart to userCart
		cartService.merge();

		super.onAuthenticationSuccess(request, response, authentication);
	}

	public CartService getCartService()
	{
		return cartService;
	}

	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	public DefaultBruteForceAttackCounter getBruteForceAttackCounter()
	{
		return bruteForceAttackCounter;
	}

	public void setBruteForceAttackCounter(DefaultBruteForceAttackCounter bruteForceAttackCounter)
	{
		this.bruteForceAttackCounter = bruteForceAttackCounter;
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
