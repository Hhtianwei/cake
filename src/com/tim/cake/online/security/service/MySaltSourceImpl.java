package com.tim.cake.online.security.service;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

import com.tim.cake.online.utils.MD5Util;


public class MySaltSourceImpl implements SaltSource
{

	public Object getSalt(UserDetails userdetails)
	{
		String source = userdetails.getUsername();
		return MD5Util.MD5(source);
	}

}
