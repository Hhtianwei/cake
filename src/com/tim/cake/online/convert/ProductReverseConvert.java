package com.tim.cake.online.convert;

import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.model.SizeProductModel;


public class ProductReverseConvert implements Convert<ProductData, ProductModel>
{

	@Override
	public void convert(ProductData source, ProductModel target)
	{
		target.setLocation(source.getLocation());
		target.setLongName(source.getLongName());
		target.setName(source.getName());
		target.setShape(source.getShape());
		target.setImageName(source.getImageUrl());
		if (source.isChild())
		{
			((SizeProductModel) target).setPid(source.getPid());
			((SizeProductModel) target).setPrice(source.getPrice());
			((SizeProductModel) target).setSize(source.getSize());
			((SizeProductModel) target).setStock(source.getStock());
		}
	}
}
