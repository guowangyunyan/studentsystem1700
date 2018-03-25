package com.training.common.service;

import java.util.List;
import java.util.Map;

import com.training.common.dao.CommonDao;

public class CommonService {

	private CommonDao commonDao;

	public <T> List<T> loadAllEntities(Class<T> clazz) {
		return commonDao.loadAllEntities(clazz);
	}

	public <T> Object load(Class<T> clazz, Integer id) {
		return commonDao.load(clazz, id);
	}

	public <T> void saveOrUpdateEntity(T entry) {
		commonDao.saveOrUpdateEntity(entry);
	}

	public <T> void delete(T entry) {
		commonDao.delete(entry);
	}

	public <T> List<T> getEntitiesByFields(Class<T> clazz, Map<String, Object> fields) {
		return commonDao.getEntitiesByFields(clazz, fields);
	}

	public <T> List<T> getEntitiesByField(Class<T> clazz, String field, Object value) {
		return commonDao.getEntitiesByField(clazz, field, value);
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

}
