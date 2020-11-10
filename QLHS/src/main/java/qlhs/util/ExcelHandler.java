package qlhs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import qlhs.model.Account;
import qlhs.model.Student;
import qlhs.model.Teacher;
import qlhs.model.TypeOfAccount;

public class ExcelHandler {
	private static ExcelHandler Instance = null;
	private XSSFWorkbook workbook;
	public ExcelHandler(InputStream is) throws IOException {
		this.workbook = new XSSFWorkbook(is);
	}
	
	public static ExcelHandler getInstance(InputStream is) throws IOException {
		if(Instance == null) {
			Instance = new ExcelHandler(is);
		}
		return Instance;
	}
	
	public List<Student> ExtractAllToStudent() throws IOException {
		List<Student> students = new ArrayList<Student>();
		XSSFSheet worksheet = workbook.getSheetAt(0);
	    DataFormatter formatter = new DataFormatter();
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	    	Account account = new Account();
	    	Student student = new Student();
	    	XSSFRow row = worksheet.getRow(i);
	    	//Tentaikhoan
//	    	System.out.println(formatter.formatCellValue(row.getCell(0)));
	    	account.setTentaikhoan(formatter.formatCellValue(row.getCell(0)));
	    	//matkhau
//	    	System.out.println(formatter.formatCellValue(row.getCell(1)));
	    	account.setMatkhau(formatter.formatCellValue(row.getCell(1)));
	    	//email
//	    	System.out.println(formatter.formatCellValue(row.getCell(2)));
	    	account.setEmail(formatter.formatCellValue(row.getCell(2)));
	    	//sdt
//	    	System.out.println(formatter.formatCellValue(row.getCell(3)));
	    	account.setSodienthoai(formatter.formatCellValue(row.getCell(3)));
	    	//typeofAccount
	    	account.setTypeOfAccount(TypeOfAccount.Student);
	    	//hovaten
//	    	System.out.println(formatter.formatCellValue(row.getCell(4)));
	    	student.setHoten(formatter.formatCellValue(row.getCell(4)));
	    	//mshs
//	    	System.out.println(formatter.formatCellValue(row.getCell(5)));
	    	student.setMshs(formatter.formatCellValue(row.getCell(5)));
	    	//tenphuhuynh
//	    	System.out.println(formatter.formatCellValue(row.getCell(6)));
	    	student.setTenphuhuynh(formatter.formatCellValue(row.getCell(6)));
	    	//gioitinh
//	    	System.out.println(formatter.formatCellValue(row.getCell(7)));
	    	student.setGioitinh(formatter.formatCellValue(row.getCell(7)));
	    	//ngaysinh
//	    	System.out.println(formatter.formatCellValue(row.getCell(8)));
	    	student.setNgaysinh(formatter.formatCellValue(row.getCell(8)));
	    	//noisinh
//	    	System.out.println(formatter.formatCellValue(row.getCell(9)));
	    	student.setNoisinh(formatter.formatCellValue(row.getCell(9)));
	    	//dantoc
//	    	System.out.println(formatter.formatCellValue(row.getCell(10)));
	    	student.setDantoc(formatter.formatCellValue(row.getCell(10)));
	    	//diachi
//	    	System.out.println(formatter.formatCellValue(row.getCell(11)));
	    	student.setDiachi(formatter.formatCellValue(row.getCell(11)));
	    	//Student <- Account
	    	student.setAccount(account);
	    	students.add(student);
	    }
	    workbook.close();
		return students;
	}
	
	public List<Teacher> ExtractAllToTeacher() throws IOException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		XSSFSheet worksheet = workbook.getSheetAt(0);
	    DataFormatter formatter = new DataFormatter();
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	    	Account account = new Account();
	    	Teacher teacher = new Teacher();
	    	XSSFRow row = worksheet.getRow(i);
	    	//Tentaikhoan
	    	account.setTentaikhoan(formatter.formatCellValue(row.getCell(0)));
	    	//matkhau
	    	account.setMatkhau(formatter.formatCellValue(row.getCell(1)));
	    	//email
	    	account.setEmail(formatter.formatCellValue(row.getCell(2)));
	    	//sdt
	    	account.setSodienthoai(formatter.formatCellValue(row.getCell(3)));
	    	//typeofAccount
	    	account.setTypeOfAccount(TypeOfAccount.Student);
	    	//hovaten
	    	teacher.setHoten(formatter.formatCellValue(row.getCell(4)));
	    	//mshs
	    	teacher.setMsgv(formatter.formatCellValue(row.getCell(5)));
	    	//gioitinh
	    	teacher.setGioitinh(formatter.formatCellValue(row.getCell(6)));
	    	//ngaysinh
	    	teacher.setNgaysinh(formatter.formatCellValue(row.getCell(7)));
	    	//noisinh
	    	teacher.setNoisinh(formatter.formatCellValue(row.getCell(8)));
	    	//dantoc
	    	teacher.setDantoc(formatter.formatCellValue(row.getCell(9)));
	    	//diachi
	    	teacher.setDiachi(formatter.formatCellValue(row.getCell(10)));
	    	//chucvu
	    	//Student <- Account
	    	teacher.setAccount(account);
	    	teachers.add(teacher);
	    }
	    workbook.close();
		return teachers;
	}
	
	public List<Account> ExtractStudentValueToAccount(List<Student> students){
		List<Account> accounts = new ArrayList<Account>();
		students.forEach(student -> accounts.add( ((Student) student).getAccount()) );
		return accounts;
	}
	
	public List<Account> ExtractTeacherValueToAccount(List<Teacher> teachers){
		List<Account> accounts = new ArrayList<Account>();
		teachers.forEach(teacher -> accounts.add( teacher.getAccount()) );
		return accounts;
	}
}
