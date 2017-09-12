package com.tim.cake.online.config;

import java.io.IOException;
import java.util.Properties;


public class Config
{
	private static final String PROPERTY_FILE = "project.properties";
	private static Properties properties = null;

	public Config()
	{
		properties = new Properties();
		try
		{
			properties.load(Config.class.getClassLoader().getResourceAsStream(PROPERTY_FILE));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static String getProperty(String name, String defaultValue)
	{
		String value = getProperty(name);
		return value == null ? defaultValue : value;
	}

	public static String getProperty(String name)
	{
		String value = properties.getProperty(name);
		return value;
	}
}
