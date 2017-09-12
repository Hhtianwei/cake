package com.tim.cake.online.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tim.cake.online.common.page.Pagination;
import com.tim.cake.online.common.page.SearchResult;
import com.tim.cake.online.config.Config;
import com.tim.cake.online.data.ProductData;
import com.tim.cake.online.facade.ProductFacade;
import com.tim.cake.online.form.ProductForm;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin")
public class AdminController
{
	private static final Logger LOG = Logger.getLogger(AdminController.class);

	public static final String REDIRECT_PREFIX = "redirect:";

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "productValidator")
	private Validator productValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model)
	{
		LOG.info("login admin page...");
		return "admin/index";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String productDisplay(Model model)
	{
		ProductForm productForm = new ProductForm();
		model.addAttribute("productForm", productForm);
		return "admin/addProduct";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(MultipartHttpServletRequest request, Model model, ProductForm productForm,
			BindingResult bindingResult)
	{
		productValidator.validate(productForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			LOG.error("add product error...");
			model.addAttribute("productForm", productForm);
			return "admin/addProduct";
		}

		ProductData data = createProductData(productForm, request);
		productFacade.save(data);
		return REDIRECT_PREFIX + "/admin/productManage";
	}

	private ProductData createProductData(ProductForm productForm, MultipartHttpServletRequest request)
	{
		ProductData data = new ProductData();
		boolean flag = productForm.isFlag();
		if (flag)
		{
			int pId = Integer.parseInt(productForm.getPid());
			data.setPid(pId);
			data.setPrice(Double.parseDouble(productForm.getPrice()));
			data.setSize(productForm.getSize());
			data.setStock(Integer.parseInt(productForm.getStock()));
			data.setChild(true);
		}
		else
		{
			data.setLocation(productForm.getLocation());
			data.setLongName(productForm.getLongName());
			data.setName(productForm.getName());
			data.setShape(productForm.getShape());
			String imageName = handleImage(request);
			data.setImageUrl(imageName);
		}
		return data;
	}

	private String handleImage(MultipartHttpServletRequest request)
	{
		FileOutputStream fileOutputStream = null;
		List<MultipartFile> listUploadFile = request.getFiles("files");
		if (listUploadFile == null || listUploadFile.size() == 0)
		{
			LOG.info("upload file,but file list is empty");
			return null;
		}
		String newFileName = null;
		for (int i = 0; i < listUploadFile.size(); i++)
		{
			MultipartFile file = listUploadFile.get(i);

			if (!file.isEmpty())
			{
				String fileName = file.getOriginalFilename();
				LOG.info(String.format("upload file,file name is %s", fileName));
				String name = fileName.split("\\.")[0];
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				//文件名重新命名 oldName + random +  time
				double r = Math.random();
				newFileName = r + "_" + System.currentTimeMillis() + suffix;
				String basePath = Config.getProperty("cake.image.base.path");
				String filePath = basePath + "\\" + newFileName;
				LOG.info(String.format("upload file,filePath  is %s", filePath));
				File files = new File(filePath);
				if (files.exists())
				{
					files.delete();
				}
				try
				{
					fileOutputStream = new FileOutputStream(files);
					fileOutputStream.write(file.getBytes());
					fileOutputStream.flush();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				if (fileOutputStream != null)
				{
					try
					{
						fileOutputStream.close();
					}
					catch (IOException ie)
					{
						ie.printStackTrace();
					}
				}
			}
		}
		return Base64.encodeBase64String(newFileName.getBytes());
	}

	@RequestMapping(value = "/queryAllParent", method = RequestMethod.GET)
	@ResponseBody
	public String queryParentProducts(Model model, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "id", defaultValue = "0", required = false) String id)
	{
		Pagination page = new Pagination();
		if (currentPage <= 0)
		{
			page.setCurrentPage(1);
		}
		else
		{
			page.setCurrentPage(currentPage);
		}
		page.setPageSize(3);

		ProductData productData = new ProductData();
		productData.setName(name);
		productData.setId(Integer.parseInt(id));

		SearchResult<ProductData> searchResult = productFacade.getAllProductWithPagniation(page, productData);
		JSONObject data = JSONObject.fromObject(searchResult);
		return data.toString();
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String updateProduct(Model model)
	{

		return "admin/updateProduct";
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(Model model, HttpServletRequest r)
	{
		return "admin/productList";
	}


	@RequestMapping(value = "/customerManage", method = RequestMethod.GET)
	public String customerManage(Model model)
	{

		return "admin/customerList";
	}
}
