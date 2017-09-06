package com.tim.cake.online.facade.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tim.cake.online.convert.Convert;
import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.exception.UpdatePasswordException;
import com.tim.cake.online.facade.CustomerFacade;
import com.tim.cake.online.form.CustomerForm;
import com.tim.cake.online.form.PasswordForm;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.service.CustomerService;
import com.tim.cake.online.service.impl.PasswordService;


public class CustomerFacadeImpl implements CustomerFacade
{
	private CustomerService customerService;

	private Convert<CustomerModel, CustomerData> customerConvert;

	private Convert<CustomerData, CustomerModel> customerReverseConvert;

	private PasswordService passwordService;

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

	@Override
	public void update(CustomerForm customerForm)
	{
		CustomerData customerData = new CustomerData();
		customerData.setId(customerForm.getId());
		customerData.setNickName(customerForm.getNickName());
		customerData.setEmail(customerForm.getEmail());
		customerData.setMobile(customerForm.getMobile());
		customerData.setUpdate(true);
		CustomerModel model = customerService.queryCustomerById(customerData.getId());
		customerReverseConvert.convert(customerData, model);
		customerService.update(model);

		//更新用户信息之后 ，session也要更新
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		customerConvert.convert(model, customerData);
		session.setAttribute("customerData", customerData);
	}

	@Override
	public void updatePassword(PasswordForm passwordForm) throws UpdatePasswordException
	{
		CustomerModel model = customerService.getCurrentCustomer();
		String oldPassword = passwordService.encodingPassword(model, passwordForm.getOldPassword());
		boolean flag = customerService.checkPassword(model.getId(), oldPassword);
		if (!flag)
		{
			throw new UpdatePasswordException("update password error, old password input error");
		}
		String newPassword = passwordService.encodingPassword(model, passwordForm.getNewPassword());
		model.setPassword(newPassword);
		customerService.update(model);
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

	public PasswordService getPasswordService()
	{
		return passwordService;
	}

	public void setPasswordService(PasswordService passwordService)
	{
		this.passwordService = passwordService;
	}

}
