package spring.edu.service;

import java.util.List;

import org.springframework.data.domain.Page;

import spring.edu.models.May;

public interface MayService {
	May getById(Long id);
	
	May getByMaMay (String maMay);
	
	boolean save(May may);
	
	boolean delete(String maMay);
	
	boolean update(May may, String maMay);
	
	List<May> findAll();
	
	Page<May> findByPage(int page, int size);
	
	Page<May> findAllByMaMay(String maMay, int page, int size);
	
	Page<May> findAllByViTri(String viTri, int page, int size);
	
	Page<May> findAllByTrangThai(String trangThai, int page, int size);
	
	
	
}
