package spring.edu.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, RedirectAttributes redirectAttributes) {
		System.out.println("in ExceptionControllerAdvice");
		
		String msg = e.getMessage() + ".Class" + e.getClass();
		
		System.out.println("Exception message" + msg);
		redirectAttributes.addFlashAttribute("ExceptionMsg", msg);
		
		return "redirect:/errors";
	}
}
