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
import spring.edu.models.May;
import spring.edu.service.MayService;
import spring.edu.validator.MayValidator;


@Controller
@RequestMapping("/may")
public class MayController {
	@Autowired
	MayService mayService;
	@Autowired
	MayValidator mayValidator;

	private Integer size = 5;
	
	@GetMapping("/them-may")
	public String themMayGET(Model model) {
		System.out.println("in MayController themkMayGET");
		if (!(model.containsAttribute("them-may"))) {
			model.addAttribute("them-may", new May());
		}
		return "May/them-may";
	}

	@PostMapping("/them-may")
	public String themKhachHangPOST(@ModelAttribute("them-may") @Valid May may, BindingResult result,
			RedirectAttributes redirectAttributes) {
		System.out.println("in MayController themMayPOST");
		mayValidator.validate(may, result);
		if (result.hasErrors()) {
			return "May/them-may";
		}
		if (mayService.save(may)) {
			redirectAttributes.addFlashAttribute("msg", "Tạo thành công Máy có mã: " + may.getMaMay());
			return "redirect:/trang-chu";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Tạo thất bại Máy có mã: " + may.getMaMay());
			return "redirect:/trang-chu";
		}
	}

	@GetMapping("/sua-chua/{maMay}")
	public String suaChuaMaygGET(@PathVariable("maMay") String maMay, Model model) {
		System.out.println("in MayController suaChuaMayGET");
		model.addAttribute("sua_may", mayService.getByMaMay(maMay));
		return "May/sua-may";
	}

	@PostMapping("/sua-chua/{maMay}")
	public String suaChuaMayPOST(@PathVariable("maMay") String maMay, Model model,
			RedirectAttributes redirectAttributes, @ModelAttribute("sua_may") @Valid May may, BindingResult result) {
		System.out.println("in MayController suaChuaMayPOST");

		mayValidator.validateUpdate(may, result, maMay);
		if (result.hasErrors()) {
			return "May/sua-may";
		}
		if (mayService.update(may, maMay) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa thành công Máy có mã " + maMay);
			return "redirect:/may/danh-sach-may";
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa thất bại Máy có mã " + maMay);
			return "redirect:/may/danh-sach-may";
		}
	}

	@GetMapping("/xoa/{maMay}")
	public String xoaMayGET(@PathVariable("maMay") String maMay, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("in MayController xoaMayGET");
		if (mayService.delete(maMay) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thành công May có mã " + maMay);
			return "redirect:/may/danh-sach-may";
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thất bại Máy có mã " + maMay);
			return "redirect:/may/danh-sach-may";
		}
	}
	
	@GetMapping(value = { "/danh-sach-may", "/danh-sach-may/{page}" })
	public String danhSachMayGET(Model model, @PathVariable(name = "page", required = false) Integer page) {
		System.out.println("in MayController danhSachMayGET");
		if (page == null) {
			page = 1;
		}
		Page<May> listMay = mayService.findByPage(page, size);
		List<May> list = null;
		if (listMay.hasContent() == false) {
			list = null;
		} else {
			list = listMay.getContent();
		}
//		System.out.println("list size = " + list.size() + " - " + listMay.getTotalElements());
		Integer curentpage = page;
		Integer totalpage = listMay.getTotalPages();
		model.addAttribute("list", list);
		model.addAttribute("curentpage", curentpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("may_TimKiem", new TimKiem());
		return "May/danh-sach-may";
	}

	@GetMapping(value = { "/may/danh-sach-may/tim-kiem/{page}/{tuKhoa}/{truongTimKiem}" })
	public String danhSachMayTimKiemGET(@PathVariable("page") Integer page, @PathVariable("tuKhoa") String tuKhoa,
			@PathVariable("truongTimKiem") String truongTimKiem, Model model) {
		System.out.println("in MayController danhSachMayTimKiemGET");

		TimKiem timKiem = new TimKiem();
		timKiem.setTuKhoa(tuKhoa);
		timKiem.setTruongTimKiem(truongTimKiem);

		Page<May> listMay = null;
		List<May> list = null;
		Integer curentpage = null;
		Integer totalpage = null;

		switch (truongTimKiem) {
		case "maMay":
			listMay = mayService.findAllByMaMay(tuKhoa, page, size);
			if (listMay.hasContent() == false) {
				list = null;
			} else {
				list = listMay.getContent();
			}
			curentpage = page;
			totalpage = listMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("may_TimKiem", timKiem);
			return "May/danh-sach-may";
		case "viTri":
			listMay = mayService.findAllByViTri(tuKhoa, page, size);
			if (listMay.hasContent() == false) {
				list = null;
			} else {
				list = listMay.getContent();
			}
			curentpage = page;
			totalpage = listMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("may_TimKiem", timKiem);
			return "May/danh-sach-may";
		case "trangThai":
			listMay = mayService.findAllByTrangThai(tuKhoa, page, size);
			if (listMay.hasContent() == false) {
				list = null;
			} else {
				list = listMay.getContent();
			}
			curentpage = page;
			totalpage = listMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("may_TimKiem", timKiem);
			return "May/danh-sach-may";

		default:
			return "redirect:/may/danh-sach-may";
		}
	}

	@PostMapping(value = { "/danh-sach-may/tim-kiem" })
	public String danhSachMayTimKiemPOST(@ModelAttribute("may_TimKiem") TimKiem timKiem, Model model) {
		System.out.println("in MayController danhSachMayTimKiemPOST");

		if ("".equals(timKiem.getTuKhoa().trim()) == true) {
			return "redirect:/may/danh-sach-may";
		}

		String tuKhoa = timKiem.getTuKhoa().trim();
		String truongTimKiem = timKiem.getTruongTimKiem();
		Integer page = null;
		Page<May> listMay = null;
		List<May> list = null;
		Integer curentpage = null;
		Integer totalpage = null;
		switch (truongTimKiem) {
		case "maMay":
			page = 1;
			listMay = mayService.findAllByMaMay(tuKhoa, page, size);
			if (listMay.hasContent() == false) {
				list = null;
			} else {
				list = listMay.getContent();
			}
			curentpage = page;
			totalpage = listMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("may_TimKiem", timKiem);
			return "May/danh-sach-may";
		case "viTri":
			page = 1;
			listMay = mayService.findAllByViTri(tuKhoa, page, size);
			if (listMay.hasContent() == false) {
				list = null;
			} else {
				list = listMay.getContent();
			}
			curentpage = page;
			totalpage = listMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("may_TimKiem", timKiem);
			return "May/danh-sach-may";
		case "trangThai":
			page = 1;
			listMay = mayService.findAllByTrangThai(tuKhoa, page, size);
			if (listMay.hasContent() == false) {
				list = null;
			} else {
				list = listMay.getContent();
			}
			curentpage = page;
			totalpage = listMay.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("may_TimKiem", timKiem);
			return "May/danh-sach-may";

		default:
			return "redirect:/may/danh-sach-may";
		}
	}

	

	
	
	
	
}
