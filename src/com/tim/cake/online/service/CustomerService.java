package com.tim.cake.online.service;

import java.util.List;

import com.tim.cake.online.model.CustomerModel;


public interface CustomerService
{
	CustomerModel queryCustomerById(int id);

	List<CustomerModel> queryAllCustomers();

	CustomerModel queryCustomerByName(String name);

	void save(CustomerModel model);

}
