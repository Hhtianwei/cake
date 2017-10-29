package com.tim.cake.online.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "location")
public class LocationModel
{

	public static final String ID = "id";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String ISPROVINCE = "isProvince";
	public static final String ISCITY = "isCity";
	public static final String ISAREA = "isArea";
	public static final String SUPERCODE = "superCode";

	private int id;
	private String code;
	private String name;
	private boolean isProvince;
	private boolean isCity;
	private boolean isArea;
	private String superCode;

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
