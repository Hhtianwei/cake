package com.tim.cake.online.facade;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.form.CustomerForm;


public interface CustomerFacade
{
	CustomerData getCustomerById(int id);

	CustomerData getCustomerByName(String name);

	void register(CustomerForm customerForm);
}
