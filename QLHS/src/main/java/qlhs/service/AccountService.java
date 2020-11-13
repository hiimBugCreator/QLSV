package qlhs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import qlhs.aop.SercurityAop;
import qlhs.model.Account;
import qlhs.repository.AccountProcudureRepository;
import qlhs.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountProcudureRepository accountProcudureRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public Account checkLogin(String taikhoan, String password) {
		boolean loginStatus = SercurityAop.LOGIN_STATUS;
		Account acc = SercurityAop.account;
		return acc;
	}

	public void changePass(String passnew, String id) {
		accountProcudureRepository.changePass(passnew, id);
	}
	
	public Account SearchByEmail(String email){
		List<Account> accounts = accountProcudureRepository.searchByEmail(email);
		return accounts.size() == 1 ? accounts.get(0) : null;
		
	}
	
	public List<Account> findAll(){
		Iterable<Account> it = accountRepository.findAll();
		List<Account> accounts = new ArrayList<Account>();
		it.forEach(account -> accounts.add(account));
		return accounts;
	}
	
	public Account findById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		return account.get();
	}

}
