package com.tim.cake.online.service.impl;

import java.util.HashMap;
import java.util.List;

import com.tim.cake.online.common.service.CommonService;
import com.tim.cake.online.model.AddressModel;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.model.LocationModel;
import com.tim.cake.online.service.AccountService;
import com.tim.cake.online.service.CustomerService;


public class AccountServiceImpl implements AccountService
{
	private CommonService commonService;

	private CustomerService customerService;

	@Override
	public void saveAddress(AddressModel model)
	{
		commonService.save(model);
		commonService.refresh(model);
	}

	@Override
	public void updateAddress(AddressModel model)
	{

	}

	@Override
	public List<LocationModel> getLocationInfoBySuperCode(String superCode)
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(LocationModel.SUPERCODE, superCode);
		return commonService.getAllEntitesByField(LocationModel.class, params);
	}

	@Override
	public LocationModel getLocationInfoByCode(String code)
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(LocationModel.CODE, code);
		List<LocationModel> list = commonService.getAllEntitesByField(LocationModel.class, params);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public void removeDefaultAddress()
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		CustomerModel customer = customerService.getCurrentCustomer();
		params.put(AddressModel.CUSTOMER, customer);
		List<AddressModel> list = commonService.getAllEntitesByField(AddressModel.class, params);
		if (list == null || list.size() == 0)
		{
			return;
		}
		for (AddressModel model : list)
		{
			model.setIsDefault(false);
		}
		commonService.saveAll(list);
	}

	@Override
	public List<AddressModel> getAddressList()
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		CustomerModel customer = customerService.getCurrentCustomer();
		params.put(AddressModel.CUSTOMER, customer);
		List<AddressModel> list = commonService.getAllEntitesByField(AddressModel.class, params);
		return list;
	}

	public CommonService getCommonService()
	{
		return commonService;
	}

	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

	public CustomerService getCustomerService()
	{
		return customerService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

}
