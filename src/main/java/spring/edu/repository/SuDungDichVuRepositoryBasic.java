package spring.edu.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.edu.models.DichVu;
import spring.edu.models.KhachHang;
import spring.edu.models.SuDungDichVu;

@Repository
@Transactional
public interface SuDungDichVuRepositoryBasic extends JpaRepository<SuDungDichVu, Long> {
	Optional<SuDungDichVu> findByKhachHangAndDichVuAndNgaySuDungAndGioSuDung(KhachHang khachHang, 
			DichVu dichVu, LocalDate ngaySuDung, LocalTime gioSuDung);
}
