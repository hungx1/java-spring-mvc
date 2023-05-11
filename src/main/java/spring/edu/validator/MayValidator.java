package spring.edu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import spring.edu.models.May;
import spring.edu.service.MayService;


@Component
public class MayValidator implements Validator{
	@Autowired
	MayService service;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("in MayValidator");
		if (!(target instanceof May)) {
			return;
		}
		May dichVu = (May) target;
		String maMay = dichVu.getMaMay();
		if (service.getByMaMay(maMay) != null) {
			errors.rejectValue("maMay", "error.maMay", null, "Mã Máy " + maMay + " đã tồn tại");
		}
	}

	public void validateUpdate(Object target, Errors errors, String maMay_Old) {
		if (!(target instanceof May)) {
			return;
		}
		May dichVu = (May) target;
		String maMay = dichVu.getMaMay();
		if (service.getByMaMay(maMay) != null && !maMay_Old.equals(maMay)) {
			errors.rejectValue("maMay", "error.maMay", null, "Mã Máy " + maMay + " đã tồn tại");
		}
	}
	
	
}
