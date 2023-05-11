package spring.edu.service;

import java.util.List;

import org.springframework.data.domain.Page;

import spring.edu.models.DichVu;

public interface DichVuService {
	DichVu getById(Long id);
	
	DichVu getByMaDV(String maDV);
	
	boolean save(DichVu dichvu);
	
	List<DichVu> findAll();
	
	Page<DichVu> findByPage(int page, int size);
	
	Page<DichVu> findAllByMaDV(int page, int size, String maDV);
	
	Page<DichVu> findAllByTenDV(int page, int size, String tenDV);
	
	boolean update(DichVu dichVu, String maDV);
	
	boolean delete(String maDV);
}
