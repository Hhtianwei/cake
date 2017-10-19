package com.tim.cake.online.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "entry")
public class EntryModel
{
	private int id;
	private ProductModel product;
	private int quantity;
	private double price;
	private double totalPrice;
	private CartModel cart;

	@Id
	@Column(name = "id", length = 11, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productId", unique = false)
	public ProductModel getProduct()
	{
		return product;
	}

	public void setProduct(ProductModel product)
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

	@ManyToOne(cascade =
	{ CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "cartId")
	public CartModel getCart()
	{
		return cart;
	}

	public void setCart(CartModel cart)
	{
		this.cart = cart;
	}

}
