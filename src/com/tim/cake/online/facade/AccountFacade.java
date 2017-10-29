package com.tim.cake.online.facade;

import java.util.List;

import com.tim.cake.online.data.AddressData;
import com.tim.cake.online.data.LocationData;


public interface AccountFacade
{
	public void saveAddress(AddressData addressData);

	public void updateAddress(AddressData addressData);

	public List<LocationData> getLocationInfoBySuperCode(String superCode);

	public LocationData getLocationInfoByCode(String code);

	List<AddressData> getAddressList();


}
