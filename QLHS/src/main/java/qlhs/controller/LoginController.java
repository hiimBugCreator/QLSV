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
import qlhs.service.AuthenticationService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	AuthenticationService authen;
	@Autowired
	AccountRepository acccount;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("dangnhap");
		Account acc = new Account();
		mv.addObject("account", acc);
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	@PostMapping()
	public ModelAndView signin(@ModelAttribute Account account,
								HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String taikhoan=account.getTentaikhoan();
		String password=account.getMatkhau();
		
		System.out.println(taikhoan);
		Account accountnow = authen.checkLogin(taikhoan, password);
		if(accountnow!=null) {
			//set session
			HttpSession session = request.getSession();
			session.setAttribute("user", accountnow);
			switch (accountnow.getTypeOfAccount()) {
			case 1:
				mv.setViewName("redirect:/admin");
				break;
			case 2:
				mv.setViewName("redirect:/student");		
				break;
			case 3:
				mv.setViewName("redirect:/teacher");
				break;
			default:
				mv.setViewName("redirect:/admin");
				break;
			}
			
		}else {
			mv.setViewName("dangnhap");
		}
		return mv;
	}
	
	
}
