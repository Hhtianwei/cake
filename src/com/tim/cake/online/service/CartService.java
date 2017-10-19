package com.tim.cake.online.service;

import com.tim.cake.online.model.CartModel;
import com.tim.cake.online.model.ProductModel;


public interface CartService
{

	CartModel createCart();

	CartModel getSessionCart();

	void addToCart(ProductModel product, int quantity);

	void updateCart(int quantity);

	void saveCart(CartModel model);

	void merge();
}
