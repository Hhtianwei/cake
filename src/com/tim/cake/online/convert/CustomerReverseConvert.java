package com.tim.cake.online.convert;

import java.util.Date;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.utils.MD5Util;


public class CustomerReverseConvert implements Convert<CustomerData, CustomerModel>
{

	@Override
	public void convert(CustomerData source, CustomerModel target)
	{
		target.setId(source.getId());
		target.setName(source.getName());
		target.setNickName(source.getNickName());
		target.setMobile(source.getMobile());
		target.setEmail(source.getEmail());
		target.setCreateDate(new Date());
		String salt = MD5Util.MD5(source.getName());
		String encodingPassword = MD5Util.MD5(source.getPassword() + "{" + salt + "}");
		target.setPassword(encodingPassword);
		target.setEnabled(1);
	}
}
