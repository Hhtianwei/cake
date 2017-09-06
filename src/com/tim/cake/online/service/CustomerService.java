package com.tim.cake.online.service;

import java.util.List;

import com.tim.cake.online.model.CustomerModel;


public interface CustomerService
{
	CustomerModel queryCustomerById(int id);

	CustomerModel getCurrentCustomer();

	List<CustomerModel> queryAllCustomers();

	CustomerModel queryCustomerByName(String name);

	void save(CustomerModel model);

	void update(CustomerModel model);

	boolean checkPassword(int id, String oldPassword);


}
