package com.tim.cake.online.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tim.cake.online.data.AddressData;
import com.tim.cake.online.data.LocationData;
import com.tim.cake.online.facade.AccountFacade;
import com.tim.cake.online.form.AddressForm;

import net.sf.json.JSONArray;


@Controller
@RequestMapping("/my-account")
public class MyAccountController
{
	private static final Logger LOG = Logger.getLogger(MyAccountController.class);

	public static final String REDIRECT_PREFIX = "redirect:";

	@Resource(name = "accountFacade")
	private AccountFacade accountFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model)
	{
		LOG.info("login my account page...");
		List<AddressData> datas = accountFacade.getAddressList();
		model.addAttribute("addresses", datas);
		return "account/index";
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.GET)
	public String addAddress(Model model)
	{
		AddressForm addressForm = new AddressForm();
		model.addAttribute("addressForm", addressForm);
		return "account/addAddress";
	}

	@RequestMapping(value = "/editAddress", method = RequestMethod.GET)
	public String editAddress(Model model, @RequestParam(value = "id") int id)
	{
		AddressData addressData = accountFacade.getAddressById(id);

		//所有的省
		List<LocationData> provinceDatas = accountFacade.getLocationInfoBySuperCode("1");

		String currentProvince = addressData.getProvinceCode();
		List<LocationData> cityDatas = accountFacade.getLocationInfoBySuperCode(currentProvince);

		String currentCity = addressData.getCityCode();
		List<LocationData> areaDatas = accountFacade.getLocationInfoBySuperCode(currentCity);

		model.addAttribute("provinceDatas", provinceDatas);
		model.addAttribute("cityDatas", cityDatas);
		model.addAttribute("areaDatas", areaDatas);

		model.addAttribute("addressData", addressData);

		return "account/editAddress";
	}

	@RequestMapping(value = "/addAddress", method = RequestMethod.POST)
	public String addAddress(Model model, AddressForm addressForm)
	{
		//1.校验数据
		model.addAttribute("addressForm", addressForm);

		//2.保存数据
		AddressData data = new AddressData();
		data.setRecipient(addressForm.getRecipient());
		data.setTel(addressForm.getTel());
		data.setProvinceCode(addressForm.getProvienceCode());
		data.setCityCode(addressForm.getCityCode());
		data.setAreaCode(addressForm.getAreaCode());
		data.setStreet(addressForm.getStreet());
		data.setIsDefault(addressForm.isDefault());
		data.setTagName(addressForm.getTagName());
		accountFacade.saveAddress(data);

		return REDIRECT_PREFIX + "/my-account";
	}

	@RequestMapping(value = "/editAddress", method = RequestMethod.POST)
	public String editAddress(Model model, AddressForm addressForm)
	{
		//1.校验数据

		//2.保存数据
		AddressData data = new AddressData();
		data.setId(addressForm.getId());
		data.setRecipient(addressForm.getRecipient());
		data.setTel(addressForm.getTel());
		data.setProvinceCode(addressForm.getProvienceCode());
		data.setCityCode(addressForm.getCityCode());
		data.setAreaCode(addressForm.getAreaCode());
		data.setStreet(addressForm.getStreet());
		data.setIsDefault(addressForm.isDefault());
		data.setTagName(addressForm.getTagName());
		accountFacade.editAddress(data);

		return REDIRECT_PREFIX + "/my-account";
	}

	@RequestMapping(value = "/delAddress/{id}", method = RequestMethod.GET)
	public String delAddress(Model model, @PathVariable("id") int id)
	{
		accountFacade.delAddress(id);
		return REDIRECT_PREFIX + "/my-account";
	}

	@RequestMapping(value = "/getLocationInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getLocationInfo(Model model, @RequestParam(value = "superCode") String superCode)
	{
		List<LocationData> datas = accountFacade.getLocationInfoBySuperCode(superCode);
		JSONArray obj = JSONArray.fromObject(datas);
		return obj.toString();
	}

	@RequestMapping(value = "/getAddressList", method = RequestMethod.GET)
	@ResponseBody
	public String getAddressList(Model model, @RequestParam(value = "superCode") String superCode)
	{
		List<AddressData> datas = accountFacade.getAddressList();
		JSONArray obj = JSONArray.fromObject(datas);
		return obj.toString();
	}

}
