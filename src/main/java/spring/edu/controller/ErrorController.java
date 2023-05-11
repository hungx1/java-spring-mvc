package spring.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	private static final String ERROR_PAGE = "error-page";
	
	@RequestMapping(value = "/errors", method = RequestMethod.GET )
	public String renderErrorPage(HttpServletRequest httpRequest, Model model) {
		System.out.println("in ErrorController Method.GET");
		String errorCode = "";
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);
		System.out.println("error code: " + httpErrorCode);
		
		switch (httpErrorCode) {
		case 400:
			errorCode = "400";
			errorMsg = "Bad request.";
			break;
		case 401:
			errorCode = "401";
			errorMsg = "Unauthorized.";
			break;

		case 403:
			errorCode = "403";
			errorMsg = "Access is denied.";
			break;

		case 404:
			errorCode = "404";
			errorMsg = "Page not found.";
			break;

		case 500:
			errorCode = "500";
			errorMsg = "Internal Server Error.";
			break;

		default:
			errorCode = ":(";
			errorMsg = "Oops! Something went wrong.";
			break;
		}
		model.addAttribute("errorMsg", errorMsg);
		model.addAttribute("errorCode", errorCode);
		String ExceptionMsg = (String) model.asMap().get("ExceptionMsg");
		if (ExceptionMsg != null) {
			model.addAttribute("ExceptionMsg", ExceptionMsg);
		}
		
		return ERROR_PAGE;
	}
	
	private int getErrorCode (HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
	
	@RequestMapping(value = "/errors", method = RequestMethod.POST)
	public String ErrorPage(HttpServletRequest httpRequest) {
		System.out.println("in ErrorController Method.POST");
		return ERROR_PAGE;
	}
}
