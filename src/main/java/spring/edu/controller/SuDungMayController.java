package spring.edu.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.edu.dto.TimKiemSuDungMay;
import spring.edu.models.KhachHang;
import spring.edu.models.May;
import spring.edu.models.SuDungMay;
import spring.edu.service.KhachHangService;
import spring.edu.service.MayService;
import spring.edu.service.SuDungMayService;
import spring.edu.validator.SuDungMayValidator;
import spring.edu.validator.TimKiemSuDungMayValidator;

@Controller
@RequestMapping("/su-dung-may")
public class SuDungMayController {
	@Autowired
	SuDungMayService suDungMayService;
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	MayService mayService;
	@Autowired
	SuDungMayValidator suDungMayValidator;
	@Autowired
	TimKiemSuDungMayValidator timKiemSuDungMayValidator;
	private Integer size = 5;
	
	@GetMapping("/them-su-dung-may")
	public String themSuDungMayGET(Model model) {
		System.out.println("in SuDungMayController themSuDungMayGET");
		if (!model.containsAttribute("them_su_dung_may")) {
			model.addAttribute("them_su_dung_may", new SuDungMay());
		}
		List<KhachHang> danhSachKH = khachHangService.findAll();
		List<May> danhSachMay = mayService.findAll();
		model.addAttribute("danhSachMay", danhSachMay);
		model.addAttribute("danhSachKH", danhSachKH);
		return "suDungMay/them-su-dung-may";
	}

	@PostMapping("/them-su-dung-may")
	public String themSuDungMayPOST(@ModelAttribute("them_su_dung_may") @Valid SuDungMay suDungMay,
			BindingResult result, RedirectAttributes redirectAttributes, Model model) {
		System.out.println("in SuDungMayController themSuDungMayPOST");
		suDungMayValidator.validate(suDungMay, result);
		if (result.hasErrors()) {
			List<KhachHang> danhSachKH = khachHangService.findAll();
			List<May> danhSachMay = mayService.findAll();
			model.addAttribute("danhSachMay", danhSachMay);
			model.addAttribute("danhSachKH", danhSachKH);
			return "suDungMay/them-su-dung-may";
		}
		if (suDungMayService.save(suDungMay) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Thêm thành công Sử dụng máy cho KH có mã: "
					+ suDungMay.getKhachHang().getMaKH() + ", có mã Máy: " + suDungMay.getMay().getMaMay());
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Thêm thất bại Sử dụng máy cho KH có mã: "
					+ suDungMay.getKhachHang().getMaKH() + ", có mã Máy: " + suDungMay.getMay().getMaMay());
		}
		return "redirect:/su-dung-may/danh-sach-su-dung-may";
	}

	@PostMapping(value = { "/sua-chua/{id}" })
	public String suaChuaSuDungMayPOST(@PathVariable(name = "id", required = true) Long id,
			RedirectAttributes redirectAttributes, @ModelAttribute("sua_su_dung_may") @Valid SuDungMay suDungMayNew,
			BindingResult result) {
		System.out.println("in SuDungMayController suaChuaSuDungMayPOST");

		SuDungMay suDungMayOld = suDungMayService.getById(id);
		if (suDungMayOld == null) {
			redirectAttributes.addFlashAttribute("thongBao", "Yêu cầu sửa Sử dụng máy lỗi");
			return "redirect:/su-dung-may/danh-sach-su-dung-may";
		}
		suDungMayValidator.validateUpdate(suDungMayNew, result, suDungMayOld);
		if (result.hasErrors()) {
			System.out.println("has validate error");
			return "suDungMay/sua-su-dung-may";
		}

		if (suDungMayService.update(suDungMayNew, suDungMayOld)) {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa Sử dụng máy thành công");
			return "redirect:/su-dung-may/danh-sach-su-dung-may";
		}

		redirectAttributes.addFlashAttribute("thongBao", "Yêu cầu sửa Sử dụng máy lỗi");
		return "redirect:/su-dung-may/danh-sach-su-dung-may";
	}

	@GetMapping(value = { "/sua-chua/{id}" })
	public String suaChuaSuDungMayGET(@PathVariable(name = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("in SuDungMayController suaChuaSuDungMayGET");

		SuDungMay suDungMay = suDungMayService.getById(id);

		if (suDungMay == null) {
			redirectAttributes.addFlashAttribute("thongBao", "Yêu cầu sửa Sử dụng máy lỗi");
			return "redirect:/su-dung-may/danh-sach-su-dung-may";
		}
		model.addAttribute("sua_su_dung_may", suDungMay);
		List<KhachHang> danhSachKH = khachHangService.findAll();
		List<May> danhSachMay = mayService.findAll();
		model.addAttribute("danhSachMay", danhSachMay);
		model.addAttribute("danhSachKH", danhSachKH);
		return "suDungMay/sua-su-dung-may";
	}

	@GetMapping(value = { "/xoa/{id}" })
	public String xoaSuDungMayByIdGET(@PathVariable(name = "id", required = true) Long id,
			RedirectAttributes redirectAttributes) {
		System.out.println("in SuDungMayController xoaSuDungMayByIdGET");
		System.out.println("id xoa =" + id);
		if (suDungMayService.delete(id) == true) {

			redirectAttributes.addFlashAttribute("thongBao", "Xóa thành công Sử dụng máy");
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thất bại Sử dụng máy");
		}
		return "redirect:/su-dung-may/danh-sach-su-dung-may";
	}
	
	@GetMapping(value = { "/danh-sach-su-dung-may", "/danh-sach-su-dung-may/{page}" })
	public String danhSachSuDungMayGET(Model model, @PathVariable(name = "page", required = false) Integer page) {
		System.out.println("in SuDungMayController danhSachSuDungMayGET");
		List<SuDungMay> list = null;

		if (!model.containsAttribute("suDungMay_TimKiem")) {
			model.addAttribute("suDungMay_TimKiem", new TimKiemSuDungMay());
		}
		model.addAttribute("timKiem", null);
		if (page == null) {
			page = 1;
		}

		Page<SuDungMay> listSuDungMay = suDungMayService.findByPage(page, size);
		if (listSuDungMay.hasContent()) {
			list = listSuDungMay.getContent();
		}
		Integer curentpage = page;
		Integer totalpage = listSuDungMay.getTotalPages();
		model.addAttribute("list", list);
		model.addAttribute("curentpage", curentpage);
		model.addAttribute("totalpage", totalpage);
		return "suDungMay/danh-sach-su-dung-may";
	}
	

	@PostMapping(value = { "/tim-kiem", "/tim-kiem/{page}" })
	public String danhSachSuDungMayTimKiemPOST(
			@ModelAttribute("suDungMay_TimKiem") @Valid TimKiemSuDungMay timKiemSuDungMay, BindingResult result,
			Model model, RedirectAttributes redirectAttributes,
			@PathVariable(name = "page", required = false) Integer page) {

		System.out.println("in SuDungMayController danhSachSuDungMayTimKiemPOST");
		System.out.println("timKiemSuDungMay: " + timKiemSuDungMay.toString());
		timKiemSuDungMay = transform(timKiemSuDungMay);
		System.out.println("timKiemSuDungMay: " + timKiemSuDungMay.toString());
		timKiemSuDungMayValidator.validate(timKiemSuDungMay, result);

		if (result.hasErrors()) {
			System.out.println("validate has error");
			redirectAttributes.addFlashAttribute("suDungMay_TimKiem", timKiemSuDungMay);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.suDungMay_TimKiem",
					result);

			return "redirect:/su-dung-may/danh-sach-su-dung-may";
		}

		String truongTimKiem = timKiemSuDungMay.getTruongTimKiem();
		String tuKhoa = null;
		Date ngayBatDauSuDung_start = null;
		Date ngayBatDauSuDung_end = null;
		Date gioBatDauSuDung_start = null;
		Date gioBatDauSuDung_end = null;
		Integer thoiGianSuDung_start = null;
		Integer thoiGianSuDung_end = null;
		Page<SuDungMay> listSuDungMay = null;
		List<SuDungMay> list = null;
		Integer curentpage = null;
		Integer totalpage = null;
		if (page == null) {
			page = 1;
		}
		System.out.println("maKH page:" + page);
		switch (truongTimKiem) {
		case "maKH":
			System.out.println("maKH page:" + page);
			tuKhoa = timKiemSuDungMay.getTuKhoa();
			listSuDungMay = suDungMayService.findAllByKhachHang(tuKhoa, page, size);
			if (listSuDungMay.hasContent() == true) {
				list = listSuDungMay.getContent();
			}
			curentpage = page;
			totalpage = listSuDungMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("suDungMay_TimKiem", timKiemSuDungMay);
			model.addAttribute("timKiem", "timkiem");
			return "suDungMay/danh-sach-su-dung-may";
		case "maMay":
			tuKhoa = timKiemSuDungMay.getTuKhoa();
			listSuDungMay = suDungMayService.findAllByMay(tuKhoa, page, size);
			if (listSuDungMay.hasContent() == true) {
				list = listSuDungMay.getContent();
			}
			curentpage = page;
			totalpage = listSuDungMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("suDungMay_TimKiem", timKiemSuDungMay);
			model.addAttribute("timKiem", "timkiem");
			return "suDungMay/danh-sach-su-dung-may";
		case "ngayBatDauSuDung":
			ngayBatDauSuDung_start = timKiemSuDungMay.getNgayBatDauSuDung_start();
			ngayBatDauSuDung_end = timKiemSuDungMay.getNgayBatDauSuDung_end();
			listSuDungMay = suDungMayService.findAllByNgayBatDauSuDung(ngayBatDauSuDung_start, ngayBatDauSuDung_end,
					page, size);
			if (listSuDungMay.hasContent() == true) {
				list = listSuDungMay.getContent();
			}
			curentpage = page;
			totalpage = listSuDungMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("suDungMay_TimKiem", timKiemSuDungMay);
			model.addAttribute("timKiem", "timkiem");
			return "suDungMay/danh-sach-su-dung-may";
		case "gioBatDauSuDung":
			gioBatDauSuDung_start = timKiemSuDungMay.getGioBatDauSuDung_start();
			gioBatDauSuDung_end = timKiemSuDungMay.getGioBatDauSuDung_end();
			listSuDungMay = suDungMayService.findAllByGioBatDauSuDung(gioBatDauSuDung_start, gioBatDauSuDung_end, page,
					size);
			if (listSuDungMay.hasContent() == true) {
				list = listSuDungMay.getContent();
			}
			curentpage = page;
			totalpage = listSuDungMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("suDungMay_TimKiem", timKiemSuDungMay);
			model.addAttribute("timKiem", "timkiem");
			return "suDungMay/danh-sach-su-dung-may";
		case "thoiGianSuDung":
			thoiGianSuDung_start = Integer.parseInt(timKiemSuDungMay.getThoiGianSuDung_start());
			thoiGianSuDung_end = Integer.parseInt(timKiemSuDungMay.getThoiGianSuDung_end());
			listSuDungMay = suDungMayService.findAllByThoiGianSuDung(thoiGianSuDung_start, thoiGianSuDung_end, page,
					size);
			if (listSuDungMay.hasContent() == true) {
				list = listSuDungMay.getContent();
			}
			curentpage = page;
			totalpage = listSuDungMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("suDungMay_TimKiem", timKiemSuDungMay);
			model.addAttribute("timKiem", "timkiem");
			return "suDungMay/danh-sach-su-dung-may";

		default:

			return "redirect:/su-dung-may/danh-sach-su-dung-may";
		}
	}

	private TimKiemSuDungMay transform(TimKiemSuDungMay timKiemSuDungMay) {
		timKiemSuDungMay
				.setTuKhoa("".equals(timKiemSuDungMay.getTuKhoa().trim()) ? null : timKiemSuDungMay.getTuKhoa().trim());
		timKiemSuDungMay
				.setThoiGianSuDung_start("".equals(timKiemSuDungMay.getThoiGianSuDung_start().trim()) == true ? null
						: timKiemSuDungMay.getThoiGianSuDung_start().trim());
		timKiemSuDungMay.setThoiGianSuDung_end("".equals(timKiemSuDungMay.getThoiGianSuDung_end().trim()) == true ? null
				: timKiemSuDungMay.getThoiGianSuDung_end().trim());
		return timKiemSuDungMay;
	}

	

	
	
	
	
	
	
	
}
