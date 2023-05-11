package spring.edu.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import spring.edu.models.KhachHang;
import spring.edu.models.May;
import spring.edu.models.SuDungMay;

public interface SuDungMayService {
	SuDungMay getById(Long id);
	
	SuDungMay findAllByKhachHangAndMayAndNgayBatDauSuDungAndGioBatDauSuDung(KhachHang khachHang,
			May may, Date ngayBatDauSuDung, Date gioBatDauSuDung);
	
	boolean save (SuDungMay suDungMay);
	
	boolean update (SuDungMay suDungMayNew, SuDungMay suDungMayOld);
	
	boolean delete (Long id);
	
	List<SuDungMay> findAll();

	Page<SuDungMay> findByPage(int page, int size);

	Page<SuDungMay> findAllByKhachHang(String maKH, int page, int size);

	Page<SuDungMay> findAllByMay(String maMay, int page, int size);

	Page<SuDungMay> findAllByNgayBatDauSuDung(Date ngayBatDauSuDung_start, Date ngayBatDauSuDung_end, int page,
			int size);

	Page<SuDungMay> findAllByGioBatDauSuDung(Date gioBatDauSuDung_start, Date gioBatDauSuDung_end, int page, int size);

	Page<SuDungMay> findAllByThoiGianSuDung(Integer thoiGianSuDung_start, Integer thoiGianSuDung_end, int page,
			int size);
}
