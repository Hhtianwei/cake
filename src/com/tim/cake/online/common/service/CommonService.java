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
		return commonDao.load(clazz, id);
	}

	public <T> List getAllEntites(Class clazz)
	{
		return commonDao.loadAllEntities(clazz);
	}

	public <T> List getAllEntitesByField(Class clazz, HashMap<String, Object> params)
	{
		return commonDao.getEntitiesByFields(clazz, params);
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
