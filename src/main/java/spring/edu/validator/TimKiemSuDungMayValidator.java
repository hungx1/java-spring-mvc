package spring.edu.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.edu.dto.TimKiemSuDungMay;

@Component
public class TimKiemSuDungMayValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("in TimKiemSuDungMayValidator");
		
		String regexIntege = "^[0-9]{1,10}$";
		if(!(target instanceof TimKiemSuDungMay)) {
			return;
		}
		TimKiemSuDungMay timKiemSuDungMay = (TimKiemSuDungMay) target;
		
		if("maKH".equals(timKiemSuDungMay.getTruongTimKiem()) || "maMay".equals(timKiemSuDungMay.getTruongTimKiem())) {
			System.out.println("Check maKH & maMay");
			if (timKiemSuDungMay.getTuKhoa() == null) {
				errors.rejectValue("tuKhoa", "error.TimKiemSuDungMay.tuKhoa", null, "Khong duoc de trong");
			}
		}
		

		if ("thoiGianSuDung".equals(timKiemSuDungMay.getTruongTimKiem())) {
			System.out.println("check thoiGianSuDung");
			if (timKiemSuDungMay.getThoiGianSuDung_start() == null) {
				errors.rejectValue("thoiGianSuDung_start", "error.TimKiemSuDungMay.thoiGianSuDung_start", null,
						"Không được để trống");
			}
			if (timKiemSuDungMay.getThoiGianSuDung_end() == null) {
				errors.rejectValue("thoiGianSuDung_start", "error.TimKiemSuDungMay.thoiGianSuDung_start", null,
						"Không được để trống");
			}
			if (timKiemSuDungMay.getThoiGianSuDung_start() != null
					&& timKiemSuDungMay.getThoiGianSuDung_end() != null) {

				if (timKiemSuDungMay.getThoiGianSuDung_start().matches(regexIntege) != true
						|| timKiemSuDungMay.getThoiGianSuDung_end().matches(regexIntege) != true) {
					errors.rejectValue("thoiGianSuDung_start", "error.TimKiemSuDungMay.thoiGianSuDung_start", null,
							"Phải nhập số từ 0 đến 9");
				}
				if (timKiemSuDungMay.getThoiGianSuDung_start().matches(regexIntege) == true
						&& timKiemSuDungMay.getThoiGianSuDung_end().matches(regexIntege) == true
						&& Integer.parseInt(timKiemSuDungMay.getThoiGianSuDung_start()) > Integer
								.parseInt(timKiemSuDungMay.getThoiGianSuDung_end())) {

					errors.rejectValue("thoiGianSuDung_start", "error.TimKiemSuDungMay.thoiGianSuDung_start", null,
							"Thời gian sử dụng Min phải nhỏ hơn thời sử dụng Max");
				}
			}
		}

		if ("gioBatDauSuDung".equals(timKiemSuDungMay.getTruongTimKiem())) {
			System.out.println("check gioBatDauSuDung");
			if (timKiemSuDungMay.getGioBatDauSuDung_start() == null
					|| timKiemSuDungMay.getGioBatDauSuDung_end() == null) {
				errors.rejectValue("gioBatDauSuDung_start", "error.TimKiemSuDungMay.gioBatDauSuDung_start", null,
						"Không được để trống");
			}
			if (timKiemSuDungMay.getGioBatDauSuDung_start() != null && timKiemSuDungMay.getGioBatDauSuDung_end() != null
					&& timKiemSuDungMay.getGioBatDauSuDung_start().after(timKiemSuDungMay.getGioBatDauSuDung_end())) {
				errors.rejectValue("gioBatDauSuDung_start", "error.TimKiemSuDungMay.gioBatDauSuDung_start", null,
						"Giờ bắt đầu sử dụng \"Từ lúc\" phải nhỏ hơn Giờ bắt đầu sử dụng \"Đến lúc\"");
			}
		}

		if ("ngayBatDauSuDung".equals(timKiemSuDungMay.getTruongTimKiem())) {
			System.out.println("check ngayBatDauSuDung");
			if (timKiemSuDungMay.getNgayBatDauSuDung_start() == null
					|| timKiemSuDungMay.getNgayBatDauSuDung_end() == null) {
				errors.rejectValue("ngayBatDauSuDung_start", "error.TimKiemSuDungMay.ngayBatDauSuDung_start", null,
						"Không được để trống");
			}
			if (timKiemSuDungMay.getNgayBatDauSuDung_start() != null
					&& timKiemSuDungMay.getNgayBatDauSuDung_end() != null
					&& timKiemSuDungMay.getNgayBatDauSuDung_start().after(timKiemSuDungMay.getNgayBatDauSuDung_end())) {
				errors.rejectValue("ngayBatDauSuDung_start", "error.TimKiemSuDungMay.ngayBatDauSuDung_start", null,
						"Ngày bắt đầu sử dụng \"Từ ngày\" phải nhỏ hơn Ngày bắt đầu sử dụng \"Đến ngày\"");
			}
		}
		
		
		
		
		
		
	}

}
