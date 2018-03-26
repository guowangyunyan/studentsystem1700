package com.training.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.data.StudentData;
import com.training.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;
import com.training.service.StudentService;

@Controller
public class StudentController {
	@Resource
	private StudentService studentService;

	@RequestMapping("/addStudent")
	public String addStudent() {
		return "student/addStudent";
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(StudentForm studentForm) {
		studentService.addStudent(studentForm);
		return "redirect:/loadStudentsByFields";
	}

	@RequestMapping("/loadStudentsByFields")
	public String loadStudentsByFields(StudentForm studentForm, Model model,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
		Pagination page = new Pagination();
		page.setCurrentPage(currentPage);
		page.setPageSize(pageSize);

		SearchResult<StudentData> studentDatas = studentService.loadStudentsByFields(studentForm, page);
		model.addAttribute("studentDatas", studentDatas);
		return "student/students";
	}

	@RequestMapping("/modifyStudent")
	public String modifyStudent(StudentForm studentForm, Model model) {
		StudentData studentData = studentService.queryStudnetById(studentForm);
		model.addAttribute("studentData", studentData);
		return "student/modifyStudent";

	}

	@RequestMapping(value = "modifyStudent", method = RequestMethod.POST)
	public String modifyStudent(StudentForm studentForm) {
		studentService.updateStudent(studentForm);
		return "redirect:/loadStudentsByFields";

	}

	@RequestMapping("/deleteStudent")
	public String deleteStudent(StudentForm studentForm) {
		studentService.deleteStudent(studentForm);
		return "redirect:/loadStudentsByFields";
	}
}
