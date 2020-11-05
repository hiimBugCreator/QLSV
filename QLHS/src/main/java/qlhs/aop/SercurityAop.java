package qlhs.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qlhs.model.Account;
import qlhs.repository.AccountProcudureRepository;
import qlhs.repository.AccountRepository;
import java.util.*;

@Aspect
@Component
public class SercurityAop {
	
	public static boolean LOGIN_STATUS = false;
	public static Account account = null;
	
	@Autowired
	AccountProcudureRepository accountProcudureRepository;
	
//	*advice*
	@Before("execution(* qlhs.service.*.*(..) )")
	public void checkEmailPassword(JoinPoint joinPoint) throws Throwable {
		System.out.println("Method: "+joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		if(args.length>1)
		{
			String taikhoan = args[0].toString();
			String password = args[1].toString();
			  
			System.out.println(taikhoan + password);
			//accountProcudureRepository.insertall();
			List<Account> account =	accountProcudureRepository.loginWithAccount(taikhoan,password);
			System.out.println("size: "+account.size());
			if(account.size()!=0) {
				LOGIN_STATUS=true;
				SercurityAop.account = account.get(0);
			} else {
				LOGIN_STATUS=false;
				SercurityAop.account = null;
			}
		}
		System.out.println("END AOP");
	}
}
