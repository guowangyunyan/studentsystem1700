package com.training.convert;

import java.util.Date;

import com.training.form.StudentForm;
import com.training.model.StudentModel;
import com.training.util.DateUtil;

public class StudentModelConvert implements Convert<StudentForm,StudentModel >{

	@Override
	public StudentModel createTarget() {
		
		return new StudentModel();
	}

	@Override
	public StudentModel convert(StudentForm studentForm) {
		StudentModel model=createTarget();
		model.setName(studentForm.getName());
		model.setClazz(studentForm.getClazz());
		model.setBirthday(DateUtil.getDateByString(studentForm.getBirthday()));
		model.setCreateTime(new Date());
		model.setModifyTime(new Date());
		model.setAvailable(true);
		return model;
	}
	
	


}
