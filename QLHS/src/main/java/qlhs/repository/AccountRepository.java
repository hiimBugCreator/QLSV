package qlhs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import qlhs.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{	
}
