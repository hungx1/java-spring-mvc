package spring.edu.service;

import spring.edu.models.Account;

public interface AccountService {
	Account getByAccountPassword(String account, String password);
	
}
