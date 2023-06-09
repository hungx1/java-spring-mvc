package spring.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	@GetMapping
	public String logout(HttpServletRequest req) {
		System.out.println("in LogoutController");
		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		return "redirect:/login";
	}
}
