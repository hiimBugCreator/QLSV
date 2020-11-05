package qlhs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import qlhs.model.Account;

public interface AccountProcudureRepository extends JpaRepository<Account,Integer>{


//	@Procedure(procedureName = "loginwithaccount")
//	public List<Account> loginWithAccount(@Param("username") String username, @Param("password") String password);
//	
	
	@Query(value = "{CALL loginwithaccount( ?,?) }",nativeQuery = true)
	List<Account> loginWithAccount(String username, String password);
	
	@Query(value = "{CALL changepassaccount( ?,?) }",nativeQuery = true)
	void changePass(String passnew, String id);
	
}
