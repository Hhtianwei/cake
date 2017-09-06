package com.tim.cake.online.dao;

import java.util.List;

import com.tim.cake.online.model.CustomerModel;


public interface CustomerDao
{
	CustomerModel findCustomerById(int id);

	List<CustomerModel> findAllCustomers();

	CustomerModel findCustomerByName(String name);

	void save(CustomerModel model);

	List checkPassword(int id, String oldPassword);
}
