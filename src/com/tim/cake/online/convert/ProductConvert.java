package com.tim.cake.online.convert;

import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.model.SizeProductModel;


public class ProductConvert implements Convert<ProductModel, ProductData>
{
	@Override
	public void convert(ProductModel source, ProductData target)
	{
		target.setId(source.getId());
		target.setLocation(source.getLocation());
		target.setLongName(source.getLongName());
		target.setName(source.getName());
		target.setShape(source.getShape());
		if (source instanceof SizeProductModel)
		{
			target.setPid(((SizeProductModel) source).getPid());
			target.setPrice(((SizeProductModel) source).getPrice());
			target.setSize(((SizeProductModel) source).getSize());
			target.setStock(((SizeProductModel) source).getStock());
		}
	}

}
