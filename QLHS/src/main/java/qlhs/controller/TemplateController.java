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
import qlhs.model.Student;
import qlhs.model.Teacher;
import qlhs.repository.AccountRepository;
import qlhs.repository.StudentRepository;
import qlhs.repository.TeacherRepository;
import qlhs.service.AuthenticationService;

@Controller
public class TemplateController {
	@Autowired
	AuthenticationService authen;
	
	private Account accountTemp;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/template")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("template");
		Account acc = new Account();
		System.out.println(">>>The Index");
		System.out.println(acc.getTentaikhoan());
		mv.addObject("account", acc);
		return mv;
	}
	
	@PostMapping("/template")
	public ModelAndView handleAddAccount(@ModelAttribute Account account, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		System.out.println(">>>Post method The handleAddAccount");
		accountTemp = account;
		switch (account.getTypeOfAccount().toString()) {
		case "Teacher":
			mv.setViewName("redirect:/template/Teacher-form");
			break;
		case "Student":
			mv.setViewName("redirect:/template/Student-form");
			break;
		case "Admin":
			accountRepository.save(accountTemp);
			accountTemp = null;
			mv.setViewName("redirect:/login");
			break;
			
		default:
			break;
		}

		return mv;
	}
	
	@GetMapping("/template/Teacher-form")
	public ModelAndView handleAddTeacher(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Teacher teacher = new Teacher();
		mv.addObject("teacher", teacher);
		mv.addObject("typeofaccount", accountTemp.getTypeOfAccount().toString());
		mv.setViewName("template");
		return mv;
	}
	
	@PostMapping("/template/Teacher-form")
	public ModelAndView handleAddTeacher(@ModelAttribute Teacher teacher, HttpServletRequest request) {
		System.out.println(">>>Post method The handleAddTeacher");
		ModelAndView mv = new ModelAndView();
		Account acc = accountTemp;
		accountTemp = null;
		System.out.println(">>>Account<<<");
		System.out.println(acc.getTentaikhoan());
		System.out.println(acc.getEmail());
		System.out.println(acc.getMatkhau());
		System.out.println(acc.getSodienthoai());
		System.out.println(acc.getTypeOfAccount());
		teacher.setAccount(acc);
		System.out.println("teacher set account -> Completed");
		accountRepository.save(acc);
		System.out.println("account saved -> Completed");
		System.out.println(">>>Teacher<<<");
		System.out.println(teacher.getMsgv());
		System.out.println(teacher.getDantoc());
		System.out.println(teacher.getGioitinh());
		System.out.println(teacher.getNgaysinh());
		System.out.println(teacher.getDiachi());
		System.out.println(teacher.getHoten());
		teacherRepository.save(teacher);
		System.out.println("teacher saved -> Completed");
		mv.setViewName("redirect:/login");
		return mv;
	}
	
	@GetMapping("/template/Student-form")
	public ModelAndView handleAddStudent(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Student student = new Student();
		mv.addObject("student", student);
		mv.addObject("typeofaccount", accountTemp.getTypeOfAccount().toString());
		mv.setViewName("template");
		return mv;
	}
	
	@PostMapping("/template/Student-form")
	public ModelAndView handleAddStudent(@ModelAttribute Student student, HttpServletRequest request) {
		System.out.println(">>>Post method The handleAddStudent");
		ModelAndView mv = new ModelAndView();
		Account acc = accountTemp;
		accountTemp = null;
		System.out.println(">>>Account<<<");
		System.out.println(acc.getTentaikhoan());
		System.out.println(acc.getEmail());
		System.out.println(acc.getMatkhau());
		System.out.println(acc.getSodienthoai());
		System.out.println(acc.getTypeOfAccount());
		student.setAccount(acc);
		System.out.println("teacher set account -> Completed");
		accountRepository.save(acc);
		System.out.println("account saved -> Completed");
		System.out.println(">>>Teacher<<<");
		System.out.println(student.getMshs());
		System.out.println(student.getDantoc());
		System.out.println(student.getGioitinh());
		System.out.println(student.getNgaysinh());
		System.out.println(student.getDiachi());
		System.out.println(student.getHoten());
		studentRepository.save(student);
		System.out.println("teacher saved -> Completed");
		System.out.println(student.getHoten());
		System.out.println(acc.getTentaikhoan());
		mv.setViewName("redirect:/login");
		return mv;
	}
}
