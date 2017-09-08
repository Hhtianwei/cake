package com.tim.cake.online.validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tim.cake.online.form.ProductForm;


@Component("productValidator")
public class ProductValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return ProductForm.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		ProductForm form = (ProductForm) object;
		String name = form.getName();
		String longName = form.getLongName();
		String shape = form.getShape();
		String location = form.getLocation();
		boolean flag = form.isFlag();

		if (StringUtils.isBlank(name))
		{
			errors.rejectValue("name", "product.name.invalid");
		}
		else if (StringUtils.length(name) > 50)
		{
			errors.rejectValue("name", "product.name.length.invalid");
		}

		if (StringUtils.isBlank(longName))
		{
			errors.rejectValue("longName", "product.longName.invalid");
		}
		else if (StringUtils.length(name) > 200)
		{
			errors.rejectValue("longName", "product.longName.length.invalid");
		}

		if (StringUtils.isBlank(shape))
		{
			errors.rejectValue("shape", "product.shape.invalid");
		}
		else if (StringUtils.length(name) > 20)
		{
			errors.rejectValue("shape", "product.shape.length.invalid");
		}

		if (StringUtils.length(location) > 20)
		{
			errors.rejectValue("location", "product.location.length.invalid");
		}

		if (flag)
		{
			String pid = form.getPid();
			String size = form.getSize();
			String price = form.getPrice();
			String stock = form.getStock();

			if (StringUtils.isBlank(pid))
			{
				errors.rejectValue("pid", "product.pid.invalid");
			}
			else if (!NumberUtils.isNumber(pid))
			{
				errors.rejectValue("pid", "product.pid.format.invalid");
			}

			if (StringUtils.isBlank(size))
			{
				errors.rejectValue("size", "product.size.invalid");
			}
			else if (StringUtils.length(size) > 20)
			{
				errors.rejectValue("pid", "product.size.length.invalid");
			}

			if (StringUtils.isBlank(price))
			{
				errors.rejectValue("price", "product.price.invalid");
			}
			else if (!NumberUtils.isNumber(price) || Double.parseDouble(price) < 0)
			{
				errors.rejectValue("price", "product.price.format.invalid");
			}

			if (StringUtils.isBlank(stock))
			{
				errors.rejectValue("stock", "product.stock.invalid");
			}
			else if (!NumberUtils.isNumber(stock) || Double.parseDouble(stock) < 0)
			{
				errors.rejectValue("price", "product.price.format.invalid");
			}
			else
			{
				try
				{
					Integer.parseInt(stock);
				}
				catch (NumberFormatException e)
				{
					errors.rejectValue("price", "product.price.format2.invalid");
				}
			}
		}

	}

}
