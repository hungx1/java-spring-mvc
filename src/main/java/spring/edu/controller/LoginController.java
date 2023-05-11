package spring.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.edu.dto.User;
import spring.edu.models.Account;
import spring.edu.service.AccountService;

@Controller
@SessionAttributes("user")
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AccountService accountService;
	@Autowired
	User user;
	
	@GetMapping
	public String login() {
		System.out.println("in LoginController GET");
		return "login/login";
	}
	
	@PostMapping
	public String getLogin(@RequestParam("account") String account, 
			@RequestParam("password") String password,
			Model model, RedirectAttributes redirectAttributes) {
		Account a = accountService.getByAccountPassword(account, password);
		if (a != null) {
			user.setUserName(account);
			model.addAttribute("user", user);
			return "redirect:/trang-chu";
		}
		return "redirect:/login";
	}
}
