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
import qlhs.model.Teacher;
import qlhs.repository.AccountRepository;
import qlhs.repository.StudentRepository;
import qlhs.repository.TeacherRepository;
import qlhs.service.AuthenticationService;
import qlhs.service.StudentService;
import qlhs.service.TeacherService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AuthenticationService authen;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
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
		HttpSession session = request.getSession();
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
	
	@GetMapping("/tatcagiaovien")
	public ModelAndView tatcagiaovien(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			List<Teacher> teachers = teacherService.findAll();
			mv.addObject("teachers", teachers);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));  
			
			mv.setViewName("tatcagiaovien");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/themtaikhoan")
	public ModelAndView themtaikhoan(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			Account acc = new Account();
			mv.addObject("account", acc);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			mv.setViewName("themtaikhoan");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themtaikhoan")
	public ModelAndView themtaikhoan(@ModelAttribute Account account, HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			//Set new Account to Session
			HttpSession session = request.getSession();
			
			switch (account.getTypeOfAccount().toString()) {
			case "Teacher":
				session.setAttribute("newAccount", account);
				mv.setViewName("redirect:/admin/themgiaovien");
				break;
			case "Student":
				session.setAttribute("newAccount", account);
				mv.setViewName("redirect:/admin/themhocsinh");
				break;
			case "Admin":
				accountRepository.save(account);
				mv.setViewName("redirect:/login");
				break;
			default:
				mv.setViewName("redirect:/admin");
				break;
			}
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/themgiaovien")
	public ModelAndView themgiaovien(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			boolean newAccount=request.getSession().getAttribute("newAccount")!=null?true:false;
			if(newAccount) {
				Teacher teacher = new Teacher();
				mv.addObject("teacher", teacher);
				mv.addObject("user",(Account)request.getSession().getAttribute("user"));
				mv.setViewName("themgiaovien");
			}else {
				mv.setViewName("redirect:/admin/themtaikhoan");
			}
			
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themgiaovien")
	public ModelAndView themgiaovien(@ModelAttribute Teacher teacher, HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			Account newAccount = (Account) request.getSession().getAttribute("newAccount");
			teacher.setAccount(newAccount);
			accountRepository.save(newAccount);
			teacherRepository.save(teacher);
			HttpSession session = request.getSession();
			session.removeAttribute("newAccount");
			System.out.println("teacher saved -> Completed");
			mv.setViewName("redirect:/admin");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@GetMapping("/themhocsinh")
	public ModelAndView themhocsinh(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			boolean newAccount=request.getSession().getAttribute("newAccount")!=null?true:false;
			if(newAccount) {
				Student student = new Student();
				mv.addObject("student", student);
				mv.addObject("user",(Account)request.getSession().getAttribute("user"));
				mv.setViewName("themhocsinh");
			}else {
				mv.setViewName("redirect:/admin/themtaikhoan");
			}
			
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themhocsinh")
	public ModelAndView themhocsinh(@ModelAttribute Student student, HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			Account newAccount = (Account) request.getSession().getAttribute("newAccount");
			student.setAccount(newAccount);
			accountRepository.save(newAccount);
			studentRepository.save(student);
			HttpSession session = request.getSession();
			session.removeAttribute("newAccount");
			System.out.println("student saved -> Completed");
			mv.setViewName("redirect:/admin");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
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
