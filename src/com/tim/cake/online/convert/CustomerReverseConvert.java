package com.tim.cake.online.convert;

import java.util.Date;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.service.impl.PasswordService;


public class CustomerReverseConvert implements Convert<CustomerData, CustomerModel>
{

	private PasswordService passwordService;

	@Override
	public void convert(CustomerData source, CustomerModel target)
	{
		boolean isUpdate = source.isUpdate();
		if (!isUpdate)
		{
			target.setName(source.getName());
			target.setCreateDate(new Date());
			String encodingPassword = passwordService.encodingPassword(target, source.getPassword());
			target.setPassword(encodingPassword);
			target.setEnabled(1);
		}

		target.setNickName(source.getNickName());
		target.setMobile(source.getMobile());
		target.setEmail(source.getEmail());
	}

	public PasswordService getPasswordService()
	{
		return passwordService;
	}

	public void setPasswordService(PasswordService passwordService)
	{
		this.passwordService = passwordService;
	}
}
