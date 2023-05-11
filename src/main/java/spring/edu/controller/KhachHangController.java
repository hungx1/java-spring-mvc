package spring.edu.controller;

import java.util.ArrayList;
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
import spring.edu.dto.TongTienKH;
import spring.edu.models.KhachHang;
import spring.edu.service.KhachHangService;
import spring.edu.validator.KhachHangValidator;


@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {
	@Autowired
	KhachHangService khachHangService;
	@Autowired
	KhachHangValidator khachHangValidator;

	private Integer size = 5;

	@GetMapping("/them-khach-hang")
	public String themKhachHangGET(Model model) {
		System.out.println("in KhachHangController themkKhachHangGET");
		if (!(model.containsAttribute("them-khach-hang"))) {
			model.addAttribute("them-khach-hang", new KhachHang());
		}
		return "KhachHang/them-khach-hang";
	}

	@PostMapping("/them-khach-hang")
	public String themKhachHangPOST(@ModelAttribute("them-khach-hang") @Valid KhachHang khachHang, BindingResult result,
			RedirectAttributes redirectAttributes) {
		System.out.println("in KhachHangController themKhachHangPOST");
		khachHang = ignoreBlank(khachHang);
		khachHangValidator.validate(khachHang, result);
		if (result.hasErrors()) {
			return "KhachHang/them-khach-hang";
		}
		if (khachHangService.save(khachHang)) {
			redirectAttributes.addFlashAttribute("msg", "Tạo thành công Khách hàng có tên: " + khachHang.getTenKH());
			return "redirect:/trang-chu";
		} else {
			redirectAttributes.addFlashAttribute("msg", "Tạo thất bại Khách hàng có tên: " + khachHang.getTenKH());
			return "redirect:/trang-chu";
		}

	}

	private KhachHang ignoreBlank(KhachHang kh) {
		kh.setDiaChi(kh.getDiaChi().trim());
		kh.setDiaChiEmail("".equals(kh.getDiaChiEmail().trim()) ? null : kh.getDiaChiEmail().trim());
		kh.setTenKH(kh.getTenKH().trim());
		kh.setSoDienThoai("".equals(kh.getSoDienThoai().trim()) ? null : kh.getSoDienThoai().trim());
		return kh;
	}

	@GetMapping("/sua-chua/{maKH}")
	public String suaChuaKhachHangGET(@PathVariable("maKH") String maKH, Model model) {
		System.out.println("in KhachHangController suaChuaKhachHangGET");
		model.addAttribute("sua_khach_hang", khachHangService.getByMaKH(maKH));
		return "KhachHang/sua-khach-hang";
	}

	@PostMapping("/sua-chua/{maKH}")
	public String suaChuaKhachHangPOST(@PathVariable("maKH") String maKH, Model model,
			RedirectAttributes redirectAttributes, @ModelAttribute("sua_khach_hang") @Valid KhachHang khachHang,
			BindingResult result) {
		System.out.println("in KhachHangController suaChuaKhachHangPOST");

		khachHangValidator.validateUpdate(khachHang, result, maKH);
		if (result.hasErrors()) {
			return "KhachHang/sua-khach-hang";
		}
		if (khachHangService.update(khachHang, maKH) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa thành công Khách hàng có mã " + maKH);
			return "redirect:/khach-hang/danh-sach-khach-hang";
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Sửa thất bại Khách hàng có mã " + maKH);
			return "redirect:/khach-hang/danh-sach-khach-hang";
		}
	}

	@GetMapping("/xoa/{maKH}")
	public String xoaKhachHangGET(@PathVariable("maKH") String maKH, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("in KhachHangController xoaKhachHangGET");
		if (khachHangService.delete(maKH) == true) {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thành công Khách hàng có mã " + maKH);
			return "redirect:/khach-hang/danh-sach-khach-hang";
		} else {
			redirectAttributes.addFlashAttribute("thongBao", "Xóa thất bại Khách hàng có mã " + maKH);
			return "redirect:/khach-hang/danh-sach-khach-hang";
		}
	}
	
	@GetMapping(value = { "/danh-sach-khach-hang", "/danh-sach-khach-hang/{page}" })
	public String danhSachKhachHangGET(Model model, @PathVariable(name = "page", required = false) Integer page) {
		System.out.println("in KhachHangController danhSachKhachHangGET");
		if (page == null) {
			page = 1;
		}
		Page<KhachHang> listKH = khachHangService.findByPage(page, size);
		List<KhachHang> list = null;
		if (listKH.hasContent() == false) {
			list = null;
		} else {
			list = listKH.getContent();
		}
//		System.out.println("list size = " + list.size() + " - " + listKH.getTotalElements());
		Integer curentpage = page;
		Integer totalpage = listKH.getTotalPages();
		model.addAttribute("list", list);
		model.addAttribute("curentpage", curentpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("khachHang_TimKiem", new TimKiem());
		return "KhachHang/danh-sach-khach-hang";
	}

	@GetMapping(value = { "/danh-sach-khach-hang/tim-kiem/{page}/{tuKhoa}/{truongTimKiem}" })
	public String danhSachKhachHangTimKiemGET(@PathVariable("page") Integer page, @PathVariable("tuKhoa") String tuKhoa,
			@PathVariable("truongTimKiem") String truongTimKiem, Model model) {
		System.out.println("in KhachHangController danhSachKhachHangTimKiemGET");

		TimKiem timKiem = new TimKiem();
		timKiem.setTuKhoa(tuKhoa);
		timKiem.setTruongTimKiem(truongTimKiem);

		Page<KhachHang> listKH = null;
		List<KhachHang> list = null;
		Integer curentpage = null;
		Integer totalpage = null;

		switch (truongTimKiem) {
		case "maKH":
			listKH = khachHangService.findAllByMaKH(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "tenKH":
			listKH = khachHangService.findAllByTenKH(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "diaChi":
			listKH = khachHangService.findAllByDiaChi(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "soDienThoai":
			listKH = khachHangService.findAllBySoDienThoai(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "diaChiEmail":
			listKH = khachHangService.findAllByDiaChiEmail(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";

		default:
			return "redirect:/khach-hang/danh-sach-khach-hang";
		}
	}

	@PostMapping(value = { "/danh-sach-khach-hang/tim-kiem" })
	public String danhSachKhachHangTimKiemPOST(@ModelAttribute("khachHang_TimKiem") TimKiem timKiem, Model model) {
		System.out.println("in KhachHangController danhSachKhachHangTimKiemPOST");

		if ("".equals(timKiem.getTuKhoa().trim()) == true) {
			return "redirect:/khach-hang/danh-sach-khach-hang";
		}

		String tuKhoa = timKiem.getTuKhoa().trim();
		String truongTimKiem = timKiem.getTruongTimKiem();
		Integer page = null;
		Page<KhachHang> listKH = null;
		List<KhachHang> list = null;
		Integer curentpage = null;
		Integer totalpage = null;
		switch (truongTimKiem) {
		case "maKH":
			page = 1;
			listKH = khachHangService.findAllByMaKH(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "tenKH":
			page = 1;
			listKH = khachHangService.findAllByTenKH(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "diaChi":
			page = 1;
			listKH = khachHangService.findAllByDiaChi(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "soDienThoai":
			page = 1;
			listKH = khachHangService.findAllBySoDienThoai(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";
		case "diaChiEmail":
			page = 1;
			listKH = khachHangService.findAllByDiaChiEmail(page, size, tuKhoa);
			if (listKH.hasContent() == false) {
				list = null;
			} else {
				list = listKH.getContent();
			}
			curentpage = page;
			totalpage = listKH.getTotalPages();
			model.addAttribute("list", list);
			model.addAttribute("curentpage", curentpage);
			model.addAttribute("totalpage", totalpage);
			model.addAttribute("khachHang_TimKiem", timKiem);
			return "KhachHang/danh-sach-khach-hang";

		default:
			return "redirect:/khach-hang/danh-sach-khach-hang";
		}
	}
	
	@GetMapping(value={"/tong-tien","/tong-tien/{page}"})
	public String tongTienKhachHangGET(Model model,@PathVariable(name="page",required = false)Integer page) {
		System.out.println("in KhachHangController tongTienKhachHangGET");
		if(page==null) {
			page = 1;
		}
		Page<Object[]> listKH = khachHangService.getTotalMoneyByAllKhachHang(page , size);
		List<TongTienKH> list = new ArrayList<TongTienKH>();
		
		for (Object[] object : listKH) {
			TongTienKH tongTienKH = new TongTienKH();
			tongTienKH.setMaKH(object[0] == null ? null : object[0].toString());
			tongTienKH.setTenKH(object[1] == null ? null : object[1].toString());
			tongTienKH.setMaMay(object[2] == null ? null : object[2].toString());
			tongTienKH.setViTriMay(object[3] == null ? null : object[3].toString());
			tongTienKH.setTrangThai(object[4] == null ? null : object[4].toString());
			tongTienKH.setNgayBatDauSuDung(object[5] == null ? null : object[5].toString());
			tongTienKH.setGioBatDauSuDung(object[6] == null ? null : object[6].toString());
			tongTienKH.setThoiGianSuDung(object[7] == null ? null : object[7].toString());
			tongTienKH.setMaDV(object[8] == null ? null : object[8].toString());
			tongTienKH.setNgaySuDung(object[9] == null ? null : object[9].toString());
			tongTienKH.setGioSuDung(object[10] == null ? null : object[10].toString());
			tongTienKH.setSoLuong(object[11] == null ? null : object[11].toString());
			tongTienKH.setTongTien(object[12] == null ? null : object[12].toString());
			System.out.println(tongTienKH.toString());
			list.add(tongTienKH);
		}
		if (list.size() == 0) {
			model.addAttribute("list", null);
			
			return "KhachHang/tong-tien";
		}
		model.addAttribute("list", list);
		Integer curentpage = page;
		Integer totalpage = listKH.getTotalPages();
		model.addAttribute("list", list);
		model.addAttribute("curentpage", curentpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("TimKiem", "timKiem");
		return "KhachHang/tong-tien";
	}
	
	
	
	
	
	
	
	
}
