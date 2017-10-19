package com.tim.cake.online.facade;

import com.tim.cake.online.data.CartData;


public interface CartFacade
{
	CartData getSessionCart();

	void addToCart(int productId, int quantity);
}
