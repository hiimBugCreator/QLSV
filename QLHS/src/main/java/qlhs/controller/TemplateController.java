package qlhs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
		Account acc = new Account();
		mv.addObject("account", acc);
		return mv;
	}
	
	@PostMapping()
	public ModelAndView handle(@ModelAttribute Account account, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		System.out.println(account.getTentaikhoan());
		System.out.println(account.getEmail());
		System.out.println(account.getMatkhau());
		System.out.println(account.getSodienthoai());
		mv.setViewName("dangnhap");
		return mv;
	}
}
