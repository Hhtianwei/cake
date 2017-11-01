package com.tim.cake.online.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "customer")
public class CustomerModel
{

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String NICKNAME = "nickName";
	public static final String MOBILE = "mobile";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String CREATEDATE = "createDate";
	public static final String ADDRESS = "address";

	private int id;
	private String name;
	private String nickName;
	private String mobile;
	private String email;
	private String password;
	private Date createDate;
	private int enabled;
	private CartModel cart;
	private Set<AddressModel> address;

	private Set<AuthoritiesModel> authorities;

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

	@Column(name = "name", length = 50, unique = false, nullable = false)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "nickName", length = 50, unique = false, nullable = false)
	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	@Column(name = "mobile", length = 20, unique = false, nullable = false)
	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	@Column(name = "email", length = 50, unique = false, nullable = false)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "createDate", unique = false, nullable = false)
	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	@Column(name = "password", length = 50, unique = false, nullable = false)
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@OneToMany(mappedBy = "customer")
	public Set<AuthoritiesModel> getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(Set<AuthoritiesModel> authorities)
	{
		this.authorities = authorities;
	}

	@Column(name = "enabled", length = 1, unique = false, nullable = false)
	public int getEnabled()
	{
		return enabled;
	}

	public void setEnabled(int enabled)
	{
		this.enabled = enabled;
	}

	@OneToOne(mappedBy = "customer", cascade =
	{ CascadeType.MERGE, CascadeType.REMOVE })
	public CartModel getCart()
	{
		return cart;
	}

	public void setCart(CartModel cart)
	{
		this.cart = cart;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	public Set<AddressModel> getAddress()
	{
		return address;
	}

	public void setAddress(Set<AddressModel> address)
	{
		this.address = address;
	}
}
