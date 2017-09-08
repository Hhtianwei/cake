package com.tim.cake.online.dao.impl;

import java.util.HashMap;

import com.tim.cake.online.common.dao.CommonDAO;
import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.dao.ProductDao;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.ProductModel;


public class ProductDaoImpl extends CommonDAO implements ProductDao
{

	@Override
	public SearchResult<ProductModel> listProducts(Pagination page, ProductData data)
	{
		StringBuilder sql = new StringBuilder();
		HashMap<String, Object> params = new HashMap<String, Object>();
		sql.append("SELECT * FROM PRODUCT");
		sql.append(" where 1=1");

		if (data != null)
		{
			if (data.getPid() == 0)
			{
				sql.append(" and  pid=:pid ");
				params.put("pid", 0);
			}

			if (data.getId() != 0)
			{
				sql.append(" and id=:id ");
				params.put("id", data.getId());
			}

			if (data.getName() != null && !"".equals(data.getName()))
			{
				sql.append(" and name=:id ");
				params.put("name", data.getName());
			}
		}

		SearchResult<ProductModel> results = super.search(sql.toString(), page, params, ProductModel.class);
		return results;
	}

}
