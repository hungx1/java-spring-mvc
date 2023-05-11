package spring.edu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.edu.models.DichVu;
import spring.edu.models.KhachHang;
import spring.edu.models.SuDungDichVu;
import spring.edu.service.DichVuService;
import spring.edu.service.KhachHangService;
import spring.edu.service.SuDungDichVuService;
import spring.edu.validator.SuDungDichVuValidator;



@Controller
@RequestMapping("/su-dung-dich-vu")
public class SuDungDichVuController {
	@Autowired
	SuDungDichVuService suDungDichVuService;
	@Autowired
	DichVuService dichVuService;
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	SuDungDichVuValidator suDungDichVuValidator;

	@PostMapping("/them-su-dung-dich-vu")
	public String themSuDungDichVuPOST(@ModelAttribute("them_su_dung_dich_vu") @Valid SuDungDichVu suDungDichVu,
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("in SuDungDichVuController themSuDungDichVuPOST");
		
		suDungDichVuValidator.validate(suDungDichVu, result);
		if (result.hasErrors()) {
			List<KhachHang> danhSachKH = khachHangService.findAll();
			List<DichVu> danhSachDV = dichVuService.findAll();
			model.addAttribute("danhSachDV", danhSachDV);
			model.addAttribute("danhSachKH", danhSachKH);
			return "suDungDichVu/them-su-dung-dich-vu";
		}
		if (suDungDichVuService.save(suDungDichVu)) {
			redirectAttributes.addFlashAttribute("thongBao",
					"Thêm thành công Sử Dụng dịch vụ cho KH " + suDungDichVu.getKhachHang().getTenKH());
			return "redirect:/su-dung-dich-vu/them-su-dung-dich-vu";
		} else {
			redirectAttributes.addFlashAttribute("thongBao",
					"Thêm thất bại Sử Dụng dịch vụ cho KH " + suDungDichVu.getKhachHang().getTenKH());
			return "redirect:/su-dung-dich-vu/them-su-dung-dich-vu";
		}

	}

	@GetMapping("/them-su-dung-dich-vu")
	public String themSuDungDichVuGET(Model model) {
		System.out.println("in SuDungDichVuController themSuDungDichVuGET");

		if (!model.containsAttribute("them_su_dung_dich_vu")) {
			model.addAttribute("them_su_dung_dich_vu", new SuDungDichVu());
		}
		List<KhachHang> danhSachKH = khachHangService.findAll();
		List<DichVu> danhSachDV = dichVuService.findAll();
		model.addAttribute("danhSachDV", danhSachDV);
		model.addAttribute("danhSachKH", danhSachKH);
		return "suDungDichVu/them-su-dung-dich-vu";
	}
	
	
}
