package spring.edu.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.edu.models.Account;

@Repository
@Transactional
public interface AccountDAObasic extends JpaRepository<Account, Integer>{
	Account findByAccountAndPassword(String account, String password);
}
