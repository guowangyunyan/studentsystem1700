package com.training.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.training.common.service.CommonService;
import com.training.convert.StudentDataConvert;
import com.training.convert.StudentModelConvert;
import com.training.dao.StudentDao;
import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;
import com.training.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentModelConvert studentModelConvert;
	private CommonService commonService;
	private StudentDao studentDao;
	private StudentDataConvert studentDataConvert;

	@Override
	public void addStudent(StudentForm studentForm) {
		StudentModel studentModel = studentModelConvert.convert(studentForm);
		commonService.saveOrUpdateEntity(studentModel);
	}

	public StudentModelConvert getStudentModelConvert() {
		return studentModelConvert;
	}

	public void setStudentModelConvert(StudentModelConvert studentModelConvert) {
		this.studentModelConvert = studentModelConvert;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Override
	public SearchResult<StudentData> loadStudentsByFields(StudentForm studentForm, Pagination page) {
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.isNotBlank(studentForm.getName())) {
			params.put(StudentModel.NAME, studentForm.getName());
		}
		if (StringUtils.isNotBlank(studentForm.getClazz())) {
			params.put(StudentModel.CLASS, studentForm.getClazz());
		}

		SearchResult<StudentModel> searchResults = studentDao.queryStudentByFields(params, page);
		// studentConvert

		SearchResult<StudentData> results = new SearchResult<StudentData>();
		List<StudentData> datas = new ArrayList<>();
		for (StudentModel model : searchResults.getResult()) {
			datas.add(studentDataConvert.convert(model));
		}
		results.setPagination(searchResults.getPagination());
		results.setResult(datas);
		return results;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public StudentDataConvert getStudentDataConvert() {
		return studentDataConvert;
	}

	public void setStudentDataConvert(StudentDataConvert studentDataConvert) {
		this.studentDataConvert = studentDataConvert;
	}

	@Override
	public StudentData queryStudnetById(StudentForm studentForm) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
		return studentDataConvert.convert(studentModel);

	}

	@Override
	public void deleteStudent(StudentForm studentForm) {
		StudentModel studentModel = studentModelConvert.convert(studentForm);
		commonService.delete(studentModel);

	}

	@Override
	public void updateStudent(StudentForm studentForm) {
		StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
		commonService.saveOrUpdateEntity(studentModel);
	}

}
