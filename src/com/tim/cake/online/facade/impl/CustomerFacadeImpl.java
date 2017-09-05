package com.tim.cake.online.facade.impl;

import com.tim.cake.online.convert.Convert;
import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.facade.CustomerFacade;
import com.tim.cake.online.form.CustomerForm;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.service.CustomerService;


public class CustomerFacadeImpl implements CustomerFacade
{
	private CustomerService customerService;

	private Convert<CustomerModel, CustomerData> customerConvert;

	private Convert<CustomerData, CustomerModel> customerReverseConvert;

	@Override
	public CustomerData getCustomerById(int id)
	{
		CustomerModel model = customerService.queryCustomerById(id);
		CustomerData data = new CustomerData();
		customerConvert.convert(model, data);
		return data;
	}

	@Override
	public CustomerData getCustomerByName(String name)
	{
		CustomerModel model = customerService.queryCustomerByName(name);
		CustomerData data = new CustomerData();
		customerConvert.convert(model, data);
		return data;
	}

	@Override
	public void register(CustomerForm customerForm)
	{
		CustomerData customerData = new CustomerData();
		customerData.setName(customerForm.getName());
		customerData.setNickName(customerForm.getNickName());
		customerData.setEmail(customerForm.getEmail());
		customerData.setMobile(customerForm.getMobile());
		customerData.setPassword(customerForm.getPassword());
		CustomerModel model = new CustomerModel();
		customerReverseConvert.convert(customerData, model);
		customerService.save(model);
	}

	public CustomerService getCustomerService()
	{
		return customerService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	public Convert<CustomerModel, CustomerData> getCustomerConvert()
	{
		return customerConvert;
	}

	public void setCustomerConvert(Convert<CustomerModel, CustomerData> customerConvert)
	{
		this.customerConvert = customerConvert;
	}

	public Convert<CustomerData, CustomerModel> getCustomerReverseConvert()
	{
		return customerReverseConvert;
	}

	public void setCustomerReverseConvert(Convert<CustomerData, CustomerModel> customerReverseConvert)
	{
		this.customerReverseConvert = customerReverseConvert;
	}

}
