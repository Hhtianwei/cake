package com.tim.cake.online.service.impl;

import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.utils.MD5Util;


public class PasswordService
{
	public String encodingPassword(CustomerModel model, String password)
	{
		String salt = MD5Util.MD5(model.getName());
		String encodingPassword = MD5Util.MD5(password + "{" + salt + "}");
		return encodingPassword;
	}
}
