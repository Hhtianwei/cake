package com.tim.cake.online.facade;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.exception.UpdatePasswordException;
import com.tim.cake.online.form.CustomerForm;
import com.tim.cake.online.form.PasswordForm;


public interface CustomerFacade
{
	CustomerData getCustomerById(int id);

	CustomerData getCustomerByName(String name);

	void register(CustomerForm customerForm);

	void update(CustomerForm customerForm);

	void updatePassword(PasswordForm passwordForm) throws UpdatePasswordException;
}
