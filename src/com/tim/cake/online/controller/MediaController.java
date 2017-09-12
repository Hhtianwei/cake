package com.tim.cake.online.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tim.cake.online.config.Config;


@Controller
public class MediaController
{

	private static final String PRODUCT_IMAGE = "cake.image.base.path";
	private static final String CONTENT_TYPE = "image/jpg";

	@RequestMapping(value = "/medias", method = RequestMethod.GET)
	public void medias(Model model, @RequestParam(value = "context") String context, HttpServletResponse response)
			throws IOException
	{
		String basePath = Config.getProperty(PRODUCT_IMAGE);
		byte[] fileNameByte = Base64.decodeBase64(context);
		String filePath = basePath + File.separator + new String(fileNameByte);
		File file = new File(filePath);
		InputStream inputStream = new FileInputStream(file);
		byte[] b = new byte[inputStream.available()];
		inputStream.read(b);
		response.setContentType(CONTENT_TYPE);
		response.getOutputStream().write(b);
		inputStream.close();
	}
}
