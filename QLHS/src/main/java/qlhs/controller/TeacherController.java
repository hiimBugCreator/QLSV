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
import qlhs.model.Teacher;
import qlhs.repository.TeacherRepository;
import qlhs.service.AuthenticationService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	AuthenticationService authen;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@GetMapping
	public ModelAndView index(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();

		System.out.println(status);
		if(status) {
			Account account = (Account)request.getSession().getAttribute("user");
			Teacher teacher = teacherRepository.findByAccountId(account.getId());
			mv.addObject("teacher", teacher);  
			
			mv.setViewName("giaovien");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/thoikhoabieu")
	public ModelAndView schedule(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("thoikhoabieu");
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
