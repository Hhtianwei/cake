package com.tim.cake.online.data;

public class AddressData
{

	private int id;
	private String tagName;
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private String provinceName;
	private String cityName;
	private String areaName;
	private String street;
	private String recipient;
	private String tel;
	private CustomerData customer;
	private Boolean isDefault;

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

	public CustomerData getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerData customer)
	{
		this.customer = customer;
	}

	public Boolean getIsDefault()
	{
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault)
	{
		this.isDefault = isDefault;
	}


	public String getProvinceCode()
	{
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode)
	{
		this.provinceCode = provinceCode;
	}

	public String getProvinceName()
	{
		return provinceName;
	}

	public void setProvinceName(String provinceName)
	{
		this.provinceName = provinceName;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}


}
