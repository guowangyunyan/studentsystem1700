package com.training.service.impl;

import com.training.common.service.CommonService;
import com.training.convert.StudentModelConvert;
import com.training.form.StudentForm;
import com.training.model.StudentModel;
import com.training.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private StudentModelConvert studentModelConvert;
	private CommonService commonService;

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

}
