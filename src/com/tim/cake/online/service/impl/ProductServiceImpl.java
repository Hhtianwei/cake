package com.tim.cake.online.service.impl;

import java.util.HashMap;
import java.util.List;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.common.service.CommonService;
import com.tim.cake.online.dao.ProductDao;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.model.SizeProductModel;
import com.tim.cake.online.service.ProductService;


public class ProductServiceImpl implements ProductService
{

	private CommonService commonService;
	private ProductDao productDao;

	@Override
	public ProductModel getProductById(int id)
	{
		ProductModel model = (ProductModel) commonService.getEntityById(ProductModel.class, id);

		if (model instanceof ProductModel)
		{
			List<SizeProductModel> products = getSizeProducts(model.getId());
			model.setProducts(products);
		}

		return model;
	}

	@Override
	public List<SizeProductModel> getSizeProducts(int pId)
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(SizeProductModel.PID, pId);
		List<SizeProductModel> list = commonService.getAllEntitesByField(SizeProductModel.class, params);
		return list;
	}

	@Override
	public SearchResult<ProductModel> getSuperProducts(Pagination page, ProductData data)
	{
		SearchResult<ProductModel> searchResult = productDao.listProducts(page, data);
		return searchResult;
	}

	@Override
	public void save(ProductModel productModel)
	{
		commonService.save(productModel);
	}

	public CommonService getCommonService()
	{
		return commonService;
	}

	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

	public ProductDao getProductDao()
	{
		return productDao;
	}

	public void setProductDao(ProductDao productDao)
	{
		this.productDao = productDao;
	}

}
