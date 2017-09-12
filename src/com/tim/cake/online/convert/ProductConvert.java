package com.tim.cake.online.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tim.cake.online.config.Config;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.model.SizeProductModel;


public class ProductConvert implements Convert<ProductModel, ProductData>
{
	private static final String PRODUCT_IMAGE_BASE_URL = "cake.product.image.base.url";

	@Override
	public void convert(ProductModel source, ProductData target)
	{
		target.setId(source.getId());
		target.setLocation(source.getLocation());
		target.setLongName(source.getLongName());
		target.setName(source.getName());
		target.setShape(source.getShape());

		setProductImage(source, target);

		if (source instanceof SizeProductModel)
		{
			target.setPid(((SizeProductModel) source).getPid());
			target.setPrice(((SizeProductModel) source).getPrice());
			target.setSize(((SizeProductModel) source).getSize());
			target.setStock(((SizeProductModel) source).getStock());
			target.setDefaultProduct(source.isDefaultProduct());
		}

		if (source instanceof ProductModel)
		{
			populatorSubProduct(source, target);
		}
	}

	private void populatorSubProduct(ProductModel source, ProductData target)
	{
		List<SizeProductModel> list = source.getProducts();
		if (CollectionUtils.isEmpty(list))
		{
			return;
		}
		List<ProductData> subProductDatas = new ArrayList<ProductData>();
		for (SizeProductModel product : list)
		{
			ProductData tempData = new ProductData();
			convert(product, tempData);
			subProductDatas.add(tempData);
		}
		target.setSubProductDatas(subProductDatas);
	}

	private void setProductImage(ProductModel source, ProductData target)
	{
		String basePath = Config.getProperty(PRODUCT_IMAGE_BASE_URL);
		target.setImageUrl(basePath + source.getImageName());
	}

}
