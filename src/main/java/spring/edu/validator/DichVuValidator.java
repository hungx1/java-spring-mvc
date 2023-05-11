package spring.edu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.edu.models.DichVu;
import spring.edu.service.DichVuService;


@Component
public class DichVuValidator implements Validator{
	@Autowired
	DichVuService service;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("in DichVuValidator");
		if (!(target instanceof DichVu)) {
			return;
		}
		DichVu dichVu = (DichVu) target;
		String maDV = dichVu.getMaDV();
		if (service.getByMaDV(maDV) != null) {
			errors.rejectValue("maDV", "error.maDV", null, "Mã dịch vụ " + maDV + " đã tồn tại");
		}
	}

	public void validateUpdate(Object target, Errors errors, String maDV_Old) {
		System.out.println("in DichVuValidator-Update");
		if (!(target instanceof DichVu)) {
			return;
		}
		DichVu dichVu = (DichVu) target;
		String maDV = dichVu.getMaDV();
		if (maDV.equals(maDV_Old)) {
			return;
		}
		if (service.getByMaDV(maDV) != null) {
			errors.rejectValue("maDV", "error.maDV_update", null, "Mã dịch vụ " + maDV + " đã tồn tại");
		}
	}
}
