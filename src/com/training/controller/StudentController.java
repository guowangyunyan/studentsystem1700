package com.training.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.form.StudentForm;
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
}
