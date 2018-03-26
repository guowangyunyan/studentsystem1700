package com.training.dao.impl;

import java.util.Map;

import com.training.common.dao.CommonDao;
import com.training.dao.StudentDao;
import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;

public class StudentDaoImpl implements StudentDao {

	private CommonDao commonDao;

	@Override
	public SearchResult<StudentModel> queryStudentByFields(Map<String, Object> params, Pagination page) {
		return commonDao.getEntitiesByFields(StudentModel.class, params, null, page);
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}
