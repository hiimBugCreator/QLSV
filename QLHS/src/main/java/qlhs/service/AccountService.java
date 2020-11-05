package qlhs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import qlhs.aop.SercurityAop;
import qlhs.model.Account;
import qlhs.repository.AccountProcudureRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountProcudureRepository accountProcudureRepository;
	
	
	
	public Account checkLogin(String taikhoan, String password) {
		boolean loginStatus = SercurityAop.LOGIN_STATUS;
		Account acc = SercurityAop.account;
		return acc;
	}

	public void changePass(String passnew, String id) {
		accountProcudureRepository.changePass(passnew, id);
	}
	

}
