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
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
public class AuthoritiesModel
{
	private int id;
	private CustomerModel customer;
	private String authority = "ROLE_";

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

	@Column(name = "authority", length = 50, unique = false)
	public String getAuthority()
	{
		return authority;
	}

	public void setAuthority(String authority)
	{
		this.authority = authority;
	}

	@ManyToOne(cascade =
	{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId")
	public CustomerModel getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerModel customer)
	{
		this.customer = customer;
	}

}
