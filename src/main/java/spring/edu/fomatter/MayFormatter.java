package spring.edu.fomatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import spring.edu.models.May;
import spring.edu.service.MayService;



@Component
//public class MayFormatter{
public class MayFormatter implements Formatter<May>{
	@Autowired
	MayService mayService;

	@Override
	public String print(May object, Locale locale) {
		// TODO Auto-generated method stub
		return object.toString();
	}

	@Override
	public May parse(String text, Locale locale) throws ParseException {
		System.out.println("MayFormatter for maMay: " + text);
		return mayService.getByMaMay(text);
	}
}
