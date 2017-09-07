package com.tim.cake.online.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class ProductModel
{
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String LONGNAME = "longName";
	public static final String LOCATION = "location";
	public static final String SHAPE = "shape";

	private int id;
	private String name;//简称
	private String longName;//全称

	private String location;//产地
	private String shape;//形状

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

	@Column(name = "name", length = 50, unique = true)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "longName", length = 200, unique = true)
	public String getLongName()
	{
		return longName;
	}

	public void setLongName(String longName)
	{
		this.longName = longName;
	}

	@Column(name = "location", length = 20)
	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	@Column(name = "shape", length = 20)
	public String getShape()
	{
		return shape;
	}

	public void setShape(String shape)
	{
		this.shape = shape;
	}

}
