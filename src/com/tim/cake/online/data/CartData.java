package com.tim.cake.online.data;

import java.util.Date;
import java.util.List;


public class CartData
{
	private int id;
	private CustomerData customer;
	private List<EntryData> entries;
	private double totalPrice;
	private Date createDate;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public CustomerData getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerData customer)
	{
		this.customer = customer;
	}

	public List<EntryData> getEntries()
	{
		return entries;
	}

	public void setEntries(List<EntryData> entries)
	{
		this.entries = entries;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

}
