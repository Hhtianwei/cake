package com.tim.cake.online.convert;

import com.tim.cake.online.data.AddressData;
import com.tim.cake.online.model.AddressModel;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.service.AccountService;
import com.tim.cake.online.service.CustomerService;


public class AddresReverseConvert implements Convert<AddressData, AddressModel>
{

	private CustomerService customerService;
	private AccountService accountService;

	@Override
	public void convert(AddressData source, AddressModel target)
	{
		target.setAreaCode(source.getAreaCode());
		target.setCityCode(source.getCityCode());

		CustomerModel customer = customerService.getCurrentCustomer();
		target.setCustomer(customer);

		//修改默认地址
		if (source.getIsDefault())
		{
			accountService.removeDefaultAddress();
			target.setIsDefault(true);
		}

		target.setProvienceCode(source.getProvinceCode());
		target.setRecipient(source.getRecipient());
		target.setStreet(source.getStreet());
		target.setTagName(source.getTagName());
		target.setTel(source.getTel());
	}

	public CustomerService getCustomerService()
	{
		return customerService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public AccountService getAccountService()
	{
		return accountService;
	}

	public void setAccountService(AccountService accountService)
	{
		this.accountService = accountService;
	}

}
