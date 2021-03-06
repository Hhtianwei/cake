package com.tim.cake.online.common.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.tim.cake.online.common.dao.CommonDAO;


public class CommonService
{
	private CommonDAO commonDao;

	public <T> void save(T entry)
	{
		commonDao.saveOrUpdateEntity(entry);
	}

	public <T> void refresh(T entry)
	{
		commonDao.refresh(entry);
	}

	public <T> void merge(T entry)
	{
		commonDao.merge(entry);
	}

	public <T> void update(T entry)
	{
		commonDao.update(entry);
	}

	public <T> void saveAll(Collection<T> entities)
	{
		commonDao.saveOrUpdateAllEntity(entities);
	}

	public <T> void delete(T entry)
	{
		commonDao.delete(entry);
	}

	public <T> void deleteAll(Collection<T> entities)
	{
		commonDao.deleteAll(entities);
	}

	public <T> Object getEntityById(Class clazz, int id)
	{
		return commonDao.get(clazz, id);
	}

	public <T> List getAllEntites(Class clazz)
	{
		return commonDao.loadAllEntities(clazz);
	}

	public <T> List getAllEntitesByField(Class clazz, HashMap<String, Object> params)
	{
		return commonDao.getEntitiesByFields(clazz, params);
	}

	public <T> List getAllEntitesByField(Class clazz, HashMap<String, Object> params, HashMap<String, String> orders)
	{
		return commonDao.getEntitiesByFields(clazz, params, orders);
	}

	public CommonDAO getCommonDao()
	{
		return commonDao;
	}

	public void setCommonDao(CommonDAO commonDao)
	{
		this.commonDao = commonDao;
	}
}
