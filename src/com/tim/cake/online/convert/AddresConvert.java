package com.tim.cake.online.convert;

import com.tim.cake.online.data.AddressData;
import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.data.LocationData;
import com.tim.cake.online.model.AddressModel;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.model.LocationModel;
import com.tim.cake.online.service.AccountService;


public class AddresConvert implements Convert<AddressModel, AddressData>
{

	private CustomerConvert customerConvert;
	private AccountService accountService;
	private LocationConvert locationConvert;

	@Override
	public void convert(AddressModel source, AddressData target)
	{
		target.setId(source.getId());
		target.setTagName(source.getTagName());
		target.setTel(source.getTel());

		CustomerModel customer = source.getCustomer();
		CustomerData customerData = new CustomerData();
		customerConvert.convert(customer, customerData);
		target.setCustomer(customerData);

		//修改默认地址
		target.setIsDefault(source.getIsDefault());

		LocationModel provinceModel = accountService.getLocationInfoByCode(source.getProvienceCode());
		LocationData provinceData = new LocationData();
		locationConvert.convert(provinceModel, provinceData);
		target.setProvinceCode(source.getProvienceCode());
		target.setProvinceName(provinceData.getName());

		LocationModel cityModel = accountService.getLocationInfoByCode(source.getCityCode());
		LocationData cityData = new LocationData();
		locationConvert.convert(cityModel, cityData);

		target.setCityCode(source.getCityCode());
		target.setCityName(cityData.getName());

		LocationModel areaModel = accountService.getLocationInfoByCode(source.getAreaCode());
		LocationData areaData = new LocationData();
		locationConvert.convert(areaModel, areaData);
		target.setAreaCode(source.getAreaCode());
		target.setAreaName(areaData.getName());

		target.setStreet(source.getStreet());

		target.setRecipient(source.getRecipient());
	}

	public AccountService getAccountService()
	{
		return accountService;
	}

	public void setAccountService(AccountService accountService)
	{
		this.accountService = accountService;
	}

	public CustomerConvert getCustomerConvert()
	{
		return customerConvert;
	}

	public void setCustomerConvert(CustomerConvert customerConvert)
	{
		this.customerConvert = customerConvert;
	}

	public LocationConvert getLocationConvert()
	{
		return locationConvert;
	}

	public void setLocationConvert(LocationConvert locationConvert)
	{
		this.locationConvert = locationConvert;
	}

}
