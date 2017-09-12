package com.tim.cake.online.facade;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.data.ProductData;


public interface ProductFacade
{
	ProductData getProductById(int id);

	SearchResult<ProductData> getAllProductWithPagniation(Pagination page, ProductData data);

	void save(ProductData data);
}
