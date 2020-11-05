package qlhs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qlhs.model.Account;
import qlhs.repository.AccountRepository;
import qlhs.service.AccountService;
import qlhs.service.AuthenticationService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AuthenticationService authen;
	@Autowired
	AccountService accountServiec;
	@Autowired
	AccountRepository accountRepo;
	
	@GetMapping("/changepassword")
	public ModelAndView changepass(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			String state = "";
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			mv.setViewName("quenmatkhau");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@PostMapping("/change")
	public ModelAndView change(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		if(status) {
			Account a = (Account)request.getSession().getAttribute("user");
			a.setMatkhau(request.getParameter("password"));
			accountRepo.save(a);
			mv.setViewName("redirect:/admin/configaccount");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
}
