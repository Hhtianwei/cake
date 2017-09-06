package com.tim.cake.online.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tim.cake.online.common.service.CommonService;
import com.tim.cake.online.dao.CustomerDao;
import com.tim.cake.online.data.CustomerData;
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


	@Override
	public CustomerModel getCurrentCustomer()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		CustomerData data = (CustomerData) session.getAttribute("customerData");
		int id = data.getId();
		CustomerModel model = customerDao.findCustomerById(id);
		return model;
	}

	@Override
	public boolean checkPassword(int id, String oldPassword)
	{
		List list = customerDao.checkPassword(id, oldPassword);
		if (CollectionUtils.isNotEmpty(list))
		{
			return true;
		}
		return false;
	}

	@Override
	public void update(CustomerModel model)
	{
		commonService.save(model);
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
