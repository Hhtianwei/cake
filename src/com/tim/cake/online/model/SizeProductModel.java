package com.tim.cake.online.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class SizeProductModel extends ProductModel
{
	public static final String SIZE = "size";
	public static final String PID = "pid";
	public static final String STOCK = "stock";
	public static final String PRICE = "price";

	private String size;

	private int pid;

	private int stock;

	private double price;//价格

	@Column(name = "size", length = 20)
	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	@Column(name = "pid", length = 11, nullable = true)
	public int getPid()
	{
		return pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}

	@Column(name = "stock", length = 11, nullable = true)
	public int getStock()
	{
		return stock;
	}

	public void setStock(int stock)
	{
		this.stock = stock;
	}


	@Column(name = "price", length = 20)
	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
}
