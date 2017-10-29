package com.tim.cake.online.service;

import java.util.List;

import com.tim.cake.online.model.AddressModel;
import com.tim.cake.online.model.LocationModel;


public interface AccountService
{
	public void saveAddress(AddressModel model);

	public void updateAddress(AddressModel model);

	public List<LocationModel> getLocationInfoBySuperCode(String superCode);

	public LocationModel getLocationInfoByCode(String code);

	public void removeDefaultAddress();

	public List<AddressModel> getAddressList();


}
