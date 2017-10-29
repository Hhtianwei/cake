package com.tim.cake.online.data;

public class LocationData
{

	private int id;
	private String code;
	private String name;
	private boolean isProvince;
	private boolean isCity;
	private boolean isArea;
	private String superCode;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isProvince()
	{
		return isProvince;
	}

	public void setProvince(boolean isProvince)
	{
		this.isProvince = isProvince;
	}

	public boolean isCity()
	{
		return isCity;
	}

	public void setCity(boolean isCity)
	{
		this.isCity = isCity;
	}


	public boolean isArea()
	{
		return isArea;
	}

	public void setArea(boolean isArea)
	{
		this.isArea = isArea;
	}

	public String getSuperCode()
	{
		return superCode;
	}

	public void setSuperCode(String superCode)
	{
		this.superCode = superCode;
	}

}
