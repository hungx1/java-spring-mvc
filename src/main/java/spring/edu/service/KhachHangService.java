package spring.edu.service;

import java.util.List;

import org.springframework.data.domain.Page;

import spring.edu.models.KhachHang;

public interface KhachHangService {
	KhachHang getById(Long id);
	
	KhachHang getByMaKH(String maKH);
	
	boolean save(KhachHang khachHang);
	
	List<KhachHang> findAll();
	
	Page<KhachHang> findByPage(int page, int size);
	
	Page<KhachHang> findAllByMaKH(int page, int size, String maKH);
	
	Page<KhachHang> findAllByTenKH(int page, int size, String tenKH);
	
	Page<KhachHang> findAllBySoDienThoai(int page, int size, String soDienThoai);
	
	Page<KhachHang> findAllByDiaChiEmail (int page, int size, String diaChiEmail);
	
	Page<KhachHang> findAllByDiaChi(int page, int size, String diaChiKH);

	boolean update(KhachHang khachHang, String maKH);

	boolean delete(String maKH);

	Page<Object[]> getTotalMoneyByAllKhachHang(int page, int size);
}
