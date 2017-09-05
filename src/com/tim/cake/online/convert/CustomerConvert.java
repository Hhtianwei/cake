package com.tim.cake.online.convert;

import com.tim.cake.online.convert.Convert;
import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.model.CustomerModel;


public class CustomerConvert implements Convert<CustomerModel, CustomerData>
{

	@Override
	public void convert(CustomerModel source, CustomerData target)
	{
		target.setId(source.getId());
		target.setName(source.getName());
		target.setNickName(source.getNickName());
		target.setMobile(source.getMobile());
		target.setEmail(source.getEmail());
		target.setCreateDate(source.getCreateDate());
	}
}
