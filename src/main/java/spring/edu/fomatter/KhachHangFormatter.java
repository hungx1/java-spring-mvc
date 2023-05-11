package spring.edu.fomatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import spring.edu.models.KhachHang;
import spring.edu.service.KhachHangService;

@Component
//public class KhachHangFormatter{
public class KhachHangFormatter implements Formatter<KhachHang>{
	@Autowired
	KhachHangService khachHangService;
	@Override
	public String print(KhachHang object, Locale locale) {
		// TODO Auto-generated method stub
		return object.toString();
	}

	@Override
	public KhachHang parse(String text, Locale locale) throws ParseException {
		System.out.println("KhachHangFormatter for maKH: " + text);
		return khachHangService.getByMaKH(text);
	}
}
