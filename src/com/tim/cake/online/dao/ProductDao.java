package com.tim.cake.online.dao;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;


public interface ProductDao
{
	SearchResult<ProductModel> listProducts(Pagination page, ProductData data);
}
