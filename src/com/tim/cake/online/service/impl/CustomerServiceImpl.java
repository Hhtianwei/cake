package com.tim.cake.online.service.impl;

import java.util.List;

import com.tim.cake.online.common.service.CommonService;
import com.tim.cake.online.dao.CustomerDao;
import com.tim.cake.online.model.AuthoritiesModel;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.service.CustomerService;


public class CustomerServiceImpl implements CustomerService
{
	private CustomerDao customerDao;

	private CommonService commonService;

	private static final String RLOE_USER = "USER";
	private static final String RLOE_ADMIN = "ADMIN";

	@Override
	public CustomerModel queryCustomerById(int id)
	{
		return customerDao.findCustomerById(id);
	}

	@Override
	public List<CustomerModel> queryAllCustomers()
	{
		return customerDao.findAllCustomers();
	}


	@Override
	public CustomerModel queryCustomerByName(String name)
	{
		return customerDao.findCustomerByName(name);
	}

	@Override
	public void save(CustomerModel model)
	{

		AuthoritiesModel athorities = new AuthoritiesModel();
		athorities.setCustomer(model);
		athorities.setAuthority(athorities.getAuthority() + RLOE_USER);
		commonService.save(model);
		commonService.save(athorities);
	}

	public CustomerDao getCustomerDao()
	{
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}

	public CommonService getCommonService()
	{
		return commonService;
	}

	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

}
