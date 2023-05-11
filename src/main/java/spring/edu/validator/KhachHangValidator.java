package spring.edu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.edu.models.KhachHang;
import spring.edu.service.KhachHangService;


@Component
public class KhachHangValidator implements Validator{
	@Autowired
	KhachHangService service;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("in KhachHangValidator");
		String regexSoDienThoai = "^((090)|(091)|(\\(84\\)\\+91)|(\\(84\\)\\+90))[0-9]{7}$";
		String regexDiaChiEmail = "^[\\w][\\w\\d-_]+@[\\w]{2,}(\\.[\\w]{2,})+$";
		if (!(target instanceof KhachHang)) {
			return;
		}
		KhachHang khachHang = (KhachHang) target;
		String maKH = khachHang.getMaKH();
		String soDienThoai = khachHang.getSoDienThoai();
		String diaChiEmail = khachHang.getDiaChiEmail();
		if (service.getByMaKH(maKH) != null) {
			errors.rejectValue("maKH", "error.maKH", null, "Mã Khách hàng " + maKH + " đã tồn tại");
		}
		if (soDienThoai != null && soDienThoai.matches(regexSoDienThoai) == false) {
			errors.rejectValue("soDienThoai", "error.soDienThoai", new String[] { soDienThoai },
					"Số điện thoại không đúng định dạng 090xxxxxxx hoặc 091xxxxxxx hoặc (84)+90xxxxxxx hoặc (84)+91xxxxxxx");
		}
		if (diaChiEmail != null && diaChiEmail.matches(regexDiaChiEmail) == false) {
			errors.rejectValue("diaChiEmail", "error.diaChiEmail", new String[] { diaChiEmail },
					"Số điện thoại không đúng định dạng xxx@xxx.xxx");
		}
	}

	public void validateUpdate(Object target, Errors errors, String maKH_Old) {
		System.out.println("in KhachHangValidator-Update");
		String regexSoDienThoai = "^((090)|(091)|(\\(84\\)\\+91)|(\\(84\\)\\+90))[0-9]{7}$";
		String regexDiaChiEmail = "^[\\w][\\w\\d-_]+@[\\w]{2,}(\\.[\\w]{2,})+$";
		if (!(target instanceof KhachHang)) {
			return;
		}
		KhachHang khachHang = (KhachHang) target;
		String maKH = khachHang.getMaKH();
		String soDienThoai = khachHang.getSoDienThoai();
		String diaChiEmail = khachHang.getDiaChiEmail();
		if (service.getByMaKH(maKH) != null && !maKH_Old.equals(maKH)) {
			errors.rejectValue("maKH", "error.maKH", null, "Mã Khách hàng " + maKH + " đã tồn tại");
		}
		if (soDienThoai != null && soDienThoai.matches(regexSoDienThoai) == false) {
			errors.rejectValue("soDienThoai", "error.soDienThoai", new String[] { soDienThoai },
					"Số điện thoại không đúng định dạng 090xxxxxxx hoặc 091xxxxxxx hoặc (84)+90xxxxxxx hoặc (84)+91xxxxxxx");
		}
		if (diaChiEmail != null && diaChiEmail.matches(regexDiaChiEmail) == false) {
			errors.rejectValue("diaChiEmail", "error.diaChiEmail", new String[] { diaChiEmail },
					"Số điện thoại không đúng định dạng xxx@xxx.xxx");
		}
	}
	
	
}
