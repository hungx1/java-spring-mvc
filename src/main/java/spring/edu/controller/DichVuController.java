package spring.edu.controller;

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

import spring.edu.dto.TimKiem;
import spring.edu.models.DichVu;
import spring.edu.service.DichVuService;
import spring.edu.validator.DichVuValidator;

@Controller
@RequestMapping("/dich-vu")
public class DichVuController {
	@Autowired
	DichVuService dichVuService;
	@Autowired
	DichVuValidator dichVuValidator;
	private Integer size = 5;
	
	@GetMapping("/them-dich-vu")
	public String themDichVuGET(Model model) {
		System.out.println("in DichVuController themDichVuGET");
		if (!(model.containsAttribute("them-dich-vu"))) {
			model.addAttribute("them-dich-vu", new DichVu());
		}
		return "DichVu/them-dich-vu";
	}

	@PostMapping("/them-dich-vu")
	public String themDichVuPOST(@ModelAttribute("them-dich-vu") @Valid DichVu dichVu, BindingResult result,
			RedirectAttributes redirectAttributes) {
		System.out.println("in DichVuController themDichVuPOST");
		dichVu = ignoreBlank(dichVu);
		dichVuValidator.validate(dichVu, result);
		if (result.hasErrors()) {
			return "DichVu/them-dich-vu";
		}
		if (dichVuService.save(dichVu)) {
			redirectAttributes.addFlashAttribute("msg", "Tạo thành công dịch vụ có mã: " + dichVu.getMaDV());
			return "redirect:/trang-chu";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Tạo thất bại dịch vụ có mã: " + dichVu.getMaDV());
			return "redirect:/trang-chu";
		}
	}

	private DichVu ignoreBlank(DichVu dichVu) {
		dichVu.setTenDV("".equals(dichVu.getTenDV().trim()) == true ? null : dichVu.getTenDV().trim());
		return dichVu;
	}
	
	
	@GetMapping("/sua-chua/{maDV}")
	public String suaChuaDichVuGET(@PathVariable("maDV") String maDV, Model model) {
		System.out.println("in DichVuController suaChuaDichVuGET");
		model.addAttribute("sua_dich_vu", dichVuService.getByMaDV(maDV));
		return "DichVu/sua-dich-vu";
	}

	@PostMapping("/sua-chua/{maDV}")
	public String suaChuaDichVuPOST(@PathVariable("maDV") String maDV, Model model,
			RedirectAttributes redirectAttributes, @ModelAttribute("sua_dich_vu") @Valid DichVu dichVu,
			BindingResult result) {
		System.out.println("in DichVuController suaChuaDichVuPOST");

		dichVuValidator.validateUpdate(dichVu, result, maDV);
		if (result.hasErrors()) {
			return "DichVu/sua-dich-vu";
		}
		if (dichVuService.update(dichVu, maDV) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa thành công Dịch Vụ có mã " + maDV);
			return "redirect:/dich-vu/danh-sach-dich-vu";
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa thất bại Dịch Vụ có mã " + maDV);
			return "redirect:/dich-vu/danh-sach-dich-vu";
		}
	}

	@GetMapping("/xoa/{maDV}")
	public String xoaDichVuGET(@PathVariable("maDV") String maDV, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("in DichVuController xoaDichVuGET");
		if (dichVuService.delete(maDV) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thành công Dịch Vụ có mã " + maDV);
			return "redirect:/dich-vu/danh-sach-dich-vu";
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thất bại Dịch Vụ có mã " + maDV);
			return "redirect:/dich-vu/danh-sach-dich-vu";
		}
	}
	
	@GetMapping(value = { "/danh-sach-dich-vu", "/danh-sach-dich-vu/{page}" })
	public String danhSachDichVuGET(Model model, @PathVariable(name = "page", required = false) Integer page) {
		System.out.println("in DichVuController danhSachDichVuGET");
		if (page == null) {
			page = 1;
		}
		Page<DichVu> listDV = dichVuService.findByPage(page, size);
		List<DichVu> list = null;
		if (listDV.hasContent() == false) {
			list = null;
		} else {
			list = listDV.getContent();
		}
		System.out.println("list size = " + list.size() + " - " + listDV.getTotalElements());
		Integer curentpage = page;
		Integer totalpage = listDV.getTotalPages();
		model.addAttribute("list", list);
		model.addAttribute("curentpage", curentpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("dichVu_TimKiem", new TimKiem());
		return "DichVu/danh-sach-dich-vu";
	}
	

	@GetMapping(value = { "/danh-sach-dich-vu/tim-kiem/{page}/{tuKhoa}/{truongTimKiem}" })
	public String danhSachDichVuTimKiemGET(@PathVariable("page") Integer page, @PathVariable("tuKhoa") String tuKhoa,
			@PathVariable("truongTimKiem") String truongTimKiem, Model model) {
		System.out.println("in DichVuController danhSachDichVuTimKiemGET");

		TimKiem timKiem = new TimKiem();
		timKiem.setTuKhoa(tuKhoa);
		timKiem.setTruongTimKiem(truongTimKiem);

		Page<DichVu> listDV = null;
		List<DichVu> list = null;
		Integer curentpage = null;
		Integer totalpage = null;

		switch (truongTimKiem) {
		case "maDV":
			listDV = dichVuService.findAllByMaDV(page, size, tuKhoa);
			if (listDV.hasContent() == false) {
				list = null;
			} else {
				list = listDV.getContent();
			}
			curentpage = page;
			totalpage = listDV.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("dichVu_TimKiem", timKiem);
			return "DichVu/danh-sach-dich-vu";
		case "tenDV":
			listDV = dichVuService.findAllByTenDV(page, size, tuKhoa);
			System.out.println("listDv hasContent :" + listDV.hasContent());

			list = listDV.getContent();
			curentpage = page;
			totalpage = listDV.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("dichVu_TimKiem", timKiem);
			return "DichVu/danh-sach-dich-vu";

		default:
			return "redirect:/dich-vu/danh-sach-dich-vu";
		}
	}

	@PostMapping(value = { "/danh-sach-dich-vu/tim-kiem" })
	public String danhSachDichVuTimKiemPOST(@ModelAttribute("dichVu_TimKiem") TimKiem timKiem, Model model) {
		System.out.println("in DichVuController danhSachDichVuTimKiemPOST");

		if ("".equals(timKiem.getTuKhoa().trim()) == true) {
			return "redirect:/dich-vu/danh-sach-dich-vu";
		}

		String tuKhoa = timKiem.getTuKhoa().trim();
		String truongTimKiem = timKiem.getTruongTimKiem();
		Integer page = null;
		Page<DichVu> listDV = null;
		List<DichVu> list = null;
		Integer curentpage = null;
		Integer totalpage = null;
		switch (truongTimKiem) {
		case "maDV":
			page = 1;
			listDV = dichVuService.findAllByMaDV(page, size, tuKhoa);
			if (listDV.hasContent() == false) {
				list = null;
			} else {
				list = listDV.getContent();
			}
			curentpage = page;
			totalpage = listDV.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("dichVu_TimKiem", timKiem);
			return "DichVu/danh-sach-dich-vu";
		case "tenDV":
			page = 1;
			listDV = dichVuService.findAllByTenDV(page, size, tuKhoa);
			if (listDV.hasContent() == false) {
				list = null;
//				System.out.println("listDV hasContent " + listDV.hasContent());
			} else {
				list = listDV.getContent();
			}
			curentpage = page;
			totalpage = listDV.getTotalPages();
//			System.out.println("total page : " + totalpage);
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("dichVu_TimKiem", timKiem);
//			System.out.println("return page");
			return "DichVu/danh-sach-dich-vu";

		default:
			return "redirect:/dich-vu/danh-sach-dich-vu";
		}
	}

	

	
	
	
	
	
	
	
}
