package com.training.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.service.UserService;

@Controller
public class LoginController {

	// Validator Spring框架里的一个接口
	@Resource
	private Validator userValidator;
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(Model model, UserForm userForm, BindingResult bindingResult, HttpSession session,
			HttpServletResponse response) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "user/login";
		}

		String verifyCode = (String) session.getAttribute("verifyCode");
		if (!verifyCode.equals(userForm.getVerifyCode())) {
			return "user/login";
		}

		UserData userData = userService.findUser(userForm);
		if (null == userData) {
			return "user/login";
		}

		if (userForm.isRememberMe()) {
			Cookie c = new Cookie("rememberMe", userForm.getName() + "(&)" + userForm.getPassword());
			c.setMaxAge(60 * 60 * 24 * 3);
			response.addCookie(c);
		}

		session.setAttribute("userData", userData);

		return "redirect:loadStudentsByFields";
	}
}
