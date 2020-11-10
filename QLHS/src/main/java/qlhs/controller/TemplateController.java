package qlhs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import qlhs.model.Account;
import qlhs.model.Student;
import qlhs.model.Teacher;
import qlhs.repository.AccountRepository;
import qlhs.repository.StudentRepository;
import qlhs.repository.TeacherRepository;
import qlhs.service.AuthenticationService;
import qlhs.util.ExcelHandler;

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
		return mv;
	}
	
	@PostMapping("/template")
	public ModelAndView handleAddAccount(@RequestParam("file") MultipartFile reapExcelDataFile, HttpServletRequest request) throws IOException{
		ModelAndView mv = new ModelAndView();
	    ExcelHandler excelHandler = ExcelHandler.getInstance(reapExcelDataFile.getInputStream());
	    List<Student> students = excelHandler.ExtractAllToStudent();
	    List<Account> accounts = excelHandler.ExtractStudentValueToAccount(students);
	    for(Student student : students) {
	    	System.out.println(student.getHoten());
	    }
	    for(Account account : accounts) {
	    	System.out.println(account.getTentaikhoan());
	    }
		return null;
	}
	
	
}
