package com.tim.cake.online.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tim.cake.online.common.service.CommonService;
import com.tim.cake.online.common.service.SessionService;
import com.tim.cake.online.model.CartModel;
import com.tim.cake.online.model.CustomerModel;
import com.tim.cake.online.model.EntryModel;
import com.tim.cake.online.model.ProductModel;
import com.tim.cake.online.model.SizeProductModel;
import com.tim.cake.online.service.CartService;
import com.tim.cake.online.service.CustomerService;


public class CartServiceImpl implements CartService
{
	public static final String SESSION_CART = "cart";

	private SessionService sessionService;

	private CommonService commonService;

	private CustomerService customerService;

	@Override
	public CartModel createCart()
	{
		CartModel cart = new CartModel();
		return cart;
	}

	@Override
	public CartModel getSessionCart()
	{
		CartModel cart = (CartModel) sessionService.getSessionAttr(SESSION_CART);
		if (cart == null)
		{
			synchronized (this)
			{
				cart = createCart();
				sessionService.setSessionAttr(SESSION_CART, cart);
			}
		}
		return cart;
	}

	@Override
	public void addToCart(ProductModel product, int quantity)
	{
		CartModel cart = getSessionCart();
		List<EntryModel> list = cart.getEntries();
		if (CollectionUtils.isEmpty(list))
		{
			EntryModel entry = addProductToEntry(cart, product);
			list = new ArrayList<EntryModel>();
			list.add(entry);
		}
		else
		{
			boolean hasPro = false;
			for (EntryModel entry : list)
			{
				ProductModel pro = entry.getProduct();
				if (pro.getId() == product.getId())
				{
					hasPro = true;
					int originQuantity = entry.getQuantity();
					entry.setQuantity(originQuantity + 1);
					entry.setTotalPrice(entry.getPrice() * (quantity + originQuantity));
					commonService.merge(entry);
				}
			}

			if (!hasPro)
			{
				EntryModel entry = addProductToEntry(cart, product);
				list = new ArrayList<EntryModel>();
				list.add(entry);
			}
		}
		cart.setEntries(list);
		commonService.merge(cart);
		commonService.refresh(cart);
	}

	private EntryModel addProductToEntry(CartModel cart, ProductModel product)
	{
		SizeProductModel sizeProduct = (SizeProductModel) product;
		EntryModel entry = new EntryModel();
		entry.setCart(cart);
		entry.setPrice(sizeProduct.getPrice());
		entry.setProduct(sizeProduct);
		entry.setQuantity(1);
		entry.setTotalPrice(sizeProduct.getPrice() * 1);
		commonService.save(entry);
		commonService.refresh(entry);
		return entry;
	}

	@Override
	public void updateCart(int quantity)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCart(CartModel model)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void merge()
	{
		CustomerModel customerModel = customerService.getCurrentCustomer();
		CartModel cartModel = customerModel.getCart();
		CartModel sessionCart = this.getSessionCart();
		if (cartModel == null)
		{
			cartModel = this.createCart();
			cartModel.setCustomer(customerModel);
			commonService.save(cartModel);
			commonService.refresh(cartModel);
		}
		List<EntryModel> entries = cartModel.getEntries();
		List<EntryModel> sessionEntries = sessionCart.getEntries();
		if (CollectionUtils.isEmpty(entries))
		{
			cartModel.setEntries(sessionEntries);
		}
		else
		{
			List<EntryModel> tempEntries = new ArrayList<EntryModel>();
			for (EntryModel sessionEntry : sessionEntries)
			{
				ProductModel sessionPro = sessionEntry.getProduct();
				if (sessionPro == null)
				{
					continue;
				}
				for (EntryModel customerEntry : entries)
				{
					ProductModel customerPro = customerEntry.getProduct();
					if (customerPro == null)
					{
						continue;
					}
					if (customerPro.getId() == sessionPro.getId())
					{
						int quantity = sessionEntry.getQuantity();
						int customerQuantity = customerEntry.getQuantity();
						customerEntry.setQuantity(quantity + customerQuantity);
						customerEntry.setTotalPrice(customerEntry.getTotalPrice() + sessionEntry.getTotalPrice());
						commonService.merge(customerEntry);
						break;
					}
				}

				//如果没有找到对应 的产品，则会进入这里
				tempEntries.add(sessionEntry);
			}
			entries.addAll(tempEntries);
			cartModel.setEntries(entries);
		}

		commonService.merge(cartModel);
		commonService.refresh(cartModel);
		sessionService.setSessionAttr(SESSION_CART, cartModel);
	}

	public SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public CommonService getCommonService()
	{
		return commonService;
	}

	public void setCommonService(CommonService commonService)
	{
		this.commonService = commonService;
	}

	public CustomerService getCustomerService()
	{
		return customerService;
	}

	public void setCustomerService(CustomerService customerService)
	{
		this.customerService = customerService;
	}

}
