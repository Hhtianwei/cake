package com.tim.cake.online.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tim.cake.online.form.PasswordForm;


@Component("passwordValidator")
public class PasswordValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return PasswordForm.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors)
	{
		PasswordForm form = (PasswordForm) object;
		String oldPassword = form.getOldPassword();
		String newPassword = form.getNewPassword();
		String confirmPpassword = form.getConfirmPassword();

		if (StringUtils.isBlank(oldPassword))
		{
			errors.rejectValue("oldPassword", "password.oldPassword.invalid");
		}
		else if (StringUtils.length(oldPassword) > 50)
		{
			errors.rejectValue("oldPassword", "password.oldPassword.length.invalid");
		}

		if (StringUtils.isBlank(newPassword))
		{
			errors.rejectValue("newPassword", "password.newPassword.invalid");
		}
		else if (StringUtils.length(newPassword) > 50)
		{
			errors.rejectValue("newPassword", "password.newPassword.length.invalid");
		}
		else if (!StringUtils.equals(newPassword, confirmPpassword))
		{
			errors.rejectValue("newPassword", "password.confirmpassword.invalid");
		}
	}
}
