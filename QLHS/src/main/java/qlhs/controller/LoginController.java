package qlhs.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import qlhs.model.Account;
import qlhs.model.Teacher;
import qlhs.repository.AccountProcudureRepository;
import qlhs.repository.AccountRepository;
import qlhs.repository.TeacherRepository;
import qlhs.service.AccountService;
import qlhs.service.AuthenticationService;
import qlhs.service.EmailService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	AuthenticationService authen;
	@Autowired
	AccountRepository acccount;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TeacherRepository teacherRepository;

//	private JavaMailSender javaMailSender;
	
	@GetMapping
	public ModelAndView index() {
		String type = null;
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
			switch (accountnow.getTypeOfAccount().toString()) {
			case "Admin":
				mv.setViewName("redirect:/admin");
				break;
			case "Student":
				mv.setViewName("redirect:/student");		
				break;
			case "Teacher":
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
	
	@PostMapping("/verify")
	public ModelAndView verifyEmail(@RequestParam("email") String email,
								HttpServletRequest request){
		System.out.println(email);
		ModelAndView mv = new ModelAndView();
		Account acc = accountRepository.findByEmail(email);
		String token = emailService.getAlphaNumericString(8);
		
		if(acc != null) {
			//Set token to Session if Exist
			HttpSession session = request.getSession();
			System.out.println(token);
			session.setAttribute("token", token);
			session.setAttribute("AccountProcess", acc);
			mv.setViewName("nhapmaxacthuc");
		}else {
			mv.setViewName("redirect:/login");
		}
//		emailService.sendMail(email, "Code to Confirm Password", "AAA-BBB-999");
		return mv;
	}
	
	@PostMapping("/verifyCode")
	public ModelAndView confirmVerifyCode(@RequestParam("token") String UserToken,
								HttpServletRequest request){
		HttpSession session = request.getSession();
		String SessionToken = session.getAttribute("token").toString();
		ModelAndView mv = new ModelAndView();
		if(UserToken.equalsIgnoreCase(SessionToken)) {
			String NoSession = "NoSession";
			session.removeAttribute("token");
			mv.addObject("NoSession", NoSession);
			mv.setViewName("quenmatkhau");
		}else {
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
	@PostMapping("/verifyPassword")
	public ModelAndView verifyPassword(HttpServletRequest request){
		HttpSession session = request.getSession();
		boolean status = session.getAttribute("AccountProcess") != null ? true : false;
		ModelAndView mv = new ModelAndView();
		if(status) {
			Account acc = (Account) session.getAttribute("AccountProcess");
			acc.setMatkhau(request.getParameter("password"));
			accountRepository.save(acc);
			session.removeAttribute("AccountProcess");
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
	
}
