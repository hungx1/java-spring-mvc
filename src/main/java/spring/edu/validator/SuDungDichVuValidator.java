package spring.edu.validator;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.edu.models.DichVu;
import spring.edu.models.KhachHang;
import spring.edu.models.SuDungDichVu;
import spring.edu.service.SuDungDichVuService;


@Component
public class SuDungDichVuValidator implements Validator{
	@Autowired
	SuDungDichVuService suDungDichVuService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("in SuDungDichVuValidator");
		if (!(target instanceof SuDungDichVu)) {
			return;
		}
		SuDungDichVu suDungDichVu = (SuDungDichVu) target;
		KhachHang khachHang = suDungDichVu.getKhachHang();
		LocalDate ngaySuDung = suDungDichVu.getNgaySuDung();
		LocalTime gioSuDung = suDungDichVu.getGioSuDung();
		DichVu dichVu = suDungDichVu.getDichVu();

		if (suDungDichVuService.findByKhachHangAndDichVuAndNgaySuDungAndGioSuDung(khachHang, dichVu, ngaySuDung,
				gioSuDung) != null) {
			errors.rejectValue("khachHang", "error.suDungDichVu.khachHang", null,
					"Mã Khách hàng " + khachHang.getMaKH() + " đã được sử dụng");
			errors.rejectValue("dichVu", "error.suDungDichVu.may", null,
					"Mã Dịch Vụ  " + dichVu.getMaDV() + " đã được sử dụng");
			errors.rejectValue("ngaySuDung", "error.suDungDichVu.ngaySuDung", null, "Ngày sử dụng đã được dùng");
			errors.rejectValue("gioSuDung", "error.suDungDichVu.gioSuDung", null, "Giờ sử dụng đã được dùng");
		}

	}
	
}
