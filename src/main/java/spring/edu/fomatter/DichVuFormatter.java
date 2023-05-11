package spring.edu.fomatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import spring.edu.models.DichVu;
import spring.edu.service.DichVuService;

@Component
//public class DichVuFormatter{
public class DichVuFormatter implements Formatter<DichVu> {
	@Autowired
	DichVuService dichVuService;
	
	@Override
	public String print(DichVu object, Locale locale) {
		return null;
	}
	
	@Override
	public DichVu parse (String text, Locale locale) throws ParseException {
		System.out.println("MayFormatter for maDV: " + text);
		return dichVuService.getByMaDV(text);
	}
}
