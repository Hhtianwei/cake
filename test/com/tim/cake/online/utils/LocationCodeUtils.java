package com.tim.cake.online.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tim.cake.online.model.LocationModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LocationCodeUtils
{
	@Resource(name = "mySessionFactory")
	private SessionFactory mySessionFactory;

	private static final int ROOT = 1;

	private static final int PROVINCE_START = 100;

	private static final int STEP = 1;

	private Session session = null;

	@Before
	public void setup()
	{
		session = mySessionFactory.openSession();
		session.beginTransaction();
	}

	@Test
	public void test() throws IOException
	{
		Properties property = new Properties();
		property.load(new InputStreamReader(LocationCodeUtils.class.getClassLoader().getResourceAsStream("location.properties")));
		String location = property.getProperty("location");
		JSONArray jsonArray = JSONArray.fromObject(location);
		List<LocationModel> list = new ArrayList<LocationModel>();
		for (int i = 0; i < jsonArray.size(); i++)
		{

			JSONObject json = jsonArray.getJSONObject(i);

			//province
			String provinceName = json.getString("name");

			LocationModel province = new LocationModel();
			String provinceCode = Integer.toString(PROVINCE_START + STEP + i);
			province.setCode(provinceCode);
			province.setSuperCode(Integer.toString(ROOT));
			province.setName(provinceName);
			province.setProvince(Boolean.TRUE);
			list.add(province);

			JSONArray citys = json.getJSONArray("city");
			for (int j = 0; j < citys.size(); j++)
			{
				//city
				JSONObject city = citys.getJSONObject(j);
				String cityName = city.getString("name");

				LocationModel cityModel = new LocationModel();
				String cityCode = provinceCode + createSetpCode(j, 3);
				cityModel.setCode(cityCode);
				cityModel.setSuperCode(provinceCode);
				cityModel.setName(cityName);
				cityModel.setCity(true);
				list.add(cityModel);


				JSONArray areas = (JSONArray) city.get("area");
				for (int k = 0; k < areas.size(); k++)
				{
					String areaName = areas.getString(k);

					LocationModel areaModel = new LocationModel();
					String areaCode = cityCode + createSetpCode(k, 3);
					areaModel.setCode(areaCode);
					areaModel.setSuperCode(cityCode);
					areaModel.setArea(true);
					areaModel.setName(areaName);
					list.add(areaModel);
				}
			}

		}

		//		for (LocationModel model : list)
		//		{
		//			session.save(model);
		//		}
		//		session.getTransaction().commit();
		//		session.close();
		//		mySessionFactory.close();

	}

	/**
	 * 
	 * @param j
	 *           从哪个数开始补
	 * @param i
	 *           补成几位数
	 * @return
	 */
	private String createSetpCode(int j, int i)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(j);
		while (sb.length() < i)
		{
			sb.insert(0, "0");
		}
		return sb.toString();
	}

}
