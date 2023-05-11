package spring.edu.repository;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.edu.models.May;

@Repository
@Transactional
public interface MayRepositoryBasic extends JpaRepository<May, Long>{
	May findByMaMay(String maMay);
	
	May findByMaMayContaining(String maMay);
	
	Page<May> findAllByMaMayContaining(String maMay, Pageable pageable);
	
	Page<May> findAllByViTriContaining(String viTri, Pageable pageable);
	
	Page<May> findAllByTrangThaiContaining(String trangThai, Pageable pageable);
}
