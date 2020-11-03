package qlhs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qlhs.model.Account;
import qlhs.service.AuthenticationService;

@Controller
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	AuthenticationService authen;
	
	
	@GetMapping
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("template");
		return mv;
	}
}
