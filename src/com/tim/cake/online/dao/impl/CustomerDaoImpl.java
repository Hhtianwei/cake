package com.tim.cake.online.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.tim.cake.online.common.dao.CommonDAO;
import com.tim.cake.online.dao.CustomerDao;
import com.tim.cake.online.model.AuthoritiesModel;
import com.tim.cake.online.model.CustomerModel;


public class CustomerDaoImpl extends CommonDAO implements CustomerDao
{
	@Override
	public CustomerModel findCustomerById(int id)
	{
		return (CustomerModel) super.get(CustomerModel.class, id);
		//return (CustomerModel) super.load(CustomerModel.class, id);
	}

	@Override
	public List<CustomerModel> findAllCustomers()
	{
		return super.loadAllEntities(CustomerModel.class);
	}

	@Override
	public CustomerModel findCustomerByName(String name)
	{
		List<CustomerModel> list = super.getEntitiesByField(CustomerModel.class, CustomerModel.NAME, name);
		if (list == null || list.isEmpty())
		{
			return null;
		}
		return list.get(0);
	}

	public void save(CustomerModel model)
	{
		super.saveOrUpdateEntity(model);
	}

	public void addAuthorities(AuthoritiesModel model)
	{
		super.saveOrUpdateEntity(model);
	}

	@Override
	public List checkPassword(int id, String oldPassword)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(CustomerModel.ID, id);
		map.put(CustomerModel.PASSWORD, oldPassword);
		List result = super.getEntitiesByFields(CustomerModel.class, map);
		return result;
	}

}
