package qlhs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Account SearchByEmail(String email){
		List<Account> accounts = accountProcudureRepository.searchByEmail(email);
		return accounts.size() == 1 ? accounts.get(0) : null;
		
	}
}
