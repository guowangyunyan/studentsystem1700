package com.training.service;

import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;

public interface StudentService {

	void addStudent(StudentForm studentForm);

	SearchResult<StudentData> loadStudentsByFields(StudentForm studentForm, Pagination page);

	StudentData queryStudnetById(StudentForm studentForm);

	void deleteStudent(StudentForm studentForm);

	void updateStudent(StudentForm studentForm);

}
