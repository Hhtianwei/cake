package com.tim.cake.online.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tim.cake.online.data.CartData;
import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.data.EntryData;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.model.CartModel;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.model.EntryModel;
import com.tim.cake.online.model.ProductModel;


public class CartConvert implements Convert<CartModel, CartData>
{

	private Convert<CustomerModel, CustomerData> customerConvert;

	private Convert<ProductModel, ProductData> productConvert;

	@Override
	public void convert(CartModel source, CartData target)
	{
		target.setId(source.getId());
		target.setCreateDate(source.getCreateTime());

		setCustomerData(source, target);

		setEntires(source, target);

		setTotalPrice(source, target);
	}

	private void setTotalPrice(CartModel source, CartData target)
	{
		List<EntryModel> list = source.getEntries();
		if (CollectionUtils.isEmpty(list))
		{
			return;
		}
		double total = 0d;
		for (EntryModel entry : list)
		{
			total += entry.getTotalPrice();
		}
		target.setTotalPrice(total);
	}

	private void setEntires(CartModel source, CartData target)
	{
		List<EntryModel> list = source.getEntries();
		List<EntryData> entries = new ArrayList<EntryData>();
		if (CollectionUtils.isEmpty(list))
		{
			return;
		}
		for (EntryModel entry : list)
		{
			EntryData entryData = new EntryData();
			entryData.setCartId(source.getId());
			entryData.setId(entry.getId());
			entryData.setPrice(entry.getPrice());
			ProductData productData = new ProductData();
			productConvert.convert(entry.getProduct(), productData);
			entryData.setProduct(productData);
			entryData.setQuantity(entry.getQuantity());
			entryData.setTotalPrice(entry.getTotalPrice());
			entries.add(entryData);
		}
		target.setEntries(entries);
	}

	private void setCustomerData(CartModel source, CartData target)
	{
		CustomerModel customerModel = source.getCustomer();
		if (customerModel == null)
		{
			return;
		}
		CustomerData customerData = new CustomerData();
		customerConvert.convert(customerModel, customerData);
		target.setCustomer(customerData);
	}

	public Convert<CustomerModel, CustomerData> getCustomerConvert()
	{
		return customerConvert;
	}

	public void setCustomerConvert(Convert<CustomerModel, CustomerData> customerConvert)
	{
		this.customerConvert = customerConvert;
	}

	public Convert<ProductModel, ProductData> getProductConvert()
	{
		return productConvert;
	}

	public void setProductConvert(Convert<ProductModel, ProductData> productConvert)
	{
		this.productConvert = productConvert;
	}

}
