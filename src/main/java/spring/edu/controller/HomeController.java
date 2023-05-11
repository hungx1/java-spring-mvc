package spring.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.edu.dto.User;

@Controller
@RequestMapping("/trang-chu")
public class HomeController {
	
	@GetMapping
	public String showHome(Model model, HttpSession session, HttpServletRequest req ) {
		System.out.println("in HomeController");
		
		session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			model.addAttribute("user", user);
		}
		return "trang-chu/trang-chu";
	}
}
