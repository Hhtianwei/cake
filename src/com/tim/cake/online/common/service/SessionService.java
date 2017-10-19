package com.tim.cake.online.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class SessionService
{
	public HttpSession getCurrentSession()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	public void setSessionAttr(String key, Object value)
	{
		HttpSession session = getCurrentSession();
		session.setAttribute(key, value);
	}

	public Object getSessionAttr(String key)
	{
		HttpSession session = getCurrentSession();
		return session.getAttribute(key);
	}
}
