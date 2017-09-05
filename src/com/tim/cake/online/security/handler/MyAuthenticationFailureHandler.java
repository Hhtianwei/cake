package com.tim.cake.online.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.tim.cake.online.security.service.DefaultBruteForceAttackCounter;


public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
	private static final Logger LOG = Logger.getLogger(MyAuthenticationFailureHandler.class);

	private DefaultBruteForceAttackCounter bruteForceAttackCounter;


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException
	{
		String userName = request.getParameter("uname");
		LOG.error("-----login failure user :" + userName);
		bruteForceAttackCounter.addFailureUser(userName);
		super.onAuthenticationFailure(request, response, authenticationException);
	}

	public DefaultBruteForceAttackCounter getBruteForceAttackCounter()
	{
		return bruteForceAttackCounter;
	}


	public void setBruteForceAttackCounter(DefaultBruteForceAttackCounter bruteForceAttackCounter)
	{
		this.bruteForceAttackCounter = bruteForceAttackCounter;
	}


}
