package com.tim.cake.online.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tim.cake.online.form.CustomerForm;


@Component("customerValidator")
public class CustomerValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return CustomerForm.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		CustomerForm form = (CustomerForm) object;
		String name = form.getName();
		String nickName = form.getNickName();
		String mobile = form.getMobile();
		String email = form.getEmail();
		String password = form.getPassword();
		String confirmPassword = form.getConfirmPassword();
		boolean isUpdate = form.isUpdate();

		if (!isUpdate)
		{
			if (StringUtils.isBlank(name))
			{
				errors.rejectValue("name", "customer.name.invalid");
			}
			else if (StringUtils.length(name) > 50)
			{
				errors.rejectValue("name", "customer.name.length.invalid");
			}
		}

		if (StringUtils.isBlank(nickName))
		{
			errors.rejectValue("nickName", "customer.nickName.invalid");
		}
		else if (StringUtils.length(nickName) > 50)
		{
			errors.rejectValue("nickName", "customer.nickName.length.invalid");
		}

		if (StringUtils.isBlank(mobile))
		{
			errors.rejectValue("mobile", "customer.mobile.invalid");
		}
		else if (!isMobile(mobile))
		{
			errors.rejectValue("mobile", "customer.mobile.format.invalid");
		}

		if (StringUtils.isBlank(email))
		{
			errors.rejectValue("email", "customer.email.invalid");
		}
		else if (!isEmail(email))
		{
			errors.rejectValue("email", "customer.email.format.invalid");
		}

		if (!isUpdate)
		{
			if (StringUtils.isBlank(password))
			{
				errors.rejectValue("password", "customer.password.invalid");
			}
			else if (StringUtils.length(password) > 50)
			{
				errors.rejectValue("password", "customer.password.length.invalid");
			}
			else if (!StringUtils.equals(password, confirmPassword))
			{
				errors.rejectValue("confirmPassword", "customer.confirmpassword.invalid");
			}
		}

	}

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile)
	{
		Pattern regex = Pattern.compile("^1[34578]\\d{9}$");
		Matcher matcher = regex.matcher(mobile);
		return matcher.matches();
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email)
	{
		return Pattern.matches(REGEX_EMAIL, email);
	}

	public static boolean checkMobileNumber(String mobileNumber)
	{
		boolean flag = false;
		try
		{
			Pattern regex = Pattern.compile("^1[34578]\\d{9}$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		}
		catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}

	public static void main(String[] args)
	{
		System.out.println(checkMobileNumber("15934076040"));
	}
}
