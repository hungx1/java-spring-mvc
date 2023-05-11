package spring.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.edu.models.Account;
import spring.edu.repository.AccountDAObasic;
import spring.edu.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAObasic accountDAObasic;

	@Override
	public Account getByAccountPassword(String account, String password) {
		return accountDAObasic.findByAccountAndPassword(account, password);
	}
	
	
}
