package com.tim.cake.online.service;

import java.util.List;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.model.SizeProductModel;


public interface ProductService
{
	ProductModel getProductById(int id);

	List<SizeProductModel> getSizeProducts(int pId);

	SearchResult<ProductModel> getSuperProducts(Pagination page, ProductData data);

	void save(ProductModel productModel);
}
