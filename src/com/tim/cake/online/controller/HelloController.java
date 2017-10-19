package com.tim.cake.online.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tim.cake.online.data.CustomerData;
import com.tim.cake.online.facade.CustomerFacade;

import net.sf.json.JSONObject;


@Controller
public class HelloController
{

	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@RequestMapping("/hello")
	@ResponseBody
	public String sayHello()
	{
		return "hello world";
	}

	@RequestMapping("/hello2")
	public void sayHello2()
	{
		System.out.println("sayHello 2 ...");
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	@ResponseBody
	public String findUser(Model model, @RequestParam(value = "id", required = false) int id)
	{
		System.out.println("id=" + id);
		CustomerData customer = customerFacade.getCustomerById(10000000);
		JSONObject obj = JSONObject.fromObject(customer);
		return obj.toString();
	}
}
