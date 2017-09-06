package com.tim.cake.online.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tim.cake.online.exception.AttackSystemException;


@Controller
@RequestMapping("/login")
public class LoginPageController
{
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request,
			@RequestParam(value = "error", defaultValue = "false") boolean loginError)
	{
		String uname = request.getParameter("uname");
		LOG.info("=====================" + uname + "======================");
		if (loginError)
		{
			AuthenticationException loginException = (AuthenticationException) request.getSession()
					.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			String errorMsg = "";
			if (loginException instanceof BadCredentialsException)
			{
				errorMsg = "password error";
				LOG.error(String.format("user password error"));
			}
			if (loginException instanceof UsernameNotFoundException)
			{
				errorMsg = "user name is not found";
				LOG.error(String.format("user name is not found"));
			}
			if (loginException instanceof AttackSystemException)
			{
				errorMsg = "user attack system,can't login";
				LOG.error(String.format("user[%s] attack system,can't login"));
			}
			model.addAttribute("uname", uname);
			model.addAttribute("errorMsg", errorMsg);
		}
		return "login/login";
	}

}
