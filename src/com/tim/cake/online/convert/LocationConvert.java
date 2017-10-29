package com.tim.cake.online.convert;

import com.tim.cake.online.data.LocationData;
import com.tim.cake.online.model.LocationModel;


public class LocationConvert implements Convert<LocationModel, LocationData>
{
	@Override
	public void convert(LocationModel source, LocationData target)
	{
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setSuperCode(source.getSuperCode());
		target.setId(source.getId());
		target.setArea(source.isArea());
		target.setCity(source.isCity());
		target.setProvince(source.isProvince());
	}
}
