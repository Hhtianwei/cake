package com.tim.cake.online.data;

public class EntryData
{
	private int id;
	private ProductData product;
	private int quantity;
	private double price;
	private double totalPrice;
	private int cartId;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public ProductData getProduct()
	{
		return product;
	}

	public void setProduct(ProductData product)
	{
		this.product = product;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public int getCartId()
	{
		return cartId;
	}

	public void setCartId(int cartId)
	{
		this.cartId = cartId;
	}


}
