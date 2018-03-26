package com.training.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.util.VerifyCodeUtils;

@Controller
public class VerifyCodeController {

	@RequestMapping("/verifyCode")
	public void verifyCode(HttpSession session, HttpServletResponse response) throws IOException {
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		session.setAttribute("verifyCode", verifyCode);
		VerifyCodeUtils.outputImage(100, 30, response.getOutputStream(), verifyCode);
	}
}
