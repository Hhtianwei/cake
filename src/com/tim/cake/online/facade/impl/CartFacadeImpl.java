package com.tim.cake.online.facade.impl;

import com.tim.cake.online.convert.Convert;
import com.tim.cake.online.data.CartData;
import com.tim.cake.online.facade.CartFacade;
import com.tim.cake.online.model.CartModel;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.service.CartService;
import com.tim.cake.online.service.ProductService;


public class CartFacadeImpl implements CartFacade
{

	private CartService cartService;

	private Convert<CartModel, CartData> cartConvert;

	private ProductService productService;

	@Override
	public CartData getSessionCart()
	{
		CartModel cart = cartService.getSessionCart();
		CartData data = new CartData();
		cartConvert.convert(cart, data);
		return data;
	}

	@Override
	public void addToCart(int productId, int quantity)
	{
		ProductModel productModel = productService.getProductById(productId);
		cartService.addToCart(productModel, quantity);
	}

	public CartService getCartService()
	{
		return cartService;
	}

	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	public Convert<CartModel, CartData> getCartConvert()
	{
		return cartConvert;
	}

	public void setCartConvert(Convert<CartModel, CartData> cartConvert)
	{
		this.cartConvert = cartConvert;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}

}
