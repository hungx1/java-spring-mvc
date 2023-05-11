package spring.edu.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import spring.edu.models.KhachHang;
import spring.edu.models.May;
import spring.edu.models.SuDungMay;
import spring.edu.repository.SuDungMayRepositoryBasic;
import spring.edu.service.KhachHangService;
import spring.edu.service.MayService;
import spring.edu.service.SuDungMayService;

@Service
public class SuDungMayServiceImpl implements SuDungMayService{
	@Autowired
	SuDungMayRepositoryBasic repositoryBasic;
	
	@Autowired
	SuDungMayService suDungMayService;
	
	@Autowired
	KhachHangService khachHangService;
	
	@Autowired
	MayService mayService;

	@Override
	public SuDungMay getById(Long id) {
		try {
			Optional<SuDungMay> exist = repositoryBasic.findById(id);
			if (exist.isPresent()) {
				return exist.get();
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("SuDungMayService getById fail");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SuDungMay findAllByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung(KhachHang khachHang, May may,
			Date ngayBatDauSuDung, Date gioBatDauSuDung) {
		try {
			return repositoryBasic.findByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung(khachHang, may,
					ngayBatDauSuDung, gioBatDauSuDung);
		} catch (Exception e) {
			System.out.println("SuDungMayService findAllByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung fail");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save(SuDungMay suDungMay) {
		try {
			repositoryBasic.saveAndFlush(suDungMay);
			if (suDungMayService.getById(suDungMay.getId()) != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("SuDungMayService  save SuDungMay có mã KH: " + suDungMay.getKhachHang().getMaKH()
					+ ", mã Máy: " + suDungMay.getMay().getMaMay() + " thất bại");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(SuDungMay suDungMayNew, SuDungMay suDungMayOld) {
		try {
			suDungMayOld = updateInfo(suDungMayOld, suDungMayNew);
			repositoryBasic.saveAndFlush(suDungMayOld);
			System.out.println("update SuDungMay có mã Khách hàng: " + suDungMayOld.getKhachHang().getMaKH()
					+ ", mã Máy" + suDungMayOld.getMay().getMaMay() + " thành công");
			return true;

		} catch (Exception e) {
			System.out.println("SuDungMayService update SuDungMay có id: " + suDungMayOld.getId() + " thất bại");
			e.printStackTrace();
			return false;
		}
	}
	
	private SuDungMay updateInfo(SuDungMay suDungMayOld, SuDungMay suDungMay) {
		suDungMayOld.setKhachHang(suDungMay.getKhachHang());
		suDungMayOld.setMay(suDungMay.getMay());
		suDungMayOld.setNgayBatDauSuDung(suDungMay.getNgayBatDauSuDung());
		suDungMayOld.setGioBatDauSuDung(suDungMay.getGioBatDauSuDung());
		suDungMayOld.setThoiGianSuDung(suDungMay.getThoiGianSuDung());
		return suDungMayOld;
	}

	@Override
	public boolean delete(Long id) {
		try {
			Optional<SuDungMay> suDungMayExist = repositoryBasic.findById(id);

			if (suDungMayExist.isPresent() == false) {
				System.out.println("SuDungMayService delete SuDungMay có id: " + id + " thất bại");
				return false;
			} else {
				SuDungMay suDungMay = suDungMayExist.get();
				repositoryBasic.delete(suDungMay);
				System.out.println("SuDungMayService delete SuDungMay có mã KH: " + suDungMay.getKhachHang().getMaKH()
						+ ", có mã Máy: " + suDungMay.getMay().getMaMay() + ", id: " + id + " thành công");
				return true;
			}

		} catch (Exception e) {
			System.out.println("SuDungMayService delete SuDungMay có id: " + id + " thất bại");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SuDungMay> findAll() {
		return repositoryBasic.findAll();
	}

	@Override
	public Page<SuDungMay> findByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAll(pageable);
		} catch (Exception e) {
			System.out.println("SuDungMayServiceImpl: findAll with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<SuDungMay> findAllByKhachHang(String maKH, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByMaKHContaining("%" + maKH + "%", pageable);
		} catch (Exception e) {
			System.out.println("SuDungMayServiceImpl: findAllByKhachHang with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<SuDungMay> findAllByMay(String maMay, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByMaMayContaining("%" + maMay + "%", pageable);
		} catch (Exception e) {
			System.out.println("SuDungMayServiceImpl: findAllByMay with Pageable  có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<SuDungMay> findAllByNgayBatDauSuDung(Date ngayBatDauSuDung_start, Date ngayBatDauSuDung_end, int page,
			int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByNgayBatDauSuDungBetween(ngayBatDauSuDung_start, ngayBatDauSuDung_end,
					pageable);
		} catch (Exception e) {
			System.out.println("SuDungMayServiceImpl: findAllByNgayBatDauSuDung with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<SuDungMay> findAllByGioBatDauSuDung(Date gioBatDauSuDung_start, Date gioBatDauSuDung_end, int page,
			int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByGioBatDauSuDungBetween(gioBatDauSuDung_start, gioBatDauSuDung_end,
					pageable);
		} catch (Exception e) {
			System.out.println("SuDungMayServiceImpl: findAllByNgayBatDauSuDung with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<SuDungMay> findAllByThoiGianSuDung(Integer thoiGianSuDung_start, Integer thoiGianSuDung_end, int page,
			int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		try {
			return repositoryBasic.findAllByThoiGianSuDungBetween(thoiGianSuDung_start, thoiGianSuDung_end, pageable);
		} catch (Exception e) {
			System.out.println("SuDungMayServiceImpl: findAllByThoiGianSuDung with Pageable có lỗi");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
}
