package com.tim.cake.online.facade.impl;

import java.util.ArrayList;
import java.util.List;

import com.tim.cake.online.convert.AddresConvert;
import com.tim.cake.online.convert.AddresReverseConvert;
import com.tim.cake.online.convert.LocationConvert;
import com.tim.cake.online.data.AddressData;
import com.tim.cake.online.data.LocationData;
import com.tim.cake.online.facade.AccountFacade;
import com.tim.cake.online.model.AddressModel;
import com.tim.cake.online.model.LocationModel;
import com.tim.cake.online.service.AccountService;


public class AccountFacadeImpl implements AccountFacade
{
	private AccountService accountService;

	private LocationConvert locationConvert;

	private AddresReverseConvert addresReverseConvert;

	private AddresConvert addresConvert;

	@Override
	public void saveAddress(AddressData addressData)
	{
		AddressModel model = new AddressModel();
		addresReverseConvert.convert(addressData, model);
		accountService.saveAddress(model);
	}

	@Override
	public void updateAddress(AddressData addressData)
	{

	}

	@Override
	public List<LocationData> getLocationInfoBySuperCode(String superCode)
	{
		List<LocationModel> models = accountService.getLocationInfoBySuperCode(superCode);
		List<LocationData> datas = new ArrayList<LocationData>();
		for (LocationModel model : models)
		{
			LocationData data = new LocationData();
			locationConvert.convert(model, data);
			datas.add(data);
		}
		return datas;
	}

	@Override
	public LocationData getLocationInfoByCode(String code)
	{
		LocationModel model = accountService.getLocationInfoByCode(code);
		LocationData data = new LocationData();
		locationConvert.convert(model, data);
		return data;
	}

	@Override
	public List<AddressData> getAddressList()
	{
		List<AddressModel> models = accountService.getAddressList();
		List<AddressData> datas = new ArrayList<AddressData>();
		for (int i = 0; i < models.size(); i++)
		{
			AddressData data = new AddressData();
			addresConvert.convert(models.get(i), data);
			datas.add(data);
		}
		return datas;
	}

	public AccountService getAccountService()
	{
		return accountService;
	}

	public void setAccountService(AccountService accountService)
	{
		this.accountService = accountService;
	}

	public LocationConvert getLocationConvert()
	{
		return locationConvert;
	}

	public void setLocationConvert(LocationConvert locationConvert)
	{
		this.locationConvert = locationConvert;
	}

	public AddresReverseConvert getAddresReverseConvert()
	{
		return addresReverseConvert;
	}

	public void setAddresReverseConvert(AddresReverseConvert addresReverseConvert)
	{
		this.addresReverseConvert = addresReverseConvert;
	}

	public AddresConvert getAddresConvert()
	{
		return addresConvert;
	}

	public void setAddresConvert(AddresConvert addresConvert)
	{
		this.addresConvert = addresConvert;
	}
}
