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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import qlhs.model.Account;
import qlhs.model.Chucvu;
import qlhs.model.Ngaychucvu;
import qlhs.model.Student;
import qlhs.model.Teacher;
import qlhs.repository.AccountRepository;
import qlhs.repository.ChucvuRepository;
import qlhs.repository.NgaychucvuRepository;
import qlhs.repository.StudentRepository;
import qlhs.repository.TeacherRepository;
import qlhs.service.AccountService;
import qlhs.service.AuthenticationService;
import qlhs.service.ChucvuService;
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
	
	@Autowired
	private ChucvuRepository chucvuRepository;
	
	@Autowired
	private ChucvuService chucvuService;
	
	@Autowired
	private NgaychucvuRepository ngaychucvuRepository;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ModelAndView index(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
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
	
	@GetMapping("/tatcataikhoan")
	public ModelAndView tatcataikhoan(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			List<Account>accounts = accountService.findAll();
			mv.addObject("accounts", accounts);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));  
			
			mv.setViewName("tatcataikhoan");
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
	
	@GetMapping("/lockAccount/{id}")
	public ModelAndView lockAccount(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int accountId = Integer.parseInt(id);
		Account account = accountService.findById(accountId);
		account.setTrangthai("0");
		System.out.println(account.getTrangthai());
		accountRepository.save(account);
		mv.setViewName("redirect:/admin/tatcataikhoan");
		return mv;
	}
	
	@GetMapping("/unlockAccount/{id}")
	public ModelAndView unlockAccount(@PathVariable String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int accountId = Integer.parseInt(id);
		Account account = accountService.findById(accountId);
		account.setTrangthai("1");
		System.out.println(account.getTrangthai());
		accountRepository.save(account);
		mv.setViewName("redirect:/admin/tatcataikhoan");
		return mv;
	}
	
	@GetMapping("/tatcagiaovien")
	public ModelAndView tatcagiaovien(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
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
	// Them Tai Khoan
	@GetMapping("/themtaikhoan")
	public ModelAndView themtaikhoan(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			Account account = new Account();
			mv.addObject("account", account);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			mv.setViewName("themtaikhoan");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themtaikhoan")
	public ModelAndView themtaikhoan(@ModelAttribute Account account,HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			System.out.println(account.getTypeOfAccount());
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			//Set new Account to Session
			session.setAttribute("newAccount", account);
			
			switch (account.getTypeOfAccount()) {
			case "GiaoVien":
				mv.setViewName("redirect:/admin/themtaikhoan/themgiaovien");
				break;
			case "HocSinh":
				mv.setViewName("redirect:/admin/themtaikhoan/themhocsinh");
				break;
			case "Admin":
				accountRepository.save(account);
				mv.setViewName("redirect:/admin");
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
	
	@GetMapping("/themtaikhoan/themgiaovien")
	public ModelAndView themgiaovien(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		Account newAccount = (Account) session.getAttribute("newAccount");
		
		
		if(status) {
			List<Chucvu> chucvus = chucvuService.findAll();
			Teacher teacher = new Teacher();
			mv.addObject("chucvus", chucvus);
			mv.addObject("teacher", teacher);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));  
			mv.addObject("typeofaccount", newAccount.getTypeOfAccount().toString());
			mv.setViewName("themtaikhoan");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themtaikhoan/themgiaovien")
	public ModelAndView themgiaovien(@ModelAttribute Teacher teacher, @RequestParam("chucvuid") int chucvuid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		
		if(status) {
			Chucvu chucvu = chucvuService.findById(chucvuid);
			Account account = (Account) session.getAttribute("newAccount");
			teacher.setAccount(account);
			Ngaychucvu ngaychucvu = new Ngaychucvu();
			ngaychucvu.setChucvu(chucvu);
			ngaychucvu.setTeacher(teacher);
			ngaychucvu.setNgaynhamchuc();
			accountRepository.save(account);
			teacherRepository.save(teacher);
			ngaychucvuRepository.save(ngaychucvu);
			session.removeAttribute("newAccount");
			mv.setViewName("redirect:/admin");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	
	@GetMapping("/themtaikhoan/themhocsinh")
	public ModelAndView themhocsinh(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		Account newAccount = (Account) session.getAttribute("newAccount");
		
		if(status) {
			Student student = new Student();
			mv.addObject("student", student);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));  
			mv.addObject("typeofaccount", newAccount.getTypeOfAccount().toString());
			mv.setViewName("themtaikhoan");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themtaikhoan/themhocsinh")
	public ModelAndView themhocsinh(@ModelAttribute Student student, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		HttpSession session = request.getSession();
		
		if(status) {
			Account account = (Account) session.getAttribute("newAccount");
			student.setAccount(account);
			studentRepository.save(student);
			session.removeAttribute("newAccount");
			mv.setViewName("redirect:/admin");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	// Them Chuc Vu
	@GetMapping("/themchucvu")
	public ModelAndView themchucvu(HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			List<Chucvu> chucvus = chucvuService.findAll();
			Chucvu chucvu = new Chucvu();
			mv.addObject("chucvus", chucvus);
			mv.addObject("chucvu", chucvu);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
			mv.setViewName("themchucvu");
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	@PostMapping("/themchucvu")
	public ModelAndView themchucvu(@ModelAttribute Chucvu chucvu, HttpServletRequest request) {
		boolean status=request.getSession().getAttribute("user")!=null?true:false;
		ModelAndView mv = new ModelAndView();
		System.out.println(status);
		if(status) {
			chucvuRepository.save(chucvu);
			mv.addObject("user",(Account)request.getSession().getAttribute("user"));
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
