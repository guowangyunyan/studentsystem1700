package com.training.dao;

import java.util.Map;

import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;

public interface StudentDao {

	SearchResult<StudentModel> queryStudentByFields(Map<String, Object> params, Pagination page);

}
