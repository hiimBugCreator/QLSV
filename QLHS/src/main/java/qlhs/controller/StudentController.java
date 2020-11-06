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
import qlhs.model.Student;
import qlhs.repository.StudentRepository;
import qlhs.service.AuthenticationService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	AuthenticationService authen;
	
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping
	public ModelAndView index(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			Account account = (Account)request.getSession().getAttribute("user");
			Student student = studentRepository.findByAccountId(account.getId());
			mv.addObject("student",student);  
			
			mv.setViewName("hocsinh");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/thoikhoabieu")
	public ModelAndView schedule(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		System.out.println(status);
		if(status) {
			Account account = (Account)request.getSession().getAttribute("user");
			Student student = studentRepository.findByAccountId(account.getId());
			mv.addObject("student",student);  
			
			mv.setViewName("tkbhs");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/bangdiem")
	public ModelAndView grade(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		System.out.println(status);
		if(status) {
			Account account = (Account)request.getSession().getAttribute("user");
			Student student = studentRepository.findByAccountId(account.getId());
			mv.addObject("student",student);  
			
			mv.setViewName("bangdiem");
		}else {
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
//	@GetMapping("/configaccount")
//	public ModelAndView configaccount(HttpServletRequest request) {
//		boolean status=request.getSession().getAttribute("user")!=null?true:false;
//		ModelAndView mv = new ModelAndView();
//		System.out.println(status);
//		if(status) {
//			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
//			mv.setViewName("thongtinbaomat");
//		} else {
//			mv.setViewName("redirect:/login");
//		}
//		return mv;
//	}
	
}
