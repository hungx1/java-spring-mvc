package spring.edu.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.edu.models.DichVu;

@Repository
@Transactional
public interface DichVuRepositoryBasic extends JpaRepository<DichVu, Long> {
	DichVu findByMaDV(String maDV);
	
	Page<DichVu> findAllByMaDVContaining(String maDV, Pageable pageable);
	
	Page <DichVu> findAllByTenDVContaining(String tenDV, Pageable pageable);
}
