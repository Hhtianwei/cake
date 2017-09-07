package com.tim.cake.online.facade.impl;

import java.util.ArrayList;
import java.util.List;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.convert.Convert;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.facade.ProductFacade;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.service.ProductService;


public class ProductFacadeImpl implements ProductFacade
{
	private ProductService productService;
	private Convert<ProductModel, ProductData> productConvert;

	@Override
	public ProductData getProductById(int id)
	{
		ProductModel productModel = productService.getProductById(id);
		ProductData data = new ProductData();
		productConvert.convert(productModel, data);
		return data;
	}

	@Override
	public SearchResult<ProductData> getAllProductWithPagniation(Pagination page, ProductData productData)
	{
		SearchResult<ProductModel> results = productService.getSuperProducts(page, productData);
		List<ProductModel> list = results.getResults();
		List<ProductData> dataList = new ArrayList<ProductData>();
		for (ProductModel model : list)
		{
			ProductData data = new ProductData();
			productConvert.convert(model, data);
			dataList.add(data);
		}
		SearchResult<ProductData> searchData = new SearchResult<ProductData>();
		searchData.setResults(dataList);
		searchData.setPagination(results.getPagination());
		return searchData;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}

	public Convert<ProductModel, ProductData> getProductConvert()
	{
		return productConvert;
	}

	public void setProductConvert(Convert<ProductModel, ProductData> productConvert)
	{
		this.productConvert = productConvert;
	}

}
