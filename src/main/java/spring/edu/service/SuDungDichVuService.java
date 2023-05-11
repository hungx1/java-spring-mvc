package spring.edu.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;

import spring.edu.models.DichVu;
import spring.edu.models.KhachHang;
import spring.edu.models.SuDungDichVu;

public interface SuDungDichVuService {
	SuDungDichVu getById(Long id);
	
	boolean save(SuDungDichVu suDungDichVu);
	
	SuDungDichVu findByKhachHangAndDichVuAndNgaySuDungAndGioSuDung(KhachHang khachHang, 
			DichVu dichVu, LocalDate ngaySuDung, LocalTime gioSuDung);
	
	List<SuDungDichVu> findAll();
	
	Page<SuDungDichVu> findByPage(int page, int size);
}
