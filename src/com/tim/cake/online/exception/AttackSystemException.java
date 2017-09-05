package com.tim.cake.online.exception;

import org.springframework.security.core.AuthenticationException;


public class AttackSystemException extends AuthenticationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AttackSystemException(String message)
	{
		super(message);
	}

	public AttackSystemException(String message, Throwable t)
	{
		super(message, t);
	}
}
