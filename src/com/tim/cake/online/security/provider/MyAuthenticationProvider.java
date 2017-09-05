package com.tim.cake.online.security.provider;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.tim.cake.online.exception.AttackSystemException;
import com.tim.cake.online.security.service.DefaultBruteForceAttackCounter;


public class MyAuthenticationProvider extends DaoAuthenticationProvider
{
	private static final Logger LOG = Logger.getLogger(MyAuthenticationProvider.class);
	private DefaultBruteForceAttackCounter bruteForceAttackCounter;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String userName = authentication.getName();
		if (bruteForceAttackCounter.isAttrack(userName))
		{
			LOG.error(String.format("--------current user[%s] attack system,can't allow login.-----", userName));
			throw new AttackSystemException(String.format("user[%s] attack system", userName));
		}
		return super.authenticate(authentication);
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
