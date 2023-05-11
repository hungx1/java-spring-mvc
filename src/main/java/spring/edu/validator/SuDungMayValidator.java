package spring.edu.validator;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.edu.models.KhachHang;
import spring.edu.models.May;
import spring.edu.models.SuDungMay;
import spring.edu.service.SuDungMayService;


@Component
public class SuDungMayValidator implements Validator{
	@Autowired
	SuDungMayService suDungMayService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("in SuDungMayValidator");
		if (!(target instanceof SuDungMay)) {
			return;
		}
		SuDungMay suDungMay = (SuDungMay) target;
		KhachHang khachHang = suDungMay.getKhachHang();
		Date ngayBatDauSuDung = suDungMay.getNgayBatDauSuDung();
		Date gioBatDauSuDung = suDungMay.getGioBatDauSuDung();
		May may = suDungMay.getMay();

		if (suDungMayService.findAllByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung(khachHang, may,
				ngayBatDauSuDung, gioBatDauSuDung) != null) {
			errors.rejectValue("khachHang", "error.suDungMay.khachHang", null,
					"Mã Khách hàng " + khachHang.getMaKH() + " đã được sử dụng");
			errors.rejectValue("may", "error.suDungMay.may", null, "Mã Máy " + may.getMaMay() + " đã được sử dụng");
			errors.rejectValue("ngayBatDauSuDung", "error.suDungMay.ngayBatDauSuDung", null,
					"Ngày bắt đầu sử dụng đã được sử dụng");
			errors.rejectValue("gioBatDauSuDung", "error.suDungMay.gioBatDauSuDung", null,
					"Giờ bắt đầu sử dụng đã được sử dụng");
		}

	}

	public void validateUpdate(Object target, Errors errors, SuDungMay suDungMayOld ) {
		if (!(target instanceof SuDungMay)) {
			return;
		}
		System.out.println("in SuDungMayValidator update");
		SuDungMay suDungMay = (SuDungMay) target;
		KhachHang khachHang = suDungMay.getKhachHang();
		Date ngayBatDauSuDung = suDungMay.getNgayBatDauSuDung();
		Date gioBatDauSuDung = suDungMay.getGioBatDauSuDung();
		May may = suDungMay.getMay();
//		Integer thoiGianSuDung = suDungMay.getThoigianSuDung();

		if (suDungMayService.findAllByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung(khachHang, may,
				ngayBatDauSuDung, gioBatDauSuDung) != null && !(suDungMay.equals(suDungMayOld))
				
				) {
			errors.rejectValue("khachHang", "error.suDungMay.khachHang", null,
					"Mã Khách hàng " + khachHang.getMaKH() + " đã được sử dụng");
			errors.rejectValue("may", "error.suDungMay.may", null, "Mã Máy " + may.getMaMay() + " đã được sử dụng");
			errors.rejectValue("ngayBatDauSuDung", "error.suDungMay.ngayBatDauSuDung", null,
					"Ngày bắt đầu sử dụng đã được sử dụng");
			errors.rejectValue("gioBatDauSuDung", "error.suDungMay.gioBatDauSuDung", null,
					"Giờ bắt đầu sử dụng đã được sử dụng");
		}

	}
}
