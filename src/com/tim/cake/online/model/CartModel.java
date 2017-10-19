package com.tim.cake.online.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cart")
public class CartModel
{
	private int id;
	private List<EntryModel> entries;
	private double totalPrice;
	private CustomerModel customer;
	private Date createTime = new Date();

	@Id
	@Column(name = "id", length = 11, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
	public List<EntryModel> getEntries()
	{
		return entries;
	}

	public void setEntries(List<EntryModel> entries)
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", unique = true)
	public CustomerModel getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerModel customer)
	{
		this.customer = customer;
	}

	@Column(name = "createTime", unique = false, nullable = false)
	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}
