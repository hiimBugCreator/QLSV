package qlhs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qlhs.model.Account;
import qlhs.model.Student;
import qlhs.repository.StudentRepository;
import qlhs.service.AuthenticationService;
import qlhs.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AuthenticationService authen;
	
	@Autowired
	StudentService studentService;
	
	@GetMapping
	public ModelAndView index(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));  
			
			mv.setViewName("quanli");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/tatcahocsinh")
	public ModelAndView tatcahocsinh(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			List<Student> students = studentService.findAll();
			mv.addObject("students", students);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));  
			
			mv.setViewName("tatcahocsinh");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/xemthongtinhocsinh/{id}")
	public ModelAndView xemthongtinhocsinh(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int studentid = Integer.parseInt(id);
		Student student = studentService.findById(studentid);
		System.out.println(student.getHoten());
		System.out.println(student.getMshs());
		System.out.println(student.getTenphuhuynh());
		return null;
	}
	
	@GetMapping("/configaccount")
	public ModelAndView configaccount(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			mv.setViewName("thongtinbaomat");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
}
