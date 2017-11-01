package com.tim.cake.online.utils;

import java.io.IOException;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tim.cake.online.model.AddressModel;
import com.tim.generator.NameGenerator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AddressServiceTest
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
		AddressModel model = new AddressModel();
		AddressModel m2 = session.get(AddressModel.class, 2);
		model.setAreaCode(m2.getAreaCode());
		model.setCityCode(m2.getCityCode());
		model.setCustomer(m2.getCustomer());
		model.setProvienceCode(m2.getProvienceCode());
		model.setRecipient(NameGenerator.generatorHistoryName());
		model.setStreet(m2.getStreet());
		model.setTagName("学校");
		model.setTel("18202998765");
		session.save(model);
		//session.delete(m2);
		session.getTransaction().commit();
		session.close();
		mySessionFactory.close();
	}

}
