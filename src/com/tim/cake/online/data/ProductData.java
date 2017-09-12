package com.tim.cake.online.data;

import java.util.List;


public class ProductData
{
	private int id;
	private String name;//简称
	private String longName;//全称
	private double price;//价格
	private String location;//产地
	private String shape;//形状
	private String size;
	private int pid;
	private int stock;
	private boolean child;
	private String imageUrl;

	private boolean defaultProduct;

	List<ProductData> subProductDatas;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLongName()
	{
		return longName;
	}

	public void setLongName(String longName)
	{
		this.longName = longName;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getShape()
	{
		return shape;
	}

	public void setShape(String shape)
	{
		this.shape = shape;
	}

	public String getSize()
	{
		return size;
	}

	public void setSize(String size)
	{
		this.size = size;
	}

	public int getPid()
	{
		return pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}

	public int getStock()
	{
		return stock;
	}

	public void setStock(int stock)
	{
		this.stock = stock;
	}

	public boolean isChild()
	{
		return child;
	}

	public void setChild(boolean child)
	{
		this.child = child;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public List<ProductData> getSubProductDatas()
	{
		return subProductDatas;
	}

	public void setSubProductDatas(List<ProductData> subProductDatas)
	{
		this.subProductDatas = subProductDatas;
	}

	public boolean isDefaultProduct()
	{
		return defaultProduct;
	}

	public void setDefaultProduct(boolean defaultProduct)
	{
		this.defaultProduct = defaultProduct;
	}
}
