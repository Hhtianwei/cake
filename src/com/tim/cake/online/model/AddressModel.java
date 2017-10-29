package com.tim.cake.online.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class AddressModel
{
	public static final String ID = "id";
	public static final String TAGNAME = "tagName";
	public static final String PROVIENCECODE = "provienceCode";
	public static final String CTIYCODE = "cityCode";
	public static final String TOWNCODE = "townCode";
	public static final String STREET = "street";
	public static final String RECIPIENT = "recipient";//收件人
	public static final String TEL = "tel";
	public static final String CUSTOMER = "customer";

	private int id;
	private String tagName;
	private String provienceCode;
	private String cityCode;
	private String areaCode;
	private String street;
	private String recipient;
	private String tel;
	private CustomerModel customer;
	private boolean isDefault;

	private Date createTime = new Date();

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


	public String getTagName()
	{
		return tagName;
	}

	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	public String getProvienceCode()
	{
		return provienceCode;
	}

	public void setProvienceCode(String provienceCode)
	{
		this.provienceCode = provienceCode;
	}

	public String getCityCode()
	{
		return cityCode;
	}

	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getRecipient()
	{
		return recipient;
	}

	public void setRecipient(String recipient)
	{
		this.recipient = recipient;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId")
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

	public Boolean getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault)
	{
		this.isDefault = isDefault;
	}

}
